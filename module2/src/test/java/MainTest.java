import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Viktor Kovalev pro@v-kovalev.ru
 */
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
        int matrixSize = 10;
        boolean result = Main.xor(getIdentityMatrix(matrixSize));
        assertEquals(false, result);

        matrixSize = 11;
        result = Main.xor(getIdentityMatrix(matrixSize));
        assertEquals(true, result);

        List<List<Boolean>> zeroMatrix = new ArrayList<>();
        matrixSize = 10;
        for (int i = 0; i < matrixSize; ++i) {
            List<Boolean> tmpRow = IntStream
                    .range(0, matrixSize)
                    .mapToObj(integer -> false)
                    .collect(Collectors.toList());
            zeroMatrix.add(tmpRow);
        }
        result = Main.xor(zeroMatrix);
        assertEquals(false, result);
    }

    private List<List<Boolean>> getIdentityMatrix(int size) {
        List<List<Boolean>> identityMatrix = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            final int finalI = i;
            List<Boolean> tmpRow = IntStream
                    .range(0, size)
                    .mapToObj(j -> (j != finalI) ? false : true)
                    .collect(Collectors.toList());
            identityMatrix.add(tmpRow);
        }
        return identityMatrix;
    }
}