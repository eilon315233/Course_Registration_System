import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//This class is the parent class of the Registration_System class
public class Registration_System {

    static final int  CAPACITY = 100; // maximum number of students or lecturers or practitioners


    //variables
    private List <User> users; // list of users
    private List<Course> courses; // list of courses
    private static Registration_System instance = null; // instance of the class

    //constructor
    public Registration_System(){
        users = new ArrayList <User> ();
        courses = new ArrayList <Course> ();
    }

    //methods

    // this method use Singleton design pattern to create only one instance of the class
    public static Registration_System getInstance(){
        if (instance == null){
            instance = new Registration_System();
        }
        return instance;
    }

    public List<User> getUsers(){
        return users;
    }

    // this method creates a user and adds it to the list of users
    public User createUser (String type, String name, String ID, String password){

        // check if the number of users is less than the maximum capacity
        if (users.size() < CAPACITY){
            if (getUserByID(ID) != null){
                System.out.println("This ID is already in use.");
                return null;
            }
            // create a user based on the factory method design pattern
            User user = UserFactory.createUser(type, name, ID, password);
            users.add(user); // add the user to the list of users
            return user;
        }
        // if the number of users is equal to the maximum capacity, print a message
        System.out.println("We are sorry, there is no more space in the system");
        return null;
    }

    // this method checks if the user is in the list of users based on the ID
    public User getUserByID(String ID){
        for (User user : users){
            if (user.getId().equals(ID)){
                return user;
            }
        }
        return null;
    }

    // this method checks if the course is in the list of courses based on the ID
    public Course isCourseInCart(String courseID){
        for (Course course : courses){
            if (course.get_id().equals(courseID)){
                return course;
            }
        }
        return null;
    }

    // this method adds a course to the list of courses
    public Course defineCourse (User user, String type, String name, String ID, int capacity)
    {
        // check if the user is a lecturer or a practitioner
        if(user instanceof Lecturer || user instanceof Practitioner){

            // check if the course is already in the system
            if (isCourseInCart(ID) != null){
                System.out.println("This course is already in system.");
                return null;
            }

            // create a course based on the factory method design pattern
            Course course = CourseFactory.createCourse(user, name, ID, capacity, type);
            courses.add(course);
            return course;
        }
        // The user is Student
        else {
            System.out.println("You are not allowed to add a course.");
            return null;
        }
    }

    // this method prints all the courses in the system
    public void printCourses(){
        if (courses.isEmpty()){
            System.out.println("There is no course in the system.");
            return;
        }
        System.out.println("Courses in the system:");
        for (Course course : courses){
            System.out.println("Name: " + course.get_name() + ", ID: " + course.get_id() + ", Capacity: " + course.get_capacity() + ".");
        }
    }





}
