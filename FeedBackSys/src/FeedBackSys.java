import java.util.InputMismatchException;
import java.util.Scanner;

//Custom Exception for comments
class CommentTooLongException extends Exception
{
    public CommentTooLongException(String message)
    {
        //Passes the message to parent class
        super(message);
    }
}

// Feedback Class
class Feedback {
    private int rating;
    private String comment;

    //Method for verifying submitted feedback
    public Feedback(int rating, String comment) throws CommentTooLongException
    {
        //Check if rating is within 1 - 5 range
        if (rating < 1 || rating > 5)
        {
            //Throw error message
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }

        //Check if comment is empty
        if (comment == null || comment.trim().isEmpty())
        {
            //Throw error message
            throw new IllegalArgumentException("Comment cannot be empty.");
        }

        //Check if comment is more than 5 words
        if (comment.split(" ").length > 5)
        {
            // Throw error message
            throw new CommentTooLongException("Comment exceeds word limit of 5.");
        }

        //Set the rating and comments
        this.rating = rating;
        this.comment = comment;
    }
        //Show submitted feedback
        public String toString()
        {
            return "Rating: " + rating + ", Comment: " + comment;
        }
    }

// Main Application Class
public class FeedBackSys
{
    //Init feedback array and set the max feedback to 5
    private Feedback[] feedbackList = new Feedback[5];
    //Init feedback count to 0
    private int feedbackCount = 0; // Tracks the number of feedback entries
    //Init scanner
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        FeedBackSys app = new FeedBackSys();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Submit Feedback\n2. View Feedback\n3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice)
            {
                case 1:
                    app.submitFeedback();
                    break;
                case 2:
                    app.viewFeedback();
                    break;
                case 3:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }

    public void submitFeedback()
    {
        //Check for if the feedback entries are >= 5
        if (feedbackCount >= feedbackList.length)
        {
            System.out.println("Maximum feedback entries reached.");
            return;
        }

        try
        {
            //Enter rating
            System.out.print("Enter rating (1-5): ");
            int rating = scanner.nextInt();
            scanner.nextLine();

            //Enter comment
            System.out.print("Enter comment (max 5 words): ");
            String comment = scanner.nextLine();

            //Submit feedback
            Feedback feedback = new Feedback(rating, comment);
            feedbackList[feedbackCount] = feedback;
            //Add +1 to feedback count
            feedbackCount++;
            //Print a success message for user
            System.out.println("Feedback submitted successfully.");

        } catch (InputMismatchException e)
        {
            //Print error for rating
            System.out.println("Invalid input. Please enter a whole number for the rating.");
            scanner.nextLine();

        } catch (CommentTooLongException e)
        {
            //Print error for comment
            System.out.println(e.getMessage());

        } catch (IllegalArgumentException e)
        {
            //Print error for rating
            System.out.println(e.getMessage());
        }
    }

    public void viewFeedback()
    {
        //Check if there is no feedback then throw message for user and exit method
        if (feedbackCount == 0)
        {
            System.out.println("No feedback submitted yet.");
            return;
        }

        //If there is feedback then show feedback to user
        System.out.println("All Feedbacks:");

        for (int i = 0; i < feedbackCount; i++)
        {
            System.out.println(feedbackList[i]);
        }
    }

}
