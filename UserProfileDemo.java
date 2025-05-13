package DAY_2;

class UserProfile {
    String name;
    int age;
    String email;

    UserProfile(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    void displayProfile() {
        System.out.println("User Profile:");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
    }
}

public class UserProfileDemo {
    public static void main(String[] args) {
        UserProfile user = new UserProfile("Jagannath Tripathy", 22, "22053601@kiit.ac.in");
        user.displayProfile();
    }
}

    

