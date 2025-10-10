import java.util.Scanner;
import mypackage.Operations;


public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Operations ops = new Operations();  // Create an instance of Operations class

        while (true) {
            // Display the menu
            System.out.println("\n---- MENU: STRING & NUMBER OPERATIONS ----\n");
            System.out.println("1. Count Unique Palindromic Substrings");
            System.out.println("2. Nth Fibonacci Number");
            System.out.println("3. Convert Snake Case to Camel Case");
            System.out.println("4. Count Consonants in a String");
            System.out.println("5. Binary to Decimal Conversion");
            System.out.println("6. Expand Characters Based on Digits (e.g. a1b2 → abb)");
            System.out.println("7. Character Frequency in a String");
            System.out.println("8. Prime Number Checker");
            System.out.println("9. Convert Number to Words");
            System.out.println("10. Longest Substring Without Repeating Characters");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine(); // consume newline character

            switch (choice) {
                // Exit program
                case 0:
                    System.out.println("Exiting program... Thank you!");
                    input.close();
                    return;

                // 1. Count Unique Palindromic Substrings
                case 1:
                    System.out.print("Enter a string: ");
                    String palindromeInput = input.nextLine();
                    int palindromeCount = ops.countUniquePalindromes(palindromeInput);
                    System.out.println("Unique palindromic substrings: " + palindromeCount);
                    break;

                // 2. Nth Fibonacci Number
                case 2:
                    System.out.print("Enter N: ");
                    int n = input.nextInt();
                    System.out.println("Nth Fibonacci number: " + ops.getNthFibonacci(n));
                    break;

                // 3. Convert Snake Case to Camel Case
                case 3:
                    System.out.print("Enter a snake_case string: ");
                    String snakeCase = input.nextLine();
                    System.out.println("CamelCase: " + Operations.snakeToCamel(snakeCase));
                    break;

                // 4. Count Consonants in a String
                case 4:
                    System.out.print("Enter a string: ");
                    String consonantInput = input.nextLine();
                    int consonantCount = Operations.countConsonants(consonantInput);
                    System.out.println("Number of consonants: " + consonantCount);
                    break;

                // 5. Binary to Decimal Conversion
                case 5:
                    System.out.print("Enter a binary string: ");
                    String binary = input.nextLine();
                    int decimal = Operations.binaryToDecimal(binary);
                    System.out.println("Decimal equivalent: " + decimal);
                    break;

                // 6. Expand Characters Based on Digits
                case 6:
                    System.out.print("Enter string with characters followed by digits (e.g. a1b2c3): ");
                    String expandInput = input.nextLine();
                    System.out.println("Expanded string: " + Operations.expandCharacters(expandInput));
                    break;

                // 7. Character Frequency in a String
                case 7:
                    System.out.print("Enter a string: ");
                    String freqInput = input.nextLine();
                    System.out.println("Character frequency: " + Operations.charFrequency(freqInput));
                    break;

                // 8. Prime Number Checker
                case 8:
                    System.out.print("Enter a number: ");
                    int num = input.nextInt();
                    boolean prime = Operations.isPrime(num);
                    System.out.println(num + (prime ? " is PRIME" : " is NOT PRIME"));
                    break;

                // 9. Convert Number to Words
                case 9:
                    System.out.print("Enter a number: ");
                    int numWords = input.nextInt();
                    System.out.println("Number in words: " + Operations.numberToWords(numWords));
                    break;

                // 10. Longest Substring Without Repeating Characters
                case 10:
                    System.out.print("Enter a string: ");
                    String substringInput = input.nextLine();
                    int maxLength = Operations.longestUniqueSubstring(substringInput);
                    System.out.println("Length of longest substring without repeating characters: " + maxLength);
                    break;

                // Invalid choice handling
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
