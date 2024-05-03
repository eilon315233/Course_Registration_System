import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

//This class is the parent class of the course classes
public abstract class Course implements Observable {

    //variables
    private String ID; // course ID
    private String name; // course name
    private int capacity; // course capacity
    private String type; // course type (chosen, required, seminar)
    private User owner; // course owner (Lecturer, Student, Practitioner)
    private int registered; // number of registered students
    private final HashSet<Observer> observers = new HashSet<>(); // list of observers


    //constructor
    public Course(User owner,String name, String id, int capacity) {
        this.owner = owner;
        this.name = name;
        this.ID = id;
        this.capacity = capacity;
        this.registered = 0;
    }

    //methods
    public String get_id() {
        return this.ID;
    }
    public String get_name() {
        return this.name;
    }
    public int get_capacity() {
        return this.capacity;
    }
    public String get_type() {return this.type;}
    public User get_owner() {
        return this.owner;
    }
    public int get_registered() {
        return this.registered;
    }
    public boolean has_space() {
        return this.registered < this.capacity;
    }

    public void register () {
        this.registered++;
    } // increment the number of registered students by 1
    public void unregister () {
        this.registered--;
    } // decrement the number of registered students by 1

    @Override
    public void addObserver(Observer o) {observers.add(o);} // add an observer to the list of observers

    @Override
    public void removeObserver(Observer o) {observers.remove(o);} // remove an observer from the list of observers

    //notify all observers
    @Override
    public void notifyObservers(String massage) {
        for (Observer o : observers) {
            o.update(massage);
        }
    }

}
