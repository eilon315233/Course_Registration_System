//This class is a subclass of User and represents a Lecturer in the system.
public class Lecturer extends User {
    //This constructor initializes the Lecturer object with the given parameters.
    public Lecturer(String name, String id, String password) {
        super(name, id, password);
    }

    //This method prints the details of the Lecturer.
    @Override
    void printDetails() {
        System.out.println("Lecturer: " + getName());
        System.out.println("ID: " + getId());
    }
}
