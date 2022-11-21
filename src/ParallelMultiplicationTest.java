import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class ParallelMultiplicationTest {
    double[][] firstMatrix = new double[2][2];
    double[][] secondMatrix = new double[2][2];
    double[][] thirdMatrix = new double[1000][1000];
    double[][] fourthMatrix = new double[1000][1000];

    @Test
    public void ParallelMatrices() {
        assertEquals(Arrays.deepToString(new double[][]{{0.0, 0.0}, {0.0, 0.0}}), Arrays.deepToString(ParallelMultiplication.MultRowThread.parallelMultiplyMatrix(firstMatrix, secondMatrix)));
    }
    //  to see the execution time of multiplying matrices with threads.
    @Test
    public void ParallelMatrices2() {
         assertEquals(Arrays.deepToString(ParallelMultiplication.MultRowThread.parallelMultiplyMatrix(thirdMatrix, fourthMatrix)),
                 Arrays.deepToString(ParallelMultiplication.MultRowThread.parallelMultiplyMatrix(thirdMatrix, fourthMatrix)));
    }
    //  to see the execution time of multiplying matrices without threads.
    @Test
    public void MultiplyingMatrices() {
        assertEquals(Arrays.deepToString(ParallelMultiplication.MultMatrices.multiplyWithoutThreads(thirdMatrix, fourthMatrix)),
                Arrays.deepToString(ParallelMultiplication.MultMatrices.multiplyWithoutThreads(thirdMatrix, fourthMatrix)));
    }
}