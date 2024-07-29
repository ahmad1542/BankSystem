import java.util.List;

public class CurrenciesListScreen extends Screen {

    private static void printCurrencyRecordLine(Currency curr) {
        System.out.format("%-8s| %-30s| %-8s| %-45s| %-10.3f%n", "", curr.getCountry(), curr.getCurrencyCode(), curr.getCurrencyName(), curr.getRate());
    }

    public static void showCurrenciesListScreen() {
        List <Currency> lCurrency = Currency.GetCurrenciesList();


        String subTitle = String.format("(%d) Currency", lCurrency.size());
        drawScreenHeader("\tCurrencies List Screen", subTitle);

        System.out.print("\n\t\t_______________________________________________________");
        System.out.println("_______________________________________________\n");

        System.out.format("%-8s| %-30s| %-8s| %-45s| %-10s%-8s%n", "", "Country", "Code", "Name", "Rate/(1$)", "");
        System.out.print("\t\t_______________________________________________________");
        System.out.println("_______________________________________________\n");

        if (lCurrency.isEmpty())
            System.out.print("\t\t\t\tNo Currencies Available In the System!");
        else
            for (Currency Currency : lCurrency) {
                printCurrencyRecordLine(Currency);
            }

        System.out.print("\t\t_______________________________________________________");
        System.out.println("_______________________________________________\n");
    }
}
