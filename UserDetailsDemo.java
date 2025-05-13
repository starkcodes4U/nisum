package DAY_2;

class UserDetails {
    public String name;
    public String id;
    public String email;

    private String password;
    private String creditCard;
    private double creditCardBalance;

    UserDetails(String name, String id, String email, String password, String creditCard, double creditCardBalance) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.password = password;
        this.creditCard = creditCard;
        this.creditCardBalance = creditCardBalance;
    }

    void showUserInfo() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Email: " + email);
    }

    String getMaskedCard() {
        int len = creditCard.length();
        return "XXXX-XXXX-XXXX-" + creditCard.substring(len - 4);
    }

    void showCardBalance(String enteredPassword) {
        if (this.password.equals(enteredPassword)) {
            System.out.println("Credit Card Balance: ₹" + creditCardBalance);
        } else {
            System.out.println("Access Denied: Incorrect Password");
        }
    }
}

public class UserDetailsDemo {
    public static void main(String[] args) {
        UserDetails user = new UserDetails(
            "Jagannath Tripathy",
            "22053601",
            "22053601@kiit.ac.in",
            "mypassword123",
            "9876543210987654",
            40000.00
        );

        user.showUserInfo();
        System.out.println("Credit Card (masked): " + user.getMaskedCard());
        user.showCardBalance("mypassword123"); // Correct password
        user.showCardBalance("wrongpass");     // Incorrect password
    }
}

// Output:
// Name: Jagannath Tripathy
// ID: 22053601
// Email:
