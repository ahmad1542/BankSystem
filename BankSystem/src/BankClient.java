import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BankClient extends Person {
    private enum enMode {
        emptyMode,
        updateMode
    }
    private enMode mode;
    private String accountNumber;
    private String pinCode;
    private double balance;

    public BankClient() {

    }

    public BankClient(enMode mode, String firstName, String lastName, String email, String phoneNumber,
                      String accountNumber, String pinCode, double balance) {
        super(firstName, lastName, email, phoneNumber);
        this.mode = mode;
        this.accountNumber = accountNumber;
        this.pinCode = pinCode;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public double getBalance() {
        return balance;
    }

    public static BankClient find(String accountNumber) {
        try (Scanner fileScanner = new Scanner(new FileReader("D:\\GitHub Repository\\Programming-Save\\Java\\Clients.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                BankClient client = convertLineToClientObject(line);

                if (client.getAccountNumber().equals(accountNumber))
                    return client;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getEmptyClientObject();
    }

    public static BankClient find(String accountNumber, String pinCode) {
        try (Scanner fileScanner = new Scanner(new FileReader("D:\\GitHub Repository\\Programming-Save\\Java\\Clients.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                BankClient client = convertLineToClientObject(line);

                if (client.getAccountNumber().equals(accountNumber) && client.getPinCode().equals(pinCode))
                    return client;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getEmptyClientObject();
    }

    private static BankClient convertLineToClientObject(String line) {
        Scanner lineScanner = new Scanner(line);
        lineScanner.useDelimiter("#//#");

        String firstName = lineScanner.next();
        String lastName = lineScanner.next();
        String email = lineScanner.next();
        String phoneNumber = lineScanner.next();
        String accountNumber = lineScanner.next();
        String pinCode = lineScanner.next();
        double  balance = lineScanner.nextDouble();

        lineScanner.close();

        return new BankClient(enMode.updateMode, firstName, lastName, email, phoneNumber, accountNumber, pinCode, balance);
    }

    private boolean isEmpty() {
        return (this.mode == enMode.emptyMode);
    }

    private static BankClient getEmptyClientObject() {
        return new BankClient(enMode.emptyMode, "", "", "", "", "", "", 0.0);
    }

    public static boolean isClientExist(String accountNumber) {
        BankClient client = find(accountNumber);
        return (!client.isEmpty());
    }

    @Override
    public String toString() {
        return "BankClient{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", balance=" + balance +
                '}';
    }
}