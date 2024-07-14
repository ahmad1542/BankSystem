import java.util.List;
import java.util.Vector;

public class Main {

    void printClientRecordBalanceLine(BankClient client) {
        System.out.printf("| %-15s", client.getAccountNumber());
        System.out.printf("| %-40s", client.getFullName());
        System.out.printf("| %-12.2f", client.getBalance());
        System.out.println();
    }

    void showTotalBalances() {
        List<BankClient> lClient = new Vector<>();
        lClient = BankClient.getClientsList();

        System.out.println("\n\t\t\t\t\tBalances List (" + lClient.size() + ") Client(s).");
        System.out.print("\n_______________________________________________________");
        System.out.println("_________________________________________\n");

        System.out.printf("| %-15s", "Accout Number");
        System.out.printf("| %-40s", "Client Name");
        System.out.printf("| %-12s", "Balance");
        System.out.print("\n_______________________________________________________");
        System.out.println("_________________________________________\n");

        double totalBalances = BankClient.getTotalBalances();

        if (lClient.isEmpty())
            System.out.println("\t\t\t\tNo Clients Available In the System!");
        else
            for (BankClient client: lClient) {
                printClientRecordBalanceLine(client);
            }

        System.out.print("\n_______________________________________________________");
        System.out.println("_________________________________________");
        System.out.println("\t\t\t\t\t   Total Balances = " + totalBalances);
        System.out.println("\t\t\t\t\t   ( " + Utility.numberToText((int) totalBalances) + ")");
    }

    public static void main(String[] args) {
        MainScreen.showMainMenu();
    }
}
