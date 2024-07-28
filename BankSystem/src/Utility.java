import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

public class Utility {


    private static final Random random = new Random();

    public static int randomNumber(int from, int to) {
        return random.nextInt(to - from + 1) + from;
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

    public static String currentDate() {
        return LocalDate.now().toString();
    }

    public static String currentTime() {
        return LocalTime.now().toString();
    }

    public static String currentDateTime() {
        return LocalDate.now() + " - " + LocalTime.now();
    }

    private static String encryptTextSkeleton(String Text, short encryptionKey) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < Text.length(); i++) {
            char encryptedChar = (char) ((int) Text.charAt(i) + encryptionKey);
            encryptedText.append(encryptedChar);
        }

        return encryptedText.toString();
    }

    public static String encryptText(String Text) {
        return encryptTextSkeleton(Text, (short) 2);
    }

    public static String encryptText(String Text, short encryptionKey) {
        return encryptTextSkeleton(Text, encryptionKey);
    }

    private static String decryptTextSkeleton(String Text, short encryptionKey) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < Text.length(); i++) {
            char decryptedChar = (char) ((int) Text.charAt(i) - encryptionKey);
            decryptedText.append(decryptedChar);
        }

        return decryptedText.toString();
    }

    public static String decryptText(String Text) {
        return decryptTextSkeleton(Text, (short) 2);
    }

    public static String decryptText(String Text, short encryptionKey) {
        return decryptTextSkeleton(Text, encryptionKey);
    }
}
