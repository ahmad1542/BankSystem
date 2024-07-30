public class CurrencyCalculatorScreen extends Screen {
    private static void currencyCalculator(String fromCurrCode, String toCurrCode, double amount) {
        Currency fromCurr = Currency.findByCode(fromCurrCode);
        Currency toCurr = Currency.findByCode(toCurrCode);
        double amountInUSD = fromCurr.convertToUSD(amount);

        System.out.format("%f %s = %f USD%n", amount, fromCurr.getCurrencyCode(), amountInUSD);

        if (toCurr.getCurrencyCode().equalsIgnoreCase("USD")) {
            return;
        }

        System.out.println("\nConverting from USD to: ");
        System.out.println("\nTo:");
        System.out.println(toCurr);

        double amountInFromCurrency = fromCurr.convertToOtherCurrencies(amount, toCurr);

        System.out.format("%f %s = %f %s%n", amount, fromCurrCode, amountInFromCurrency, toCurrCode);

    }

    public static void showCurrencyCalculatorScreen() {
        drawScreenHeader("  Currency Calculator Screen", "");
        char answer = 'y';
        while (answer == 'Y' || answer == 'y') {
            Utility.clearConsole();

            System.out.print("Please enter the currency code you want to convert from: ");
            String fromCurrCode = Utility.readString();
            while (!Currency.isCurrencyExist(fromCurrCode)) {
                System.out.print("\nCurrency code is not found, choose another one: ");
                fromCurrCode = Utility.readString();
            }

            System.out.print("\nPlease enter the currency code you want to convert to: ");
            String toCurrCode = Utility.readString();
            while (!Currency.isCurrencyExist(toCurrCode)) {
                System.out.print("\nCurrency code is not found, choose another one: ");
                toCurrCode = Utility.readString();
            }

            System.out.print("Enter amount to exchange: ");
            double amount = Utility.readDoubleNumber();

            System.out.println("Convert From:");
            System.out.println(Currency.findByCode(fromCurrCode));

            currencyCalculator(fromCurrCode, toCurrCode, amount);

            System.out.print("Do you want to perform another calculation? y/n? ");
            answer = Utility.readChar();
        }
    }
}
