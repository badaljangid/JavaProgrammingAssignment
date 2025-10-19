
/**
 * @filename - Main.java
 * @Descripiton - This will perform most of the string operation : (e.g- uniquePalindromesCounter, getnthFibonacci, 
 *                                                                       convertSnakeToCamel, countConsonants, binaryToDecimal, 
 *                                                                       expandCharacter, getCharFrequency, isPrime, numberToWords,
 *                                                                       lengthOfLongestSubstring)
 * @author - Badal Jangid
 */
import java.util.InputMismatchException;
import java.util.Scanner;
import mypackage.Operations;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Operations ops = new Operations();

        while (true) {
            System.out.println("\n---- MENU: STRING & NUMBER OPERATIONS ----\n");
            System.out.println("1. Count Unique Palindromic Substrings");
            System.out.println("2. Nth Fibonacci Number (1..46)");
            System.out.println("3. Convert Snake Case to Camel Case");
            System.out.println("4. Count Consonants in a String");
            System.out.println("5. Binary to Decimal Conversion (only 0/1 allowed)");
            System.out.println("6. Expand Characters Based on Digits (e.g. a1b2 → abb)");
            System.out.println("7. Character Frequency in a String");
            System.out.println("8. Prime Number Checker (>=2)");
            System.out.println("9. Convert Number to Words (-9999..9999)");
            System.out.println("10. Longest Substring Without Repeating Characters");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = input.nextInt();
                input.nextLine(); // consume newline
            } catch (InputMismatchException e) {
                System.out.println("Wrong input! Try again.");
                input.nextLine(); // clear bad token
                continue;
            }

            switch (choice) {
                case 0:
                    System.out.print("Are you sure you want to exit? (y/n): ");
                    String confirm = input.nextLine().trim().toLowerCase();
                    if (confirm.equals("y") || confirm.equals("yes")) {
                        System.out.println("Exiting program... Thank you!");
                        input.close();
                        return;
                    } else {
                        System.out.println("Exit cancelled. Returning to menu...");
                    }
                    break;

                case 1:
                    System.out.print("Enter a string: ");
                    String palindromeInput = input.nextLine();
                    if (palindromeInput == null) {
                        System.out.println("Wrong input! Try again.");
                        break;
                    }
                    int palindromeCount = ops.countUniquePalindromes(palindromeInput);
                    System.out.println("Unique palindromic substrings: " + palindromeCount);
                    break;

                case 2:
                    System.out.print("Enter N (1..46): ");
                    try {
                        int n = Integer.parseInt(input.nextLine().trim());
                        int fib = ops.getNthFibonacci(n);
                        if (fib == Integer.MIN_VALUE) {
                            System.out.println("Wrong input! Try again.");
                        } else {
                            System.out.println("Nth Fibonacci number: " + fib);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input! Try again.");
                    }
                    break;

                case 3:
                    System.out.print("Enter a snake_case string: ");
                    String snakeCase = input.nextLine();
                    if (snakeCase == null) {
                        System.out.println("Wrong input! Try again.");
                        break;
                    }
                    System.out.println("CamelCase: " + Operations.snakeToCamel(snakeCase));
                    break;

                case 4:
                    System.out.print("Enter a string: ");
                    String consonantInput = input.nextLine();
                    if (consonantInput == null) {
                        System.out.println("Wrong input! Try again.");
                        break;
                    }
                    int consonantCount = Operations.countConsonants(consonantInput);
                    System.out.println("Number of consonants: " + consonantCount);
                    break;

                case 5:
                    System.out.print("Enter a binary string (only 0 and 1): ");
                    String binary = input.nextLine();
                    int decimal = Operations.binaryToDecimal(binary);
                    if (decimal == Integer.MIN_VALUE) {
                        System.out.println("Wrong input! Try again.");
                    } else {
                        System.out.println("Decimal equivalent: " + decimal);
                    }
                    break;

                case 6:
                    System.out.print("Enter string with characters followed by digits (e.g. a1b2c3): ");
                    String expandInput = input.nextLine();
                    if (expandInput == null) {
                        System.out.println("Wrong input! Try again.");
                        break;
                    }
                    System.out.println("Expanded string: " + Operations.expandCharacters(expandInput));
                    break;

                case 7:
                    System.out.print("Enter a string: ");
                    String freqInput = input.nextLine();
                    if (freqInput == null) {
                        System.out.println("Wrong input! Try again.");
                        break;
                    }
                    System.out.println("Character frequency: " + Operations.charFrequency(freqInput));
                    break;

                case 8:
                    System.out.print("Enter a number (>=2): ");
                    try {
                        int num = Integer.parseInt(input.nextLine().trim());
                        if (num < 2) {
                            System.out.println("Wrong input! Try again.");
                        } else {
                            boolean prime = Operations.isPrime(num);
                            System.out.println(num + (prime ? " is PRIME" : " is NOT PRIME"));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input! Try again.");
                    }
                    break;

                case 9:
                    System.out.print("Enter a number (-9999..9999): ");
                    try {
                        int number = Integer.parseInt(input.nextLine().trim());
                        if (number < -9999 || number > 9999) {
                            System.out.println("Wrong input! Try again.");
                        } else {
                            System.out.println("Number in words: " + Operations.numberToWords(number));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input! Try again.");
                    }
                    break;

                case 10:
                    System.out.print("Enter a string: ");
                    String substringInput = input.nextLine();
                    if (substringInput == null) {
                        System.out.println("Wrong input! Try again.");
                        break;
                    }
                    int maxLength = Operations.longestUniqueSubstringWithOutput(substringInput);
                    System.out.println("Length of longest substring without repeating characters: " + maxLength);
                    break;

                default:
                    System.out.println("Wrong input! Try again.");
            }
        }
    }
}
