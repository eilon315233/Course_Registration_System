// This file contains Practitioner class which is a subclass of the User class.
public class Practitioner extends User{
    public Practitioner(String name, String id, String password) {
        super(name, id, password);
    }

    //This method prints the details of the Practitioner.
    @Override
    void printDetails() {
        System.out.println("Practitioner: " + getName());
        System.out.println("ID: " + getId());
    }
}
