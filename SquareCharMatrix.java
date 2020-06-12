import java.util.Random;

public class SquareCharMatrix {

    public final char[][] classMatrix;

    public int alive = 0;

    /**
     * Constructs a new SquareCharMatrix instance form given dimensions and
     * populates it with random characters O and " "(empty String);
     *
     * @param dimension
     * @param randomizerSeed
     */
    public SquareCharMatrix(int dimension, long randomizerSeed) {

        classMatrix = populateMatrix(dimension, randomizerSeed);

    }

    /**
     * @param matrix
     */
    public SquareCharMatrix(char[][] matrix) {
        classMatrix = matrix;
    }

    public char[][] advanceGeneration(char [][] arr){

        char[][] arr1 = newCopyOfMatrix(arr);
        char[][] arr2 = newCopyOfMatrix(arr);

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1.length; j++) {

                if (checkSurroundingCell(i, j, arr1) < 2) {
                    arr2[i][j] = ' ';
                    if(arr1[i][j] == 'O') alive--;
                }
                else if (checkSurroundingCell(i, j, arr1) == 3) {
                    arr2[i][j] = 'O';
                    if(arr1[i][j] != 'O') alive++;
                }
                else if (checkSurroundingCell(i, j, arr1) > 3) {
                    arr2[i][j] = ' ';
                    if(arr1[i][j] == 'O') alive--;
                }
            }
        }

        arr1 = newCopyOfMatrix(arr2);

        return arr1;

    }

    /**
     * Build the array according to the dimensions and populate it with the randomizer int
     *
     * @param dimensions     The dimensions for the square matrix
     * @param randomizerSeed RandomizerString
     * @return Returns a character Matrix
     */
    public char[][] populateMatrix(int dimensions, long randomizerSeed) {

        //Array that represents our matrix
        char[][] matrix = new char[dimensions][dimensions];

        Random random = new Random(randomizerSeed);

        //Populate the array
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {

                if (random.nextBoolean()){
                    matrix[i][j] = 'O';
                    alive++;
                }
                else matrix[i][j] = ' ';
            }
        }

        return matrix;
    }

    public void printThisMatrix(){
        printMatrix(classMatrix);
    }

    /**
     * @param matrix Character array to print
     */
    public void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            //Except for the first loop print next loop on next-line
            if (i > 0) {
                System.out.println("");
            }
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j]);
            }
        }
        System.out.println("");
    }

    /**
     * @return New copu of the instance of the Matrix
     */
    public char[][] newCopyOfMatrix(char[][] matrix) {
        char[][] arr = new char[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {

                arr[i][j] = matrix[i][j];

            }
        }

        return arr;
    }

    /**
     * Check if the index is an element beyond the bounds of the matrix
     * and returns the other side index if it is.
     *
     * @param index index to be checked
     * @return
     */
    private int giveEdgeIndex(int index) {
        int a = index;
        //Check i;
        if (a < 0) {
            a = classMatrix.length - 1;
        } else if (a >= classMatrix.length) {
            a = 0;
        }
        return a;
    }

    /**
     * Check haw many alive cells surround our cell
     *
     * @param i
     * @param j
     * @return
     */
    public int checkSurroundingCell(int i, int j, char[][] matrix) {

        int aliveCellCount = 0;

        //below
        if (matrix[giveEdgeIndex(i + 1)][giveEdgeIndex(j)] == 'O') aliveCellCount++;
        if (matrix[giveEdgeIndex(i + 1)][giveEdgeIndex(j + 1)] == 'O') aliveCellCount++;
        if (matrix[giveEdgeIndex(i + 1)][giveEdgeIndex(j - 1)] == 'O') aliveCellCount++;

        //above
        if (matrix[giveEdgeIndex(i - 1)][giveEdgeIndex(j)] == 'O') aliveCellCount++;
        if (matrix[giveEdgeIndex(i - 1)][giveEdgeIndex(j + 1)] == 'O') aliveCellCount++;
        if (matrix[giveEdgeIndex(i - 1)][giveEdgeIndex(j - 1)] == 'O') aliveCellCount++;

        //same Row
        if (matrix[giveEdgeIndex(i)][giveEdgeIndex(j + 1)] == 'O') aliveCellCount++;
        if (matrix[giveEdgeIndex(i)][giveEdgeIndex(j - 1)] == 'O') aliveCellCount++;

        return aliveCellCount;

    }

    /**
     * Test Class to check logic
     *
     * @param i
     * @param j
     */
    public void printSurroundingCells(int i, int j) {

        char[][] colorArr = newCopyOfMatrix(classMatrix);
        //below
        colorArr[giveEdgeIndex(i + 1)][giveEdgeIndex(j)] = 'X';
        colorArr[giveEdgeIndex(i + 1)][giveEdgeIndex(j + 1)] = 'X';
        colorArr[giveEdgeIndex(i + 1)][giveEdgeIndex(j - 1)] = 'X';

        //above
        colorArr[giveEdgeIndex(i - 1)][giveEdgeIndex(j)] = 'X';
        colorArr[giveEdgeIndex(i - 1)][giveEdgeIndex(j + 1)] = 'X';
        colorArr[giveEdgeIndex(i - 1)][giveEdgeIndex(j - 1)] = 'X';

        //same Row
        colorArr[giveEdgeIndex(i)][giveEdgeIndex(j + 1)] = 'X';
        colorArr[giveEdgeIndex(i)][giveEdgeIndex(j - 1)] = 'X';

        for (int a = 0; a < colorArr.length; a++) {
            System.out.println("");
            for (int b = 0; b < colorArr.length; b++) {

                if (colorArr[a][b] == 'X') {
                    System.out.print(ColorToConsole.ANSI_CYAN_BACKGROUND + colorArr[a][b] + ColorToConsole.ANSI_RESET);
                } else if (a == i && b == j) {
                    System.out.print(ColorToConsole.ANSI_PURPLE_BACKGROUND + colorArr[a][b] + ColorToConsole.ANSI_RESET);
                } else {
                    System.out.print(colorArr[a][b]);
                }

            }
        }

    }
}
