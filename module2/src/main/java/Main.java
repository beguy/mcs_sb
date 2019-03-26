import java.util.List;

public class Main {
    // task1
    public static int uniqueNumbers(List<? extends Number> numberList) {
        return (int) numberList.stream().distinct().count();
    }

    // task2
    public static boolean xor(List<List<Boolean>> matrix) {
        return matrix.stream()
                .parallel()
                .flatMap(row -> row.stream())
                .reduce(false, (a, b) -> a ^ b);
    }
}
