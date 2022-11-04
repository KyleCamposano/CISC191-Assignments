import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] list = {};

        try {
            System.out.print("Enter 10 numbers: ");
            for (int i = 0; i < 10; i++) {
                int x = input.nextInt();
                list = addToList(list, x);
            }
        }
        // forces successful termination if invalid input. Still prints after a number is inputted.
        catch (Exception e) {
            if (list.length < 1) {
                System.out.println("\nInvalid Input. Please only enter numbers.\n");
                System.exit(0);
            } else {
                System.out.println("\nInvalid Input. Please only enter numbers.\n");
            }
        }

        System.out.print("Your digits are: " + Arrays.toString(list));
        mergeSort(list);
        System.out.print("\nThe new sorted numbers are: [");

        /*
            The two for-loops are split into two ranges (first at one
            index before last index, and second at last index) to
            add a comma each iteration and a closing bracket on the list.
         */
        for (int i = 0; i <= list.length - 2; i++) {
            System.out.print(list[i] + ", ");
        }
        for (int i = list.length - 1; i <= list.length - 1; i++) {
            System.out.print(list[i] + "]");
        }
    }

    /*
        Increases size of old list, copies all items from old to new list,
        and adds new element into the last index of new list. Alternatively,
        use ArrayList<Integer> and call .add() method.
     */
    public static int[] addToList(int[] list, int x) {
        int[] newList = new int[list.length + 1]; // adds +1 size on list
        int i;

        for (i = 0; i < list.length; i++) {// same notation: i <= list.length-1
            newList[i] = list[i];
        }
        newList[list.length] = x; // newly added (+1) last index of the newList.

        return newList;
    }

    /*
        Recursively splits list into two sublist until there's only one element
        in the list.

        Credit: <https://github.com/LuizGsa21/intro-to-java-10th-edition/blob/master/src/Chapter_23/MergeSort.java>
     */

    public static int[] mergeSort (int[] list){
        // ensures method only works on list with two or more number.
        if (list.length > 1) {  // base case for recursion

            /*
                Recursively splits list into two sublist and stores its
                first half until base case (list only has 1 length).
             */
            int firstHalfLen = list.length / 2;
            int[] firstHalf = new int[firstHalfLen];
            System.arraycopy(list, 0, firstHalf, 0, firstHalfLen);
            mergeSort(firstHalf);   // recursion for firstHalf of list.

             /*
                Recursively splits list into two sublist and stores its
                second half until base case (list only has 1 length).
             */
            int secondHalfLen = list.length - (list.length / 2);
            int[] secondHalf = new int[secondHalfLen];
            System.arraycopy(list, (list.length / 2), secondHalf, 0, secondHalfLen);
            mergeSort(secondHalf);  // recursion for secondHalf of list.

            merge(firstHalf, secondHalf, list);
        }
        return list;
    }

    /*
        Compares current indices relative to the length of each list. If the value
        is the least, then increment both third list's current index and the list's current
        index of the least value. At the end, the last two while-loops inputs the value of
        the last current index (either first or second list).
     */
    public static int[] merge (int[] firstList, int[] secondList, int[] thirdList){
        int firstListCurrIndex = 0;
        int secondListCurrIndex = 0;
        int thirdListCurrIndex = 0;
        //  compares values in first and second lists.
        while (firstListCurrIndex < firstList.length && secondListCurrIndex < secondList.length) {
            // stores the least value in current index in thirdList then increments.
            if (firstList[firstListCurrIndex] < secondList[secondListCurrIndex]) {
                thirdList[thirdListCurrIndex++] = firstList[firstListCurrIndex++];
            } else {
                thirdList[thirdListCurrIndex++] = secondList[secondListCurrIndex++];  // increments both lists
            }
        }

        while (firstListCurrIndex < firstList.length) {
            thirdList[thirdListCurrIndex++] = firstList[firstListCurrIndex++];  // increments both lists
        }

        while (secondListCurrIndex < secondList.length ) {
            thirdList[thirdListCurrIndex++] = secondList[secondListCurrIndex++]; // increments both lists
        }
        return thirdList;
    }
}
