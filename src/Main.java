import java.util.Scanner;
import java.lang.*;
import static java.lang.System.exit;

//This class is the main class of the system
public class Main {
    public static void main(String[]args) {
        Registration_System s = Registration_System.getInstance(); // create an instance of the Registration_System
        startSystem(s); // start work the registration system
    }

    //This method is used to start the system and display the main menu
    public static void startSystem(Registration_System s) {
        System.out.println("--------------------------------------------");
        System.out.println("Welcome to the Registration System");
        System.out.println("Please select an option:");
        System.out.println("1. Login as a existing user");
        System.out.println("2. Register as a new user");
        System.out.println("9. To exit the system");

        Scanner scanner = new Scanner(System.in);
        String option = scanner.next(); // get the user's choice

        switch (option) {
            case "9":
                System.out.println("Thank you for using the Registration System");
                return;
            case "2":
                System.out.println("Please enter your name:");
                String nameUser = scanner.next();
                System.out.println("Please enter your ID:");
                String id = scanner.next();
                // check if the user with this ID already exists
                if (s.getUserByID(id) != null) {
                    System.out.println("User with this ID already exists, please try again");
                    startSystem(s);
                    return;
                }
                System.out.println("Please enter your password:");
                String password = scanner.next();
                System.out.println("Please enter your type:");
                System.out.println("1. Practitioner");
                System.out.println("2. Student");
                System.out.println("3. Lecturer");
                java.lang.String type = scanner.next();
                switch (type) {
                    case "1":
                        type = "Practitioner";
                        break;
                    case "2":
                        type = "Student";
                        break;
                    case "3":
                        type = "Lecturer";
                        break;
                    default:
                        System.out.println("Invalid type, please try again"); // if the user entered an invalid type
                        startSystem(s);
                        return;
                }
                // create a new user by the factory design pattern
                User newUser = s.createUser(type, nameUser, id, password);
                System.out.println("User created successfully");
                loginInUser(s, newUser); // login the new user and display the user menu
                break;

            case "1":
                System.out.println("Please enter your ID:");
                String userID = scanner.next();
                User user = s.getUserByID(userID);
                // check if the user with this ID exists
                if (user == null) {
                    System.out.println("User with this ID does not exist, please try again");
                    startSystem(s);
                    return;
                }
                System.out.println("Please enter your password:");
                String userPassword = scanner.next();
                // check if the password is correct
                if (user.getPassword().equals(userPassword)) {
                    loginInUser(s, user);
                } else {
                    System.out.println("Incorrect password, please try again");
                    startSystem(s);
                }
                break;
            default:
                System.out.println("Invalid option, please try again"); // if the user entered an invalid option
                startSystem(s);
                break;
        }
    }

    //This method is used to display the user menu based on the type of the user
    public static void loginInUser(Registration_System s, User user) {
        // check if the user is a student
        if (user instanceof Student) {
            Student student = (Student) user;
             studentUserMenu(s, student); // display the student menu
        }
        else { otherUserMenu(s, user);} // display the other user menu
    }

    //This method is used to display the student menu
    public static void studentUserMenu(Registration_System s, Student student) {
        System.out.println("--------------------------------------------");
        System.out.println("Hello " + student.getName() + "!");
        System.out.println("Please select an option :");
        System.out.println("1. Register to a course");
        System.out.println("2. View all your courses");
        System.out.println("3. Remove a course");
        System.out.println("4. To show your details");
        System.out.println("5. To show all the courses in the system");
        System.out.println("6. To return to the main menu");
        System.out.println("9. To exit the system");

        Scanner scanner = new Scanner(System.in);
        String option = scanner.next(); // get the user's choice

        switch (option) {
            case "1":
                System.out.println("Please enter the ID of the course you want to register to:");
                String courseID = scanner.next();
                Course course = s.isCourseInCart(courseID); // check if the course with this ID exists
                if (course == null) {
                    System.out.println("Course with this ID does not exist, please try again");
                    studentUserMenu(s, student);
                    return;
                }
                // The course is exist in the system and the student can register to it
                student.addCourseToCourses(course);
                studentUserMenu(s, student);
                break;
            case "2":
                student.viewCourses(); // display all the courses that the student registered to
                studentUserMenu(s, student);
                break;
            case "3":
                System.out.println("Please enter the ID of the course you want to remove:");
                String courseIDToRemove = scanner.next();
                Course courseToRemove = student.isCourseInCourses(courseIDToRemove); // check if the course with this ID exists in the student's courses
                if (courseToRemove == null) {
                    System.out.println("Course with this ID does not exist in your courses, please try again");
                    studentUserMenu(s, student);
                    return;
                }
                // The course is exist in the student's courses and the student can remove it
                student.removeCourseFromCourses(courseToRemove);
                studentUserMenu(s, student);
                break;
            case "4":
                student.printDetails(); // display the student's details
                studentUserMenu(s, student);
                break;
            case "5":
                s.printCourses(); // display all the courses in the system
                studentUserMenu(s, student);
                break;
            case "6":
                startSystem(s);
                break;
            case "9":
                System.out.println("Thank you for using the Registration System");
                exit(0);
                break;

            default:
                System.out.println("Invalid option, please try again"); // if the user entered an invalid option
                studentUserMenu(s, student);
                break;
        }
    }

//This method is used to display the other user menu
public static void otherUserMenu(Registration_System s, User user) {
        System.out.println("--------------------------------------------");
        System.out.println("Hello " + user.getName() + "!");
        System.out.println("Please select an option :");
        System.out.println("1. Define a course");
        System.out.println("2. View your details");
        System.out.println("3. To show all the courses in the system");
        System.out.println("4. To return to the main menu");
        System.out.println("9. To exit the system");

        Scanner scanner = new Scanner(System.in);
        String option = scanner.next();
        switch (option) {
            case "1":
                System.out.println("Please enter the name of the course:");
                String courseName = scanner.next();
                System.out.println("Please enter the ID of the course:");
                String courseID = scanner.next();
                // check if the course with this ID already exists
                if (s.isCourseInCart(courseID) != null) {
                    System.out.println("Course with this ID already exists, please try again");
                    otherUserMenu(s, user);
                    return;
                }
                System.out.println("Please enter the capacity of the course:");
                int courseCapacity = scanner.nextInt();
                System.out.println("Please enter the type of the course:");
                System.out.println("1. chosenCourse");
                System.out.println("2. requiredCourse");
                System.out.println("3. seminarCourse");
                String courseType = scanner.next();
                switch (courseType) {
                    case "1":
                        courseType = "chosenCourse";
                        break;
                    case "2":
                        courseType = "requiredCourse";
                        break;
                    case "3":
                        courseType = "seminarCourse";
                        break;

                    default:
                        System.out.println("Invalid type, please try again");// if the user entered an invalid type
                        otherUserMenu(s, user);
                        return;
                }
                // define a new course by the factory design pattern
                Course newCourse = s.defineCourse(user, courseType, courseName, courseID, courseCapacity);
                if (newCourse != null) {
                    System.out.println("Course defined successfully");
                }
                otherUserMenu(s, user);
                break;
            case "2":
                user.printDetails(); // display the user's details
                otherUserMenu(s, user);
                break;
            case "3":
                s.printCourses(); // display all the courses in the system
                otherUserMenu(s, user);
                break;
            case "4":
                startSystem(s);
                break;
            case "9":
                System.out.println("Thank you for using the Registration System");
                exit(0);
                break;
            default:
                System.out.println("Invalid option, please try again");
                otherUserMenu(s, user);
                break;
        }
        startSystem(s);
    }




}