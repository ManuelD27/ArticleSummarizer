import java.util.Scanner;

/**
 * This class is used to collect user inputs to summarize.
 */
public class ArticleReader {

    public String getArticle() {
        StringBuilder article = new StringBuilder(); // Create a StringBuilder to efficiently build our article text piece by piece

        System.out.println("Welcome to the Content Analyzer, You can enter text, paste URLs, or provide PDF or DOC files!"); // This shows a message in terminal
        System.out.println("Please enter your article (press enter twice on keyboard to finish):"); // This shows a message in terminal
        System.out.println("Note: Random text or gibberish will not be accepted.\n"); // Warning about random text

        try (Scanner scanner = new Scanner(System.in)) { // This is like a magic ear that listens to what you type
            String line; // This is like a small box to hold each line you type
            boolean lastLineEmpty = false; // Flag to check if the last line was empty

            while (scanner.hasNextLine()) { // Keep reading as long as there's input available
                line = scanner.nextLine(); // Listen to what you typed and put it in our box

                if (line.trim().isEmpty()) { // If you didn't type anything (just pressed Enter)
                    if (lastLineEmpty) { // If the last line was also empty
                        break; // Stop listening for more text - user pressed Enter twice
                    }
                    lastLineEmpty = true; // Remember that this line was empty
                    article.append("\n"); // Still add the newline to preserve formatting
                } else {
                    // Check if the line looks like random text
                    if (isRandomText(line)) {
                        System.out.println("This looks like random text. Please enter a proper sentence:");
                        continue; // Skip this line and ask for new input
                    }

                    lastLineEmpty = false; // Reset the flag since this line has content
                    article.append(line).append("\n"); // Add line to the article with newline
                }
            }
        } catch (Exception e) { // If something goes wrong (like our computer gets confused)
            System.err.println("An error occurred while reading input: " + e.getMessage()); // Tell us what went wrong
        }

        return article.toString().trim(); // Give back all the words you typed, without extra spaces at the end
    }

    /**
     * This function checks if text looks random/suspicious rather than like a proper sentence
     */
    private boolean isRandomText(String text) {
        // Check for null or empty text
        if (text == null || text.trim().isEmpty()) {
            return true; // Empty text is considered random
        }

        // These counters keep track of different character types
        int letters = 0;
        int numbers = 0;
        int specials = 0;

        // This goes through each character and counts them
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                letters++;
            } else if (Character.isDigit(c)) {
                numbers++;
            } else if (!Character.isWhitespace(c)) {
                specials++;
            }
        }

        // Add up all non-space characters
        int total = letters + numbers + specials;

        // If input is super short, it's probably not a real sentence
        if (total < 3) {
            return true; // Yes, it's random
        }

        // Real sentences are mostly letters - if less than half are letters, this means it's not a real sentence
        if (letters < total * 0.5) {
            return true; // Yes, it's random
        }

        // This checks if input starts with a capital letter and ends with proper punctuation
        String trimmedText = text.trim();
        if (trimmedText.isEmpty()) {
            return true;
        }

        boolean startsWithCapital = Character.isUpperCase(trimmedText.charAt(0));
        boolean endsWithPunctuation = ".!?".contains(String.valueOf(trimmedText.charAt(trimmedText.length() - 1)));

        // If it's missing BOTH proper capitalization AND ending punctuation, it's probably random
        if (!startsWithCapital && !endsWithPunctuation) {
            return true; // Yes, it's random
        }

        // If it passed all our tests, it's probably a normal sentence
        return false;
    }
}
