import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    /**
     * The first name of the user
     */
    private String firstName;

    /**
     * The last name of the user.
     */
    private String lastName;

    /**
     * The id number of the user;
     */
    private String uuid;

    /**
     * The MD5 hash of the user's pin  number;
     */
    private byte pinHash[];

    /**
     * The list of accounts
     */
    private ArrayList<Account>  accounts;

    /**
     * create a new user
     * @param firstName the users first name
     * @param lastName the users last name
     * @param pin the user's account pin
     * @param theBank the bank object that the user is a customer of
     */

    public User(String firstName, String lastName, String pin, Bank theBank) {
        //set user's name
        this.firstName = firstName;
        this.lastName = lastName;

        //Store the pin's MD5 hash, rather than the original value for security reasons.
        try {
            MessageDigest md  = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());

        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithm Exception");
            e.printStackTrace();
            System.exit(1);
        }
        //get a new unique universal id for the user.
        this.uuid = theBank.getNewUserUUID();

        //Create empty list of accounts
        this.accounts = new ArrayList<Account>();

        //Print log message
        System.out.printf("New user %s, %s with ID %s created \n",
                lastName, firstName, this.uuid);
    }

    /**
     * Add an account for the user
     * @param anAcct
     */
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }

    /**
     *
     * @return the user uuid
     */
    public String getUUID() {
        return this.uuid;
    }

    /**
     * Check whether a given pin matches the true user pin
     * @param aPin the pin to check;
     * @return whether the pin is valid or not
     */
    public boolean validatePin(String aPin){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
        }catch (NoSuchAlgorithmException e){
            System.err.println("error, caught NoSuchException");
            e.printStackTrace();;
            System.exit(1);
        }
        return false;
    }

}
