import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAddCalculator {

    private static final Pattern PATTERN = Pattern.compile("//(.)\n(.*)");
    private static final int DELIMITER = 1;
    private static final int TEXT = 2;

    public static int splitAndSum(String input) {
        if (isNullOrEmpty(input)) {
            return 0;
        }

        String[] tokens = getArrayDividedByDelimiter(input);
        checkNonNumericVal(tokens);
        checkNegative(tokens);

        return sum(tokens);
    }

    private static boolean isNullOrEmpty(String text) {
        return text == null || text.isEmpty();
    }

    private static String[] getArrayDividedByDelimiter(String input) {

        Matcher matcher = PATTERN.matcher(input);
        if (matcher.find()) {
            String customDelimiter = matcher.group(DELIMITER);
            return matcher.group(TEXT).split(customDelimiter);
        }

        return input.split(",|:");
    }

    private static boolean isContainNegative(String[] tokens) {
        return Arrays.stream(tokens).anyMatch(token -> Integer.parseInt(token) < 0);
    }

    private static int sum(String[] tokens) {
        return Arrays.stream(tokens).mapToInt(Integer::parseInt).sum();
    }

    private static void checkNegative(String[] tokens) {
        if (isContainNegative(tokens)) {
            throw new IllegalArgumentException("음수가 포함되어 있습니다.");
        }
    }

    private static void checkNonNumericVal(String[] tokens) {
        Arrays.stream(tokens).forEach(token -> checkEachValueWhetherNumberFormat(token));
    }

    private static void checkEachValueWhetherNumberFormat(String eachString) {
        try {
            Integer.parseInt(eachString);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("숫자 형태의 값이 들어있습니다.");
        }
    }

}
