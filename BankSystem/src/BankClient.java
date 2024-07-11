import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.util.List;

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

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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

    private static Vector <BankClient> loadClientsDataFromFile() {
        Vector<BankClient> vClient = new Vector<>();
        try (Scanner fileScanner = new Scanner(new FileReader("D:\\GitHub Repository\\Programming-Save\\Java\\Clients.txt"))) {
            String line = fileScanner.nextLine();
            while (fileScanner.hasNextLine()) {
                BankClient client = convertLineToClientObject(line);
                vClient.add(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vClient;
    }

    private void update() {
        List <BankClient> lClient;
        lClient = loadClientsDataFromFile();
        for(int i = 0; i <= lClient.size(); i++) {
            if(lClient.get(i).getAccountNumber().equals(this.getAccountNumber())) {
                lClient.set(i, this);
                break;
            }
        }
        saveClientsDataToFile(lClient);
    }

    private void saveClientsDataToFile(List<BankClient> clients) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Clients.txt"))) {
            for (BankClient client : clients) {
                bw.write(client.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toFileString() {
        return getFirstName() + "#//#" + getLastName() + "#//#" + getEmail() + "#//#" + getPhoneNumber() +
                "#//#" + accountNumber + "#//#" + pinCode + "#//#" + balance;
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

    public static String readString() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine().trim();
        return input;
    }

    enum enSaveResults {
        svFailedEmptyObject,
        svSucceeded
    }

    enSaveResults save() {
        switch (mode) {
            case enMode.emptyMode -> {
                return enSaveResults.svFailedEmptyObject;
            }
            default ->   {
                return enSaveResults.svSucceeded;
            }
        }
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