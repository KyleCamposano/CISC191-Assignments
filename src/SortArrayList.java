import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Prompts user for 10 integers and sorts to increasing order.
 */
public class SortArrayList {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();

        try {
            System.out.print("Enter 10 numbers: ");
            for (int i = 0; i < 10; i++) {
                int numbers = input.nextInt();
                list.add(numbers);
            }
        }
        catch (Exception e) {
            System.out.println("\nPlease only enter numbers.\n");
        }

        System.out.print("Your digits are: " + list);
        sort(list);
        System.out.print("\nThe new sorted numbers are: [");

        /*
            The two for-loops are split into two ranges (first at one
            index before last index, and second at last index) to
            add a comma each iteration and a closing bracket on the list.
         */

        for (int i = 0; i <= list.size() - 2; i++) {
            System.out.print(list.get(i) + ", ");
        }
        for (int i = list.size() - 1; i <= list.size() - 1; i++) {
            System.out.print(list.get(i) + "]");
        }
    }


    //using return type Object for unit testing
    public static <E extends Comparable<E>> Object sort(ArrayList<E> list) {
        E min;
        int minIndex;

        /*
            compares current index (i) to next index (j) and sets j as minimum
            value if value of i is higher.

            comparesTo() returns:
            -negative if value of i < value of j.
            -zero if value of i == value of j.
            -positive if value of i > value of j.
         */

        for (int i = 0; i < list.size()-1; i++) {
            minIndex = i;
            min = list.get(i);

            for (int j= i + 1; j < list.size(); j++) {
                if (min.compareTo(list.get(j)) > 0) { //compares value of i to value of j
                    minIndex = j;
                    min = list.get(j);
                }
            }

            if (minIndex != i) {
                list.set(minIndex, list.get(i)); //sets current value of i to minIndex (i or j).
                list.set(i, min); //sets minimum value (.get(i) or .get(j)) to current index.
            }
        }

        return list;
    }


    //Alternative method: with this method, no need to extend Comparable<E>
    public static <E extends Comparable<E>> Object sort2(ArrayList<E> list) {
        Collections.sort(list);

        return list;
    }
}

