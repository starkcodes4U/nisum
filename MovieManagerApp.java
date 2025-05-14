package DAY_3;

import java.util.*;

class Movie {
    String title;
    String director;
    String genre;
    int year;
    double rating;

    Movie(String title, String director, String genre, int year, double rating) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-15s %-10s %-6d %.1f", title, director, genre, year, rating);
    }
}

public class MovieManagerApp {
    static ArrayList<Movie> movies = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n1. Add Movie\n2. Remove Movie\n3. View All Movies\n4. Filter Movies\n5. Sort Movies\n6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addMovie();
                case 2 -> removeMovie();
                case 3 -> displayMovies(movies);
                case 4 -> filterMovies();
                case 5 -> sortMovies();
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    static void addMovie() {
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter director: ");
        String director = sc.nextLine();
        System.out.print("Enter genre: ");
        String genre = sc.nextLine();
        System.out.print("Enter release year: ");
        int year = sc.nextInt();
        System.out.print("Enter rating (0.0 - 10.0): ");
        double rating = sc.nextDouble();
        sc.nextLine();

        movies.add(new Movie(title, director, genre, year, rating));
        System.out.println("Movie added.");
    }

    static void removeMovie() {
        System.out.print("Enter title of movie to remove: ");
        String title = sc.nextLine();
        boolean removed = movies.removeIf(movie -> movie.title.equalsIgnoreCase(title));
        if (removed) {
            System.out.println("Movie removed.");
        } else {
            System.out.println("Movie not found.");
        }
    }

    static void displayMovies(List<Movie> list) {
        if (list.isEmpty()) {
            System.out.println("No movies to display.");
            return;
        }

        System.out.printf("%-20s %-15s %-10s %-6s %-6s\n", "Title", "Director", "Genre", "Year", "Rating");
        System.out.println("---------------------------------------------------------------");
        for (Movie m : list) {
            System.out.println(m);
        }
    }

    static void filterMovies() {
        System.out.println("Filter by: 1. Genre  2. Director  3. Release Year");
        int opt = sc.nextInt();
        sc.nextLine();

        List<Movie> filtered = new ArrayList<>();
        switch (opt) {
            case 1 -> {
                System.out.print("Enter genre: ");
                String genre = sc.nextLine();
                filtered = movies.stream().filter(m -> m.genre.equalsIgnoreCase(genre)).toList();
            }
            case 2 -> {
                System.out.print("Enter director: ");
                String director = sc.nextLine();
                filtered = movies.stream().filter(m -> m.director.equalsIgnoreCase(director)).toList();
            }
            case 3 -> {
                System.out.print("Enter release year: ");
                int year = sc.nextInt();
                filtered = movies.stream().filter(m -> m.year == year).toList();
            }
            default -> System.out.println("Invalid filter option.");
        }

        displayMovies(filtered);
    }

    static void sortMovies() {
        System.out.println("Sort by: 1. Title  2. Year  3. Rating");
        int opt = sc.nextInt();

        switch (opt) {
            case 1 -> movies.sort(Comparator.comparing(m -> m.title.toLowerCase()));
            case 2 -> movies.sort(Comparator.comparingInt(m -> m.year));
            case 3 -> movies.sort((m1, m2) -> Double.compare(m2.rating, m1.rating));
            default -> {
                System.out.println("Invalid sort option.");
                return;
            }
        }

        System.out.println("Movies sorted.");
        displayMovies(movies);
    }
}
