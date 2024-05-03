import java.util.Scanner;

/*
    * This class represents the StudentFacade class which is a facade class for the Student class.
    * It implements the methods that are related to the Student class.
 */
public class StudentFacade {

    //This method adds a course to the list of courses of the student
    public void addCourseToCourses(Course course, Student student){
        // check if the course is already in the list of courses
        if (student.getCourses().contains(course)){
            System.out.println("This course is already in your courses.");
            return;
        }
        // check if the course has space to add the student
        if (course.has_space() == false){
            System.out.println("This capacity of this course is full. You cannot add it to your courses.");
            System.out.println("Do you we message you when there is a free place? (yes/no)");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            switch (answer){
                case "yes":
                    course.addObserver(student); // add the student to the list of observers
                    System.out.println("We added you to the notification list, we will message you when there is a free place.");
                    break;
                case "no":
                    break;
                default:
                    System.out.println("Invalid input, we will not message you."); // if the input is not yes or no
                    break;
            }
            return;
        }
        student.getCourses().add(course); // add the course to the list of courses
        course.register(); // update the number of registered students in the course
        System.out.println("We added the course to your courses.");
    }

    //This method removes a course from the list of courses of the student
    public void removeCourseFromCourses(Course course, Student student){
        // check if the course is in the list of courses
        if (student.getCourses().contains(course))
        {
            student.getCourses().remove(course); // remove the course from the list of courses
            System.out.println("We removed the course from your courses.");
            System.out.println("");

            // check if now there is a free place in the course and notify the observers
            if (course.get_capacity() == course.get_registered())
            {
                course.notifyObservers("someone has left the course, and now there is a free place.");
            }
            course.unregister(); // update the number of registered students in the course
        }
        // if the course is not in the list of courses
        else{ System.out.println("This course is not in your courses."); }
    }

    //This method prints the courses that the student is registered in
    public void viewCourses(Student student){
        System.out.println("Your courses: ");
        for (Course course : student.getCourses()){
            System.out.println(course.get_name());
        }
    }

}
