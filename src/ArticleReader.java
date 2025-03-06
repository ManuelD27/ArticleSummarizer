import java.util.Scanner;

/**
 * This class is used to collect user inputs to summarize.
 */
public class ArticleReader {

    /**
     * This method asks for and collects your story
     * @return The story you typed
     */
    public String getArticle() {

        StringBuilder article = new StringBuilder(); // Create a StringBuilder to efficiently build our article text piece by piece

        System.out.println("Please enter your article (press enter twice on keyboard to finish):"); // This shows a message in terminal

        try (Scanner scanner = new Scanner(System.in)) { // This is like a magic ear that listens to what you type

            String line; // This is like a small box to hold each line you type
            boolean lastLineEmpty = false; // Flag to check if the last line was empty

            while (true) { // This means "keep doing this forever until I say stop"

                line = scanner.nextLine(); // Listen to what you typed and put it in our box

                if (line.trim().isEmpty()) { // If you didn't type anything (just press Enter)
                    if (lastLineEmpty) { // If the last line was also empty
                        break; // Stop listening for more text.
                    } else {
                        lastLineEmpty = true; // Set the flag to true
                    }
                } else {
                    lastLineEmpty = false; // Reset the flag
                    article.append(line).append("\n"); // Adds line to the article. The "\n" is like pressing Enter on your keyboard.
                }
            }

        } catch (Exception e) { // If something goes wrong (like our computer gets confused)
            System.err.println("An error occurred while reading input: " + e.getMessage()); // Tell us what went wrong
        }

        return article.toString().trim(); // Give back all the words you typed, without extra spaces at the end
    }
}