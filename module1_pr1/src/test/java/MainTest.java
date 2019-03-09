import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Viktor Kovalev pro@v-kovalev.ru
 */
class MainTest {
    private ByteArrayOutputStream systemOut = new ByteArrayOutputStream();
    private PrintStream originalPrintStream = System.out;

    @BeforeEach
    private void cathSystemOut() {
        systemOut = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(systemOut);
        System.setOut(ps);
    }

    @AfterEach
    private void returnSystemOut() {
        System.setOut(originalPrintStream);
    }

    private String getSystemOutString() {
        String tmp = systemOut.toString();
        systemOut.reset();
        return tmp;
    }

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

    @Test
    void task2() {
        Supplier<Void> printHello = () -> {
            Main.printHello();
            return null;
        };

        printHello.get();
        assertEquals(getSystemOutString(), "Hello");
    }

    @Test
    void task3() {
        Main.task3().accept("abc");
        assertEquals(getSystemOutString(), "97 98 99 ");

        Main.task3().accept("$ !");
        assertEquals(getSystemOutString(), "36 32 33 ");

        Main.task3().accept("");
        assertEquals(getSystemOutString(), "");
    }
}