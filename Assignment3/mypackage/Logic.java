package mypackage;

import java.util.Random;

/**
 * @filename - Logic.java
 * @description - Core logic for the 2048 console game.
 *                Contains all movement, merging, and board management methods.
 * 
 * @author -
 * Badal Jangid
 */
public class Logic {

    // Starts the game and returns the initial game board
    public static int[][] startGame() {
        int[][] mat = new int[4][4];

        System.out.println("\nCommands:");
        System.out.println("W / w → Up");
        System.out.println("S / s → Down");
        System.out.println("A / a → Left");
        System.out.println("D / d → Right\n");

        addNew2(mat);
        addNew2(mat); // start with 2 tiles like original 2048
        return mat;
    }

    // Finds an empty cell
    private static int[] findEmpty(int[][] mat) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (mat[i][j] == 0) return new int[]{i, j};
            }
        }
        return null;
    }

    // Adds a new '2' tile to a random empty cell
    public static void addNew2(int[][] mat) {
        Random rand = new Random();

        boolean full = true;
        for (int[] row : mat) {
            for (int val : row) {
                if (val == 0) {
                    full = false;
                    break;
                }
            }
        }
        if (full) return;

        int r, c;
        do {
            r = rand.nextInt(4);
            c = rand.nextInt(4);
        } while (mat[r][c] != 0);

        mat[r][c] = 2;
    }

    // Checks if the game is WON, LOST, or still in progress
    public static String getCurrentState(int[][] mat) {
        // Check win
        for (int[] row : mat) {
            for (int val : row) {
                if (val == 2048) return "WON";
            }
        }

        // Check empty cells
        for (int[] row : mat) {
            for (int val : row) {
                if (val == 0) return "GAME NOT OVER";
            }
        }

        // Check possible merges
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (mat[i][j] == mat[i + 1][j] || mat[i][j] == mat[i][j + 1])
                    return "GAME NOT OVER";
            }
        }

        for (int j = 0; j < 3; j++) {
            if (mat[3][j] == mat[3][j + 1]) return "GAME NOT OVER";
        }

        for (int i = 0; i < 3; i++) {
            if (mat[i][3] == mat[i + 1][3]) return "GAME NOT OVER";
        }

        return "LOST";
    }

    // Compresses all non-zero tiles to the left
    public static Object[] compress(int[][] mat) {
        int[][] newMat = new int[4][4];
        boolean changed = false;

        for (int i = 0; i < 4; i++) {
            int pos = 0;
            for (int j = 0; j < 4; j++) {
                if (mat[i][j] != 0) {
                    newMat[i][pos] = mat[i][j];
                    if (j != pos) changed = true;
                    pos++;
                }
            }
        }

        return new Object[]{newMat, changed};
    }

    // Merges adjacent tiles of the same value
    public static Object[] merge(int[][] mat) {
        boolean changed = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (mat[i][j] == mat[i][j + 1] && mat[i][j] != 0) {
                    mat[i][j] *= 2;
                    mat[i][j + 1] = 0;
                    changed = true;
                }
            }
        }
        return new Object[]{mat, changed};
    }

    // Reverse each row
    public static int[][] reverse(int[][] mat) {
        int[][] newMat = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newMat[i][j] = mat[i][3 - j];
            }
        }
        return newMat;
    }

    // Transpose the matrix
    public static int[][] transpose(int[][] mat) {
        int[][] newMat = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newMat[i][j] = mat[j][i];
            }
        }
        return newMat;
    }

    // Movement logic
    public static Object[] moveLeft(int[][] grid) {
        Object[] comp = compress(grid);
        int[][] newGrid = (int[][]) comp[0];
        boolean changed1 = (boolean) comp[1];

        Object[] mergeResult = merge(newGrid);
        newGrid = (int[][]) mergeResult[0];
        boolean changed2 = (boolean) mergeResult[1];

        Object[] comp2 = compress(newGrid);
        newGrid = (int[][]) comp2[0];

        boolean changed = changed1 || changed2;
        return new Object[]{newGrid, changed};
    }

    public static Object[] moveRight(int[][] grid) {
        int[][] newGrid = reverse(grid);
        Object[] move = moveLeft(newGrid);
        newGrid = reverse((int[][]) move[0]);
        return new Object[]{newGrid, (boolean) move[1]};
    }

    public static Object[] moveUp(int[][] grid) {
        int[][] newGrid = transpose(grid);
        Object[] move = moveLeft(newGrid);
        newGrid = transpose((int[][]) move[0]);
        return new Object[]{newGrid, (boolean) move[1]};
    }

    public static Object[] moveDown(int[][] grid) {
        int[][] newGrid = transpose(grid);
        Object[] move = moveRight(newGrid);
        newGrid = transpose((int[][]) move[0]);
        return new Object[]{newGrid, (boolean) move[1]};
    }

    // Print the board
    public static void printMatrix(int[][] mat) {
        System.out.println("\nCurrent Board:");
        for (int[] row : mat) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
        System.out.println();
    }
}
