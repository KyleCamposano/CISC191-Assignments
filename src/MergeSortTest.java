import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
    static int[] list = {};
    static int[] secondList = {};
    static int[] thirdList = {};

    static int[] fourthList = {};

    @BeforeAll
    public static void setUp() {
        secondList = new int[]{8,9};
        thirdList = new int[]{6,7};
        fourthList = new int[4];
    }

    @Test
    public void AddToList() {
        assertEquals(Arrays.toString(new int[]{4}), Arrays.toString(MergeSort.addToList(list, 4)));
    }

    @Test
    public void MergeSortList() {
        assertEquals(Arrays.toString(new int[]{1,2,3,4}), Arrays.toString(MergeSort.mergeSort(new int[]{3,2,4,1})));
    }

    @Test
    public void MergeLists() {
        assertEquals(Arrays.toString(new int[]{6,7,8,9}), Arrays.toString(MergeSort.merge(secondList, thirdList, fourthList)));
    }
}