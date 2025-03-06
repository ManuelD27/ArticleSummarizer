import java.util.Scanner;

/**
 * Main program that lets you paste text
 */
public class Main {
    public static void main(String[] args) {
        // Create our helper that collects text
        ArticleReader reader = new ArticleReader();

        // Get text from the user
        String userArticle = reader.getArticle();

        // Show what was entered
        System.out.println("\nYou entered:\n" + userArticle);
        System.out.println("\nProcessing your text...");

        // Here you would add your text processing code
    }
}



