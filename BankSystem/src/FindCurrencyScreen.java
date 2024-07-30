import java.util.Scanner;

public class FindCurrencyScreen extends Screen {
    private static void showResults(Currency currency)
    {
        if (!currency.isEmpty()) {
            System.out.println("\nCurrency Found :-)");
            System.out.println(currency);
        }
        else {
            System.out.println("\nCurrency Was not Found :-(");
        }
    }
    public static void showFindCurrencyScreen() {

        drawScreenHeader("\t  Find Currency Screen", "");

        Scanner answerScanner = new Scanner(System.in);
        System.out.print("Find By: [1] Code or [2] Country ? ");
        short answer = answerScanner.nextShort();
        if (answer == 1) {
            System.out.print("\nPlease Enter CurrencyCode: ");
            String currencyCode = Utility.readString();
            showResults(Currency.findByCode(currencyCode));
        }
        else {
            System.out.print("\nPlease Enter Country Name: ");
            String country = Utility.readString();
            showResults(Currency.findByCountry(country));
        }
    }
}
