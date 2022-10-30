import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class SortArrayListTest {
    static ArrayList<Integer> list = new ArrayList<>();
    Object newArray = SortArrayList.sort(list);

    @BeforeAll
    public static void setUp() {
        list.add(3);
        list.add(4);
        list.add(12);
        list.add(7);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(4);
        list.add(7);
    }

    @Test
    public void TestSort(){
        assertEquals(Arrays.asList(3,3,4,4,4,5,6,7,7,12), newArray);

    }
}