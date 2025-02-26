import java.util.Scanner;

public class Main {

    // Function to get article from the user
    public static String getArticle() {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object to read user input
        StringBuilder article = new StringBuilder(); // Use StringBuilder to accumulate the article text
        String line; // Variable to store each line of input

        System.out.println("Please enter your article (type 'END' on a new line to finish):");

        while (true) {
            line = scanner.nextLine(); // Read a line of input from the user
            if (line.trim().equalsIgnoreCase("END")) { // Check if the user typed 'END'
                break; // Exit the loop if 'END' is typed
            }
            if (line.trim().isEmpty()) { // Check if the line is empty
                System.out.println("The line cannot be empty. Please enter some text or type 'END' to finish.");
            } else {
                article.append(line).append("\n"); // Append the line to the article with a newline character
            }
        }

        scanner.close(); // Close the Scanner to free up resources
        return article.toString().trim(); // Return the accumulated article as a string, trimming any trailing whitespace
    }

    public static void main(String[] args) {
        String userArticle = getArticle(); // Call the function to get the article from the user
        System.out.println("You entered:\n" + userArticle); // Print the user's article
    }
}
