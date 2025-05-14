package DAY_3;

import java.util.Scanner;
import java.util.Stack;

public class BrowserHistoryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<String> history = new Stack<>();
        int choice;

        do {
            System.out.println("\n--- Browser History ---");
            System.out.println("1. Visit New Website");
            System.out.println("2. Go Back");
            System.out.println("3. View Current Page");
            System.out.println("4. View Full History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter website URL: ");
                    String url = sc.nextLine();
                    history.push(url);
                    System.out.println("Visited: " + url);
                    break;

                case 2:
                    if (!history.isEmpty()) {
                        String lastPage = history.pop();
                        System.out.println("Went back from: " + lastPage);
                    } else {
                        System.out.println("No history to go back.");
                    }
                    break;

                case 3:
                    if (!history.isEmpty()) {
                        System.out.println("Current page: " + history.peek());
                    } else {
                        System.out.println("No page visited yet.");
                    }
                    break;

                case 4:
                    if (history.isEmpty()) {
                        System.out.println("History is empty.");
                    } else {
                        System.out.println("Browser History:");
                        for (String site : history) {
                            System.out.println(site);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting browser history.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
