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
    private void returnSystemOut(){
        System.setOut(originalPrintStream);
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
    void task2(){
        Supplier<Void> printHello = () -> {
             Main.printHello();
             return null;
         };

         printHello.get();
         assertEquals(systemOut.toString(), "Hello");
    }
}