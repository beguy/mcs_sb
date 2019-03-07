import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Main {
    public static BiPredicate<Set<Character>, String> task1() {
        return (alphabetSet, testString) -> {
            String alphabetString = alphabetSet.stream().map(Object::toString)
                    .collect(Collectors.joining(" "));
            return !testString.matches("^[" + alphabetString + "]+");
        };
    }

    public static void printHello() {
        System.out.print("Hello");
    }

    public static Consumer<String> task3() {
        return null;
    }
}