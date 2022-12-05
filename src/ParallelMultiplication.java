import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Scanner;

/**
 *  Prompts user to enter size of two matrices and multiplies
 *  them with default values. Returns the execution time of
 *  2 methods -- with & without threads.
 */
public class ParallelMultiplication {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter size of the matrices: ");
        int size = input.nextInt();

        double[][] newMatrix1 = new double[size][size];
        double[][] newMatrix2 = new double[size][size];

        //  multiplying matrices with threads.
        Date start = new Date();
        MultRowThread.parallelMultiplyMatrix(newMatrix1, newMatrix2);
        Date end = new Date();
        System.out.println("\nMultiplication of matrices with threads: " + ((end.getTime() - start.getTime())/1000) + " second(s)");

        //  multiplying matrices without threads.
        start = new Date();
        MultMatrices.multiplyWithoutThreads(newMatrix1, newMatrix2);
        end = new Date();
        System.out.println("\nMultiplication of matrices without threads: " + ((end.getTime() - start.getTime())/1000) + " second(s)");
    }

    //  creating worker thread with override run()
    public static class MultRowThread implements Runnable {
        private final double[][] result;
        private final double[][] matrix1;
        private final double[][] matrix2;
        private final int row;

        //  constructor for class
        public MultRowThread(double[][] result, double[][] matrix1, double[][] matrix2, double row) {
            this.result = result;
            this.matrix1 = matrix1;
            this.matrix2 = matrix2;
            this.row = (int) row;
        }

        //  multiplies two matrices and stores result.
        public static double[][] parallelMultiplyMatrix(double[][] a, double[][] b) {
            double[][] newResult = new double[a.length][b[0].length];
            CreateThreads.multiplyWithThreads(a, b, newResult);
            return newResult;
        }

        //  overrides run() from Runnable interface with calculation of two matrices
        //  (col of 1st matrix * row of 2nd matrix) + next row & col
        @Override
        public void run() {
            for (int i = 0; i < matrix2[0].length; i++) {
                result[row][i] = 0;
                for (int j = 0; j < matrix1[row].length; j++) {
                    result[row][i] += matrix1[row][j] * matrix2[j][i];
                }
            }
        }
    }

    // creates a thead up to 10, waits for each completion, and repeat.
    // credit: https://www.javaprogramto.com/2020/01/java-matrix-multiplication-threads.html
    public static class CreateThreads {
        /*
            When utilizing this method with actual values,
            race condition will occur since the result since
            result is a shared variable among task threads.

            solutions:
            -synchronization (1 thread at a time accessing shared var),
            -volatile (no local caching of shared var, changes instantly visible to all threads),
            -or atomic vars (all or nothing op, ensures end result is accurate).
         */

        //  param takes in total rows for run(), each row is a task thread.
        public static void multiplyWithThreads(double[][] matrix1, double[][] matrix2, double[][] result) {
            List<Thread> threads = new ArrayList<>();
            int rows1 = matrix1.length;
            for (int i = 0; i < rows1; i++) {
                MultRowThread task = new MultRowThread(result, matrix1, matrix2, i);
                Thread multThread = new Thread(task);
                multThread.start();
                threads.add(multThread);
                if (threads.size() == 10) {
                    waitForThreads(threads);
                }
            }
        }

        private static void waitForThreads(List<Thread> threads) {
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            threads.clear();
        }
    }

    //  Multiplying matrices with no threads.
    public static class MultMatrices {
        public static int[][] multiplyWithoutThreads(double[][] matrix1, double[][] matrix2) {
            int rows = matrix1.length;
            int columns = matrix1[0].length;
            int columns2 = matrix2[0].length;
            int[][] result = new int[rows][columns];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns2; j++) {
                    result[i][j] = 0;
                    for (int k = 0; k < columns; k++) {
                        result[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
            return result;
        }
    }
}

