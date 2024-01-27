import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Exam {
    static String username = "";
    static String password = "";
    static String profileInfo = "No additional profile information";
    static boolean isLoggedIn = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (!isLoggedIn) {
                displayLoginMenu();
            } else {
                displayMainMenu();
            }

            int choice = scanner.nextInt();

            if (!isLoggedIn) {
                switch (choice) {
                    case 1:
                        createAccount(scanner);
                        break;
                    case 2:
                        isLoggedIn = login(scanner);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } else {
                switch (choice) {
                    case 1:
                        updateProfileAndPassword(scanner);
                        break;
                    case 2:
                        takeMCQQuiz(scanner);
                        break;
                    case 3:
                        System.out.println("Closing session and Logout");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            }
        }
    }

    public static void displayLoginMenu() {
        System.out.println("Enter a number between 1 and 2:");
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.print("Your choice: ");
    }

    public static void displayMainMenu() {
        System.out.println("Enter a number between 1 and 3:");
        System.out.println("1. Update Profile and Password");
        System.out.println("2. Take MCQ Quiz");
        System.out.println("3. Closing session and Logout");
        System.out.println("\n");
        System.out.print("Your choice: ");
    }

    public static void createAccount(Scanner scanner) {
        System.out.print("Enter a username: ");
        String newUsername = scanner.next();

        System.out.print("Enter a password (8-15 characters, 1 capital, 1 special character): ");
        String newPassword = scanner.next();

        if (isValidPassword(newPassword)) {
            username = newUsername;
            password = newPassword;
            System.out.println("\n");
            System.out.println("Account created successfully!");
            System.out.println("\n");
        } else {
            System.out.println("Invalid password. Please make sure it follows the rules.");
        }
    }

    public static boolean login(Scanner scanner) {
        boolean isLoggedIn = false;

        System.out.print("Enter username: ");
        String inputUsername = scanner.next();

        System.out.print("Enter password: ");
        String inputPassword = scanner.next();

        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            System.out.println("\n");
            System.out.println("Welcome!");
            System.out.println("\n");
            isLoggedIn = true;
        } else {
            System.out.println("Wrong username or password. Please try again.");
        }

        return isLoggedIn;
    }

    public static void updateProfileAndPassword(Scanner scanner) {
        System.out.println("Current Profile Information: " + profileInfo);
        System.out.print("Do you want to update your profile information? (yes/no): ");
        String response = scanner.next();

        if (response.equalsIgnoreCase("yes")) {
            System.out.print("Enter new profile information: ");
            scanner.nextLine();
            String newProfileInfo = scanner.nextLine();
            profileInfo = newProfileInfo;
            System.out.println("Profile information updated successfully!");
        }

        System.out.print("Do you want to change your password? (yes/no): ");
        response = scanner.next();

        if (response.equalsIgnoreCase("yes")) {
            System.out.print("Enter your current password: ");
            String currentPassword = scanner.next();

            if (currentPassword.equals(password)) {
                System.out.print("Enter a new password (8-15 characters, 1 capital, 1 special character): ");
                String newPassword = scanner.next();

                if (isValidPassword(newPassword)) {
                    password = newPassword;
                    System.out.println("Password changed successfully!");
                } else {
                    System.out.println("Invalid password. Please make sure it follows the rules.");
                }
            } else {
                System.out.println("Incorrect current password. Password not changed.");
            }
        }

        System.out.println("Profile Name: " + profileInfo);
        System.out.println("Password: " + password);
        System.out.println("Thank you!");
        System.out.println("\n");
    }

    public static boolean isValidPassword(String password) {
       
        return Pattern.matches("^(?=.*[A-Z])(?=.*[!@#$%^&*()])(?=\\S+$).{8,15}$", password);
    }

    public static String generateRandomPassword() {
        Random random = new Random();
        int generatedPassword = 1000 + random.nextInt(9000);
        return Integer.toString(generatedPassword);
    }

    static class MCQQuestion {
        private String question;
        private String[] options;
        private char correctAnswer;
        private int mark;

        public MCQQuestion(String question, String[] options, char correctAnswer, int mark) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
            this.mark = mark;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getOptions() {
            return options;
        }

        public char getCorrectAnswer() {
            return correctAnswer;
        }

        public int getMark() {
            return mark;
        }
    }

    static List<MCQQuestion> mcqQuestions = new ArrayList<>();

    static {
       
        mcqQuestions.add(new MCQQuestion("Who is the Father of Java?", new String[]{"A. James Gosling", "B. Guido Van Rossum", "C. Denis Ritchie", "D. Rasmus Lerdorf"}, 'A', 1));
        System.out.println("\n");
        mcqQuestions.add(new MCQQuestion("What is the capital of France?", new String[]{"A. London", "B. Berlin", "C. Paris", "D. Rome"}, 'C', 1));
        System.out.println("\n");
        mcqQuestions.add(new MCQQuestion("What is the largest planet in our solar system?", new String[]{"A. Venus", "B. Mars", "C. Earth", "D. Jupiter"}, 'D', 1));
        System.out.println("\n");
        mcqQuestions.add(new MCQQuestion("Who wrote the play 'Romeo and Juliet'?", new String[]{"A. William Shakespeare", "B. Charles Dickens", "C. Jane Austen", "D. Leo Tolstoy"}, 'A', 1));
        System.out.println("\n");
        mcqQuestions.add(new MCQQuestion("What is the chemical symbol for gold?", new String[]{"A. Go", "B. Au", "C. Gd", "D. Ag"}, 'B', 1));
        System.out.println("\n");
        mcqQuestions.add(new MCQQuestion("What is the largest mammal on Earth?", new String[]{"A. Elephant", "B. Rhinoceros", "C. Blue Whale", "D. Giraffe"}, 'C', 1));
        System.out.println("\n");
        mcqQuestions.add(new MCQQuestion("What gas do plants absorb from the atmosphere during photosynthesis?", new String[]{"A. Oxygen", "B. Carbon Dioxide", "C. Nitrogen", "D. Hydrogen"}, 'B', 1));
    }

    public static void takeMCQQuiz(Scanner scanner) {
        int totalMarks = 7;
        //int totalQuestions = mcqQuestions.;
        int marksObtained = 0;

        for (MCQQuestion question : mcqQuestions) {
            System.out.println("Answer this question:");
            System.out.println(question.getQuestion());
            for (String option : question.getOptions()) {
                System.out.println(option);
            }

            System.out.print("Your choice: ");
            String userChoice = scanner.next();

            if (userChoice.equalsIgnoreCase(String.valueOf(question.getCorrectAnswer()) )){
                marksObtained += question.getMark();
            }
        }

        System.out.println("Quiz completed.");

        System.out.println("Marks Obtained: " + marksObtained + " / " + totalMarks);
    }
}