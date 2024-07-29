import java.util.Scanner;

public class CurrencyExchangeMainScreen extends Screen {
    private enum enCurrenciesMainMenuOptions {
        eListCurrencies,
        eFindCurrency,
        eUpdateCurrencyRate,
        eCurrencyCalculator,
        eMainMenu
    }

    private static enCurrenciesMainMenuOptions readCurrenciesMainMenuOption() {
        System.out.printf("%-37s%s", "", "Choose what do you want to do? [1 to 5]? ");
        short choice = Utility.readShortNumberBetween((short) 1, (short) 5);
        switch (choice) {
            case 1 -> {
                return enCurrenciesMainMenuOptions.eListCurrencies;
            }
            case 2 -> {
                return enCurrenciesMainMenuOptions.eFindCurrency;
            }
            case 3 -> {
                return enCurrenciesMainMenuOptions.eUpdateCurrencyRate;
            }
            case 4 -> {
                return enCurrenciesMainMenuOptions.eCurrencyCalculator;
            }
            default ->  {
                return enCurrenciesMainMenuOptions.eMainMenu;
            }
        }
    }

    private static void goBackToCurrenciesMenu() {
        System.out.println("\n\nPress any key to go back to Currencies Menu...");
        new Scanner(System.in).nextLine();
        showCurrenciesMenu();
    }

    private static void showCurrenciesListScreen() {
        CurrenciesListScreen.showCurrenciesListScreen();
    }

    private static void showFindCurrencyScreen() {
        System.out.println("\nFind Currency Screen Will Be Here.\n");
    }

    private static void showUpdateCurrencyRateScreen() {
        System.out.println("\nUpdate Currency Rate Screen Will Be Here.\n");
    }

    private static void showCurrencyCalculatorScreen() {
        System.out.println("\nCurrency Calculator Screen Will Be Here.\n");
    }

    private static void performCurrenciesMainMenuOptions(enCurrenciesMainMenuOptions CurrenciesMainMenuOptions) {
        switch (CurrenciesMainMenuOptions) {
            case eListCurrencies -> {
                Utility.clearConsole();
                showCurrenciesListScreen();
                goBackToCurrenciesMenu();
                break;
            }
            case eFindCurrency -> {
                Utility.clearConsole();
                showFindCurrencyScreen();
                goBackToCurrenciesMenu();
                break;
            }
            case eUpdateCurrencyRate -> {
                Utility.clearConsole();
                showUpdateCurrencyRateScreen();
                goBackToCurrenciesMenu();
                break;
            }
            case eCurrencyCalculator -> {
                Utility.clearConsole();
                showCurrencyCalculatorScreen();
                goBackToCurrenciesMenu();
                break;
            }
            case eMainMenu -> {
                //do nothing here the main screen will handle it :-) ;
            }
        }

    }

    public static void showCurrenciesMenu() {
        Utility.clearConsole();
        drawScreenHeader("    Currency Exchange Main Screen", "");

        System.out.format("%-37s===========================================%n", "");
        System.out.format("%-37s\t\t  Currency Exchange Menu\n", "");
        System.out.format("%-37s===========================================\n", "");
        System.out.format("%-37s\t[1] List Currencies.\n", "");
        System.out.format("%-37s\t[2] Find Currency.\n", "");
        System.out.format("%-37s\t[3] Update Rate.\n", "");
        System.out.format("%-37s\t[4] Currency Calculator.\n", "");
        System.out.format("%-37s\t[5] Main Menu.\n", "");
        System.out.format("%-37s===========================================\n", "");

        performCurrenciesMainMenuOptions(readCurrenciesMainMenuOption());
    }
}
