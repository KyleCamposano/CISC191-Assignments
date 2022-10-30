import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveFibonacciTest {

    @Test
    public void TestFibonacci() {
        assertEquals(-1, RecursiveFibonacci.fibonacci(-7));
        assertEquals(1, RecursiveFibonacci.fibonacci(2));
        assertEquals(13, RecursiveFibonacci.fibonacci(7));

    }
}