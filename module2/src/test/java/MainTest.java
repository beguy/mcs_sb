import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void uniqueNumbers() {
        List<Double> doubleList = Arrays.asList(12.2, 13.0, 12.2, 0.0, 0.0);
        int result = Main.uniqueNumbers(doubleList);
        assertEquals(3, result);

        List emptyList = Collections.emptyList();
        result = Main.uniqueNumbers(emptyList);
        assertEquals(0, result);

        List<Integer> integerList = Arrays.asList(1, 2, 3, 1, 4, 5, 6);
        result = Main.uniqueNumbers(integerList);
        assertEquals(6, result);
    }

    @Test
    void xor() {

    }
}