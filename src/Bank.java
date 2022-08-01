import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    /**
     * Create a new Banl object with empty list of users and accounts
     * @param name the name of the bank
     */
    public Bank(String name){
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    /**
     * Generate a new universally unique id for a user
     *
     * @return the uuid
     */
    public String getNewUserUUID() {
        //inits
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique = false;

        //continue looping until we get a unique ID.
        do {
            // generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }

            //check to make sure it's unique
            nonUnique = false;
            for (User u : this.users) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    break;
                }
            }

        } while (nonUnique);

        return uuid;

    }

    /**
     * Generate a new universally unique ID for an account
     * @return the uuid
     */
    public String getNewAccountUUID() {
    //inits
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique = false;

        //continue looping until we get a unique ID.
        do {
            // generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }

            //check to make sure it's unique
            nonUnique = false;
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUUID()) == 0) {
                    break;
                }
            }
        } while (nonUnique);

        return uuid;
    }


    /**
     * Add an account
     * @param anAcct the account to add
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /**
     * create a new user of the bank
      * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param pin       the user's pin
     * @return          the new User object
     */
     public User addUser(String firstName, String lastName, String pin){
        //Create a new user object and add it to our list
         User newUser = new User(firstName, lastName, pin, this);
         this.users.add(newUser);

         //create a savings account for the user and add to User and Bank
         //account lists
         Account newAccount = new Account("Savings", newUser,this);
         newUser.addAccount(newAccount);
         this.addAccount(newAccount);
         return newUser;
     }
public User userLogin(String userID, String pin){
    for (User u : this.users) {
        //check user ID is correct
        if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)){
            return u;
        }
    }
    //if we haven't found the user or have an incorrect pin
    return null;
}

    public String getName() {
        return this.name;
    }
}
