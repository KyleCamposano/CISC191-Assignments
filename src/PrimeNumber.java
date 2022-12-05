import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 * Identifies if number is Prime Number or not using lambda.
 */
public class PrimeNumber {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number to check if it's Prime (True / False):");
        int num = input.nextInt();

        boolean isPrime = (new isPrimeNumber().isPrime(num));
        System.out.println("Is " + num + " a Prime Number? " + isPrime);

        //test for alternative method
        //System.out.println(isPrime2(num));
    }

    // necessary functional interface for lambda exp.
    public interface Prime {
        boolean primeCalc(int num);
    }

    public static class isPrimeNumber implements Prime {
        @Override // tests if num is prime or not.
        public boolean primeCalc(int num) {
            int i;
            for (i = 2; i < num - 1; i++) {
                if ((num % i == 0) || num == 1)
                    return false;
            }
            return true;
        }

        /*
            Prime interface w/ override primeCalc() from isPrimeNumber
            class. Takes num and returns true/ false if it's prime or not
         */
        public boolean isPrime(int num) {
            Prime isNumPrime = x -> {
                int i;
                boolean a = true;
                if (num < 2) a = false;
                for (i = 2; i < num - 1; i++) {
                    if ((num % i == 0))
                        a = false;
                }
                return a;
            };
            // directly calls primeCalc() from new Prime object
            // bypasses the body of lambda expression?
            return isNumPrime.primeCalc(num);
        }
    }

    /*
        alternative method:
        credits from Eck textbook && https://www.youtube.com/watch?v=8pDm_kH4YKY
        Creates a stream with closed range and matches stream with predicate.
        Predicate calcs every i from given range if number is divisible
        by all preceding numbers (i) within given range.
     */
    public static boolean isPrime2(int number) {
        IntPredicate primeCalc = i -> number % i == 0;
        return number > 1 && (IntStream.rangeClosed(2, (number - 1)).noneMatch(primeCalc));
    }
}
