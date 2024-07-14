import java.util.List;

public class ClientListScreen extends Screen {
    private static void printClientRecordLine(BankClient client) {
        System.out.printf("| %-15s", client.getAccountNumber());
        System.out.printf("| %-20s", client.getFullName());
        System.out.printf("| %-12s", client.getPhoneNumber());
        System.out.printf("| %-25s", client.getEmail());
        System.out.printf("| %-10s", client.getPinCode());
        System.out.printf("| %-12.2f", client.getBalance());
        System.out.println();
    }

    public static void showClientsList() {
        List<BankClient> clients = BankClient.getClientsList();

        String title = "\t  Client List Screen";
        String subTitle = "(" + clients.size() + ") " + "Client(s).";

        drawScreenHeader(title, subTitle);

        System.out.print("_______________________________________________________");
        System.out.println("______________________________________________\n");

        System.out.printf("| %-15s", "Account Number");
        System.out.printf("| %-20s", "Client Name");
        System.out.printf("| %-12s", "Phone");
        System.out.printf("| %-25s", "Email");
        System.out.printf("| %-10s", "Pin Code");
        System.out.printf("| %-12s", "Balance");
        System.out.println();
        System.out.print("_______________________________________________________");
        System.out.println("______________________________________________\n");

        if (clients.isEmpty()) {
            System.out.println("\t\t\t\tNo Clients Available In the System!");
        } else {
            for (BankClient client : clients) {
                printClientRecordLine(client);
            }
        }

        System.out.print("_______________________________________________________");
        System.out.println("______________________________________________\n");
    }
}
