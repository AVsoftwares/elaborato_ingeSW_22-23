package it.unibs.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputManager {

    public static final String DEFAULT_DATE_FORMATTER_PATTERN = "dd/MM/yy";
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INTEGER_INPUT_PROMPT = "Enter an integer: ";
    private static final String FLOAT_INPUT_PROMPT = "Enter a float: ";
    private static final String STRING_INPUT_PROMPT = "Enter a string: ";

    private static final String YES_NO_REGEX = "(\\s*)(?:y|yes|n|no)(\\s*)";
    private static final String YES_REGEX = "(\\s*)(?:y|yes)(\\s*)";

    private static final String PROMPT_CANNOT_BE_NULL = "Prompt cannot be null";
    private static final String MIN_MUST_BE_LESS_THAN_MAX = "min must be less than max";
    private static final String INVALID_INPUT_OUT_OF_RANGE = "Invalid input: out of range";
    private static final String INVALID_INPUT_EXPECTED_INT = "Invalid input: expected int";
    private static final String INVALID_INPUT_EXPECTED_FLOAT = "Invalid input: expected float";
    private static final String INVALID_INPUT_EXPECTED_STRING = "Invalid input: expected string";
    private static final String INVALID_INPUT_NO_MATCH = "Invalid input: no match";
    private static final String INVALID_INPUT_DATE_CANNOT_BE_PARSED = "Invalid input: date cannot be parsed";
    private static final String INVALID_INPUT_EXPECTED_YES_NO = "Invalid input: expected (y)es or (n)o";

    private InputManager() {
    }

    public static int readInt(String prompt) {
        Objects.requireNonNull(prompt, PROMPT_CANNOT_BE_NULL);

        while (true) {
            System.out.print(prompt);

            if (scanner.hasNextInt()) {
                final var result = scanner.nextInt();
                scanner.nextLine();
                return result;
            } else {
                advanceScanner(INVALID_INPUT_EXPECTED_INT);
            }
        }
    }

    public static int readInt() {
        return readInt(INTEGER_INPUT_PROMPT);
    }

    public static int readInt(String prompt, int min, int max) {
        Objects.requireNonNull(prompt, PROMPT_CANNOT_BE_NULL);

        if (min > max) {
            throw new IllegalArgumentException(MIN_MUST_BE_LESS_THAN_MAX);
        }

        while (true) {
            var input = readInt(prompt);

            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.println(INVALID_INPUT_OUT_OF_RANGE);
            }
        }
    }

    public static float readFloat(String prompt) {
        Objects.requireNonNull(prompt, PROMPT_CANNOT_BE_NULL);

        while (true) {
            System.out.print(prompt);

            if (scanner.hasNextFloat()) {
                final var result = scanner.nextFloat();
                scanner.nextLine();
                return result;
            } else {
                advanceScanner(INVALID_INPUT_EXPECTED_FLOAT);
            }
        }
    }

    public static float readFloat() {
        return readFloat(FLOAT_INPUT_PROMPT);
    }

    public static float readFloat(String prompt, float min, float max) {
        Objects.requireNonNull(prompt, PROMPT_CANNOT_BE_NULL);

        if (min >= max) {
            throw new IllegalArgumentException(MIN_MUST_BE_LESS_THAN_MAX);
        }

        while (true) {
            var input = readFloat(prompt);

            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.println(INVALID_INPUT_OUT_OF_RANGE);
            }
        }
    }

    public static String readString(String prompt) {
        Objects.requireNonNull(prompt, PROMPT_CANNOT_BE_NULL);

        while (true) {
            System.out.print(prompt);

            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            } else {
                advanceScanner(INVALID_INPUT_EXPECTED_STRING);
            }
        }
    }

    public static String readString() {
        return readString(STRING_INPUT_PROMPT);
    }

    public static String readString(String prompt, String regex) {
        Objects.requireNonNull(prompt, PROMPT_CANNOT_BE_NULL);
        Objects.requireNonNull(regex, PROMPT_CANNOT_BE_NULL);

        Pattern pattern = Pattern.compile(regex);

        while (true) {
            var input = readString(prompt);

            if (pattern.matcher(input).matches()) {
                return input;
            } else {
                advanceScanner(INVALID_INPUT_NO_MATCH);
            }
        }
    }

    public static LocalDate readDate(String prompt, String pattern) {
        Objects.requireNonNull(prompt, PROMPT_CANNOT_BE_NULL);
        Objects.requireNonNull(pattern, PROMPT_CANNOT_BE_NULL);

        while (true) {
            System.out.print(prompt + "(" + pattern + ") ");

            if (scanner.hasNextLine()) {
                try {
                    return LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern(pattern));
                } catch (DateTimeParseException e) {
                    System.out.println(INVALID_INPUT_DATE_CANNOT_BE_PARSED);
                }
            } else {
                advanceScanner(INVALID_INPUT_EXPECTED_STRING);
            }
        }
    }

    public static boolean readYesOrNo(String prompt) {
        Objects.requireNonNull(prompt, PROMPT_CANNOT_BE_NULL);

        while (true) {
            System.out.print(prompt + "(y)es/(n)o ");

            if (scanner.hasNext(YES_NO_REGEX)) {
                return scanner.nextLine().matches(YES_REGEX);
            } else {
                advanceScanner(INVALID_INPUT_EXPECTED_YES_NO);
            }
        }
    }

    private static void advanceScanner(String reason) {
        scanner.nextLine();
        System.out.println(reason);
    }
}
