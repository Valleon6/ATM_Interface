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

    /**
     * Get summary line for the account
     * @return the string summary
     */
    public String getSummaryLine(){
        //get the account's balance
        double balance = this.getBalance();

        //format the summary line, depending on whether the balance is negative
        if(balance >=  0){
            return String.format("%s : $%.2f : %s", this.uuid, balance, this.name);
        }else {
            return String.format("%s : $%.2f : %s", this.uuid, balance, this.name);
        }
    }

    public double getBalance(){
        double balance = 0;
        for (Transaction t : this.transactions) {
            balance += t.getAmount();
        }
        return balance;
    }
    public void printTransHistory() {
        System.out.printf("\n Transaction history for account %s\n", this.uuid);
        for (int t = this.transactions.size() - 1; t >= 0; t--) {
            System.out.println(this.transactions.get(t).getSummaryLine() );
        }
        System.out.println();
    }

    public void addTransaction(double amount, String memo){
        //create new transaction object and add it to out list
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }

}
