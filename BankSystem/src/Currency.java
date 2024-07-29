import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Currency {
    enum enMode {
        emptyMode,
        updateMode
    }
    enMode mode;

    String country;
    String currencyCode;
    String currencyName;
    double rate;

    public Currency(enMode mode, String country, String currencyName, String currencyCode, double rate) {
        this.mode = mode;
        this.country = country;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.rate = rate;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
        update();
    }

    private static Currency convertLineToCurrencyObject(String line) {
        String separator = "#//#";
        Scanner lineScanner = new Scanner(line);
        lineScanner.useDelimiter(separator);

        String country = lineScanner.next();
        String currencyCode = lineScanner.next();
        String currencyName = lineScanner.next();
        Double rate = lineScanner.nextDouble();

        return new Currency(enMode.updateMode, country, currencyName, currencyCode, rate);
    }

    private static String convertCurrencyObjectToLine(Currency currency) {
        String separator = "#//#";

        String currencyRecord = "";
        currencyRecord += currency.country + separator;
        currencyRecord += currency.currencyCode + separator;
        currencyRecord += currency.currencyName + separator;
        currencyRecord += currency.rate;

        return  currencyRecord;
    }

    private static List <Currency> loadCurrencyDataFromFile() {
        String dataLine;
        List <Currency> lCurrency = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new FileReader("Currency.txt"))) {
            while (fileScanner.hasNextLine()) {
                dataLine = fileScanner.nextLine();
                lCurrency.add(convertLineToCurrencyObject(dataLine));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lCurrency;
    }

    private static void saveCurrencyDataToFile(List <Currency> lCurrency) {
        try (BufferedWriter bfWriter = new BufferedWriter(new FileWriter("Currency.txt"))) {
            for (Currency curr : lCurrency) {
                bfWriter.write(convertCurrencyObjectToLine(curr));
                bfWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        List <Currency> lCurrency;
        lCurrency = loadCurrencyDataFromFile();
        for(int i = 0; i <= lCurrency.size(); i++) {
            if(lCurrency.get(i).currencyCode.equals(this.currencyCode)) {
                lCurrency.set(i, this);
                break;
            }
        }
        saveCurrencyDataToFile(lCurrency);
    }

    private static Currency getEmptyCurrencyObject() {
        return new Currency(enMode.emptyMode, "", "", "", 0);
    }

    public static List <Currency> getAllUSDRates() {
        return loadCurrencyDataFromFile();
    }

    public boolean isEmpty() {
        return mode == enMode.emptyMode;
    }

    public static Currency findByCode(String currencyCode) {
        return null;
    }

    public static boolean IsCurrencyExist(String currencyCode) {
        Currency C1 = Currency.findByCode(currencyCode);
        return (!C1.isEmpty());

    }

    public static List <Currency> GetCurrenciesList() {
        return loadCurrencyDataFromFile();
    }
}
