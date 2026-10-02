package movieDriver;
import java.util.Scanner;

public class MovieDriver {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        char choice;

        do {
            Movie movie = new Movie();

            System.out.println("Enter the title of a movie: ");
            String title = keyboard.nextLine();
            movie.setTitle(title);

            System.out.println("Enter the rating of the movie: ");
            String rating = keyboard.nextLine();
            movie.setRating(rating);

            System.out.println("Enter the number of tickets sold for this movie: ");
            int soldTickets = keyboard.nextInt();
            movie.setSoldTickets(soldTickets);

            System.out.println(movie.toString());

            keyboard.nextLine();

            System.out.println("Do you want to enter another? (y or n): ");
            String response = keyboard.nextLine();
            choice = response.charAt(0);

        } while (choice == 'y' || choice == 'Y');

        System.out.println("Program finished. Goodbye!");
        keyboard.close();
    }
}