import java.util.Random;
import java.util.Scanner;

public class Utility {

    public enum CharType {
        SmallLetter(1),
        CapitalLetter(2),
        Digit(3),
        MixChars(4),
        SpecialCharacter(5);

        private final int value;

        CharType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static CharType fromInt(int value) {
            for (CharType type : CharType.values()) {
                if (type.getValue() == value) {
                    return type;
                }
            }
            return null;
        }
    }

    private static final Random random = new Random();

    public static void srand() {
        random.setSeed(System.currentTimeMillis());
    }

    public static int randomNumber(int from, int to) {
        return random.nextInt(to - from + 1) + from;
    }

    public static char getRandomCharacter(CharType charType) {
        if (charType == CharType.MixChars) {
            charType = CharType.fromInt(randomNumber(1, 3));
        }

        switch (charType) {
            case SmallLetter:
                return (char) randomNumber(97, 122);
            case CapitalLetter:
                return (char) randomNumber(65, 90);
            case SpecialCharacter:
                return (char) randomNumber(33, 47);
            case Digit:
                return (char) randomNumber(48, 57);
            default:
                return (char) randomNumber(65, 90);
        }
    }

    public static String generateWord(CharType charType, short length) {
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < length; i++) {
            word.append(getRandomCharacter(charType));
        }

        return word.toString();
    }

    public static String generateKey(CharType charType) {
        return generateWord(charType, (short) 4) + "-" +
                generateWord(charType, (short) 4) + "-" +
                generateWord(charType, (short) 4) + "-" +
                generateWord(charType, (short) 4);
    }

    public static void generateKeys(short numberOfKeys, CharType charType) {
        for (int i = 1; i <= numberOfKeys; i++) {
            System.out.printf("Key [%d] : %s%n", i, generateKey(charType));
        }
    }

    public static void fillArrayWithRandomNumbers(int[] arr, int from, int to) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = randomNumber(from, to);
        }
    }

    public static void fillArrayWithRandomWords(String[] arr, CharType charType, short wordLength) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = generateWord(charType, wordLength);
        }
    }

    public static void fillArrayWithRandomKeys(String[] arr, CharType charType) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = generateKey(charType);
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void swap(double[] a, int i, int j) {
        double temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void swap(boolean[] a, int i, int j) {
        boolean temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void swap(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void shuffleArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            swap(arr, randomNumber(0, arr.length - 1), randomNumber(0, arr.length - 1));
        }
    }

    public static void shuffleArray(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            swap(arr, randomNumber(0, arr.length - 1), randomNumber(0, arr.length - 1));
        }
    }

    public static String tabs(short numberOfTabs) {
        StringBuilder t = new StringBuilder();

        for (int i = 1; i < numberOfTabs; i++) {
            t.append("\t");
            System.out.print(t.toString());
        }

        return t.toString();
    }

    public static String numberToText(int number) {
        if (number == 0) {
            return "";
        }

        if (number >= 1 && number <= 19) {
            String[] arr = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
                    "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
                    "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
            return arr[number] + " ";
        }

        if (number >= 20 && number <= 99) {
            String[] arr = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
            return arr[number / 10] + " " + numberToText(number % 10);
        }

        if (number >= 100 && number <= 199) {
            return "One Hundred " + numberToText(number % 100);
        }

        if (number >= 200 && number <= 999) {
            return numberToText(number / 100) + " Hundreds " + numberToText(number % 100);
        }

        if (number >= 1000 && number <= 1999) {
            return "One Thousand " + numberToText(number % 1000);
        }

        if (number >= 2000 && number <= 999999) {
            return numberToText(number / 1000) + " Thousands " + numberToText(number % 1000);
        }

        if (number >= 1000000 && number <= 1999999) {
            return "One Million " + numberToText(number % 1000000);
        }

        if (number >= 2000000 && number <= 999999999) {
            return numberToText(number / 1000000) + " Millions " + numberToText(number % 1000000);
        }

        if (number >= 1000000000 && number <= 1999999999) {
            return "One Billion " + numberToText(number % 1000000000);
        } else {
            return numberToText(number / 1000000000) + " Billions " + numberToText(number % 1000000000);
        }

    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static String readString() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine().trim();
        return input;
    }

    public static short readShort() {
        Scanner scan = new Scanner(System.in);
        short input = scan.nextShort();
        return input;
    }

    public static short readShortNumberBetween(short from, short to) {
        Scanner scan = new Scanner(System.in);
        short input = scan.nextShort();
        if (input <= to && input >= from) {
            return input;
        }
        while (input > to || input < from) {
            System.out.print("Error!!\nEnter an appropriate number: ");
            input = scan.nextShort();
        }
        return input;
    }

    public static double readDoubleNumber() {
        String errorMessage = "Invalid Number, Enter again";
        Scanner scanner = new Scanner(System.in);
        double number;
        while (true) {
            if (scanner.hasNextDouble()) {
                number = scanner.nextDouble();
                break;
            } else {
                System.out.print(errorMessage);
                scanner.next(); // clear the invalid input
            }
        }
        return number;
    }

    public static int readIntNumber() {
        String errorMessage = "Invalid Number, Enter again";
        Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                break;
            } else {
                System.out.print(errorMessage);
                scanner.next(); // clear the invalid input
            }
        }
        return number;
    }
}
