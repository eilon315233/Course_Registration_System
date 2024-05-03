import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This class represents the Student class which is a subclass of the User
public class Student extends User implements Observer{
    private List<Course> courses; // list of courses that the student is registered in
    private final StudentFacade studentFacade = new StudentFacade();

    //constructor
    public Student(String name, String id, String password){
        super(name, id, password);
        courses = new ArrayList<Course>();
    }

    //methods
    public String getName(){
        return super.getName();
    }
    public String getID(){
        return super.getId();
    }
    public String getPassword(){
        return super.getPassword();
    }
    public List<Course> getCourses(){
        return courses;
    }

    //This method prints the details of the Student.
    @Override
    void printDetails() {
        System.out.println("Student: " + this.getName());
        System.out.println("ID: " + this.getID());
        System.out.println("Courses: ");
        for (Course course : courses){
            System.out.println(course.get_name());
        }
    }

    //This method checks if the course is in the list of courses based on the ID
    public Course isCourseInCourses(String courseID){
        for (Course course : courses){
            if (course.get_id().equals(courseID)){
                return course;
            }
        }
        return null;
    }

    //This method prints a message to the student based on the observer design pattern
    @Override
    public void update(String message) {
        System.out.println("notification to "+ this.getName() +": " + message);
    }


    /*
            ***These methods implement the Facade design pattern***
     */

    //This method adds a course to the list of courses of the student
    public void addCourseToCourses(Course course){ studentFacade.addCourseToCourses(course, this);}

    //This method removes a course from the list of courses
    public void removeCourseFromCourses(Course course){studentFacade.removeCourseFromCourses(course, this);}

    //This method prints the courses that the student is registered in
    public void viewCourses(){ studentFacade.viewCourses(this);}

}
