import java.util.List;
import java.util.Vector;

public class TotalBlancesScreen extends Screen {

    private static void printClientRecordBalanceLine(BankClient client) {
        System.out.printf("\t\t | %-15s", client.getAccountNumber());
        System.out.printf("\t\t | %-40s", client.getFullName());
        System.out.printf("\t\t | %-12.2f", client.getBalance());
        System.out.println();
    }

    public static void showTotalBalancesScreen() {
        List <BankClient> lClients = BankClient.getClientsList();
        drawScreenHeader("\t   Balances List Screen",("\t  (" + lClients.size() + ") Client(s)."));

        System.out.print("\n\t\t_______________________________________________________");
        System.out.println("_________________________________________\n");

        System.out.printf("\t\t | %-15s", "Account Number");
        System.out.printf("\t\t | %-40s", "Client Name");
        System.out.printf("\t\t | %-12s", "Balance");
        System.out.print("\n\t\t_______________________________________________________");
        System.out.println("_________________________________________\n");

        double totalBalances = BankClient.getTotalBalances();

        if (lClients.isEmpty())
            System.out.println("\t\t\t\tNo Clients Available In the System!");
        else
            for (BankClient client: lClients) {
                printClientRecordBalanceLine(client);
            }

        System.out.print("\n\t\t_______________________________________________________");
        System.out.println("_________________________________________");
        System.out.println("\t\t\t\t\t\t\t   Total Balances = " + totalBalances);
        System.out.println("\t\t\t\t\t\t\t   ( " + Utility.numberToText((int) totalBalances) + ")");
    }
}

