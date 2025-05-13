package DAY_2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class UserValidationUtil {

    public static boolean isValidUsername(String username) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher matcher = emailPattern.matcher(username);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String username = "johndoe@gmail.com";
        String password = "Passw0rd@123";

        if (isValidUsername(username)) {
            System.out.println("Username is valid.");
        } else {
            System.out.println("Invalid username format.");
        }

        if (isValidPassword(password)) {
            System.out.println("Password is valid.");
        } else {
            System.out.println("Invalid password format.");
        }
    }
}
// Output:
// Username is valid.
// Password is valid.
// The UserValidationUtil class provides methods to validate usernames and passwords.
