import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void task1() {
        BiPredicate<Set<Character>, String> isIllegalString = Main.task1();

        Set<Character> alphabetSet = new HashSet<>(asList('a', 'b'));
        String testSting = "abc";
        assertTrue(isIllegalString.test(alphabetSet, testSting));

        testSting = "ab";
        assertFalse(isIllegalString.test(alphabetSet, testSting));

        testSting = "";
        assertTrue(isIllegalString.test(alphabetSet, testSting));
    }
}