import java.util.Scanner;

public class UpdateCurrencyRateScreen extends Screen {
    public static void showUpdateCurrencyRateScreen() {
        drawScreenHeader("\tUpdate Currency Screen", "");

        System.out.print("Please enter currency code: ");
        String currCode = Utility.readString();
        while (!Currency.isCurrencyExist(currCode)) {
            System.out.print("\nCurrency code is not found, choose another one: ");
            currCode = Utility.readString();
        }

        Currency curr = Currency.findByCode(currCode);
        System.out.println(curr);

        char answer = 'n';
        System.out.print("Are you sure you want to update the rate of this currency y/n? ");
        answer = new Scanner(System.in).next().charAt(0);
        if (answer == 'Y' || answer == 'y') {
            System.out.print("\n\nUpdate Currency Rate:");
            System.out.print("\n____________________\n");

            System.out.print("\nEnter New Rate: ");
            curr.setRate(Utility.readDoubleNumber());

            System.out.println("\nCurrency Rate Updated Successfully :-)");
            System.out.println(curr);
        }
    }
}
