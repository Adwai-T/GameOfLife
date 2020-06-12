/**
 * 
 * The Program given the size of matrix to be formed Will generate a random initial state and play the 
 * Game of Life for an infinite number of generations.
 * 
 * If the program shows generations one after the other and does not clear console after every generation
 * please use console Emulator like cmder.
 * 
 * The program is tested on cmder console emulator.
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Main main = new Main();

        main.doTest();
    }

    public void doTest() throws InterruptedException {

        int count = 1;

        // System.out.println("Please Enter the Number of Cells To play Game of Life : ");

        String[] ar = getUserInput().split(" ");

        SquareCharMatrix matrix = new SquareCharMatrix(Integer.parseInt(ar[0]), Math.round(Math.random()*100));
/*

        //Generations 0 original
        System.out.println("Generation: " + count++);
        System.out.println("Alive : " + matrix.alive);
        matrix.printThisMatrix();
*/
        //First Generation
        char[][] arr = matrix.advanceGeneration(matrix.classMatrix);
        System.out.println("Generation: " + count++);
        System.out.println("Alive : " + matrix.alive);
        matrix.printMatrix(arr);

        while (true) {

            arr = matrix.advanceGeneration(arr);
            System.out.println("Generation: " + count);

            System.out.println("Alive : " + matrix.alive);

            matrix.printMatrix(arr);
            count++;

            Thread.sleep(290);

            clearScreen();
        }
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Get user input from Terminal or console
     *
     * @return
     */
    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
