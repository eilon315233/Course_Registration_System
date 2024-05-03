// This is the abstract class for the User class, representing students and practitioners and lecturers.
public abstract class User {
    //variables
    private String name;
    private String ID;
    private String password;


    //constructor
    public User(String name, String id, String password) {
        this.name = name;
        this.ID = id;
        this.password = password;
    }

    //methods
    public String getName() {
        return name;
    }
    public String getId() {
        return ID;
    }
    public String getPassword() {
        return password;
    }

    abstract void printDetails(); //This method prints the details of the User.


}
