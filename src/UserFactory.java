// This class is a factory class that creates a user object based on the Factory Method design pattern.
public class UserFactory {
    public static User createUser(String type, String name, String id, String password) {
        switch (type) {
            case "Student":
                return new Student(name, id, password);
            case "Lecturer":
                return new Lecturer(name, id, password);
            case "Practitioner":
                return new Practitioner(name, id, password);
            default:
                throw new IllegalArgumentException("Invalid user type: " + type);

        }
    }
}
