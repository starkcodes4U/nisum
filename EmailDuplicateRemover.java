package DAY_3;

import java.util.HashSet;
import java.util.Scanner;

public class EmailDuplicateRemover {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<String> emailSet = new HashSet<>();
        int choice;

        do {
            System.out.println("\n1. Add Email");
            System.out.println("2. Display Unique Emails");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter email address: ");
                    String email = sc.nextLine().toLowerCase();
                    if (emailSet.add(email)) {
                        System.out.println("Email added.");
                    } else {
                        System.out.println("Duplicate email. Already exists.");
                    }
                    break;

                case 2:
                    if (emailSet.isEmpty()) {
                        System.out.println("No emails in the list.");
                    } else {
                        System.out.println("Unique Emails:");
                        for (String e : emailSet) {
                            System.out.println(e);
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 3);

        sc.close();
    }
}
