/*
  This class is a factory class that creates different types of courses based on the type of course,
  using the factory method design pattern
*/
public class CourseFactory {
    public  static Course createCourse(User owner, String name, String id, int capacity, String type) {
        switch (type) {
            case "chosenCourse":
                return new chosenCourse(owner, name, id, capacity);
            case "requiredCourse":
                return new requiredCourse(owner, name, id, capacity);
            case "seminarCourse":
                return new seminarCourse(owner, name, id, capacity);
            default:
                throw new IllegalArgumentException("Invalid course type: " + type); // if the type is not valid, throw an exception
        }
    }
}
