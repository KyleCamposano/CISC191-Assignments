import java.util.Scanner;

/**
 * Prompts user to enter a digit (number of iteration in the
 * Fibonacci sequence). Returns sum of the sequence.
 */
public class RecursiveFibonacci {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int inputValue;
        int sum;

        System.out.println("[   WELCOME TO THE FIBONACCI SEQUENCE   ]" +
                "\nTell me a number, and " +
                "\nI will show your lucky" +
                "\ndigit in the sequence." +
                "\n\nEnter any number:");

        inputValue = input.nextInt();
        sum = fibonacci(inputValue);

        System.out.println("Your number in the sequence is " + sum);
    }

    /*
        Adds the sums of the two previous sumValues to find the value
        of the current index (i).
     */
    public static int fibonacci(int i) {
        // Any negative index values returns -1.
        if (i < 0) return -1;
        // Ensures the starting point of recursion is i = 2.
        if (i < 2) return i;

        // current sumValue = sum of two previous sumValue.
        int sumValue = fibonacci(i - 1) + fibonacci(i - 2);

        return sumValue;
    }
}

