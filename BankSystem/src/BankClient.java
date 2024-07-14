import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.util.List;

public class BankClient extends Person {
    private enum enMainMenueOptions {
        eListClients,
        eAddNewClient,
        eDeleteClient,
        eUpdateClient,
        eFindClient,
        eShowTransactionsMenue,
        eManageUsers,
        eExit
    }
    private enum enMode {
        emptyMode,
        updateMode,
        addNewMode
    }
    private enMode mode;
    private String accountNumber;
    private String pinCode;
    private double balance;
    private boolean markedForDelete;

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

    public boolean isMarkedForDelete() {
        return markedForDelete;
    }

    public static BankClient find(String accountNumber) {
        try (Scanner fileScanner = new Scanner(new FileReader("Clients.txt"))) {
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
        try (Scanner fileScanner = new Scanner(new FileReader("Clients.txt"))) {
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

    private void addNew() {
        addDataLineToFile(convertClientObjectToLine(this));
    }

    private void addDataLineToFile(String dataLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Clients.txt", true))) {
            writer.newLine();
            writer.write(dataLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Vector <BankClient> loadClientsDataFromFile() {
        Vector<BankClient> vClient = new Vector<>();
        try (Scanner fileScanner = new Scanner(new FileReader("Clients.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
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
                if (!client.isMarkedForDelete()) {
                    bw.write(client.convertClientObjectToLine(client));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertClientObjectToLine(BankClient client) {
        return client.getFirstName() + "#//#" + client.getLastName() + "#//#" + client.getEmail() + "#//#" + client.getPhoneNumber() +
                "#//#" + client.getAccountNumber() + "#//#" + client.getPinCode() + "#//#" + client.getBalance();
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

    public boolean isEmpty() {
        return (this.mode == enMode.emptyMode);
    }

    private static BankClient getEmptyClientObject() {
        return new BankClient(enMode.emptyMode, "", "", "", "", "", "", 0.0);
    }

    public static boolean isClientExist(String accountNumber) {
        BankClient client = find(accountNumber);
        return (!client.isEmpty());
    }

    public boolean delete() {
        List <BankClient> lClients;
        lClients = loadClientsDataFromFile();

        for(BankClient client: lClients) {
            if (client.getAccountNumber().equals(accountNumber)) {
                client.markedForDelete = true;
                break;
            }
        }

        saveClientsDataToFile(lClients);

        this.copyFrom(getEmptyClientObject());

        return true;
    }

    private void copyFrom(BankClient other) {
        this.accountNumber = other.accountNumber;
        this.pinCode = other.pinCode;
        this.balance = other.balance;
        this.markedForDelete = other.markedForDelete;
        this.setFirstName(other.getFirstName());
        this.setLastName(other.getLastName());
        this.setEmail(other.getEmail());
        this.setPhoneNumber(other.getPhoneNumber());
    }

    public enum enSaveResults {
        svFailedEmptyObject,
        svSucceeded,
        svFaildAccountNumberExists
    }

    public enSaveResults save() {
        switch (mode) {
            case enMode.emptyMode -> {
                if (isEmpty())
                    return enSaveResults.svFailedEmptyObject;
            }
            case enMode.updateMode ->   {
                update();
                return enSaveResults.svSucceeded;
            }
            default -> {
                if (isClientExist(accountNumber))
                    return enSaveResults.svFaildAccountNumberExists;
                else {
                    addNew();
                    mode = enMode.updateMode;
                    return enSaveResults.svSucceeded;
                }
            }
        }
        return null;
    }

    static BankClient getAddNewClientObject(String AccountNumber) {
        return new BankClient(enMode.addNewMode, "", "", "", "", AccountNumber, "", 0);
    }

    public static List <BankClient> getClientsList() {
        return loadClientsDataFromFile();
    }

    public static double getTotalBalances() {
        List<BankClient> lCLient = getClientsList();
        double totalBalances = 0;

        for (BankClient client: lCLient) {
            totalBalances += client.getBalance();
        }
        return totalBalances;
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