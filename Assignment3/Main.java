package mypackage;

import java.util.Scanner;

/**
 * @filename - Main.java
 * @description - Entry point for the 2048 console game.
 *                Handles user inputs (W, A, S, D), updates the board using Logic class methods,
 *                and manages the overall game flow until the game is over.
 * @author - Badal Jangid
 */
public class Main {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        // Initialize game board
        int[][] mat = Logic.startGame();
        Logic.printMatrix(mat);

        while (true) {
            System.out.print("Press the command: ");
            String x = inputScanner.nextLine();

            boolean validMove = true;
            Object[] moveResult = null;

            // Handle movement input
            switch (x.toLowerCase()) {
                case "w":
                    moveResult = Logic.moveUp(mat);
                    break;
                case "s":
                    moveResult = Logic.moveDown(mat);
                    break;
                case "a":
                    moveResult = Logic.moveLeft(mat);
                    break;
                case "d":
                    moveResult = Logic.moveRight(mat);
                    break;
                default:
                    System.out.println("❌ Invalid key! Use W, A, S, or D.");
                    validMove = false;
            }

            if (validMove && moveResult != null) {
                mat = (int[][]) moveResult[0];
                String status = Logic.getCurrentState(mat);

                if (status.equals("GAME NOT OVER")) {
                    Logic.addNew2(mat);
                    Logic.printMatrix(mat);
                } else {
                    Logic.printMatrix(mat);
                    System.out.println("🎮 " + status);
                    break;
                }
            }
        }

        inputScanner.close();
    }
}
