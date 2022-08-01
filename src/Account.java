import java.util.ArrayList;

public class Account {
    /**
     * The name of the account
     */
    private String name;

    /**
     * The account ID number
     */
    private String uuid;

    /**
     * The user object that owns this account.
     */
    private User holder;

    /**
     * The list of transactions for this account.
     */
    private ArrayList<Transaction> transactions;

    /**
     *
     * @param name the name of the account
     * @param holder the user object that holds this account
     * @param theBank the bank that issues the account
     */
    public Account(String name, User holder, Bank theBank) {
        //set the account name and holder
        this.name = name;
        this.holder = holder;
        //get newAccountUUid
        this.uuid = theBank.getNewAccountUUID();

        // Initialize transaction
        this.transactions = new ArrayList<Transaction>();

        //add to holder and bank lists

    }

    /**
     * Get the account UUID
     * @return the uuid
     */
    public String getUUID(){
        return this.uuid;
    }

}
