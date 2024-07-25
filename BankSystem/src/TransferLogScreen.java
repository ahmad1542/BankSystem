import java.util.List;

public class TransferLogScreen extends Screen {

    private static void printLoginRegisterUsersRecordLine(BankClient.TransferLogRecord client) {
        System.out.format("%-5s| %-32s| %-8s| %-8s| %-8s| %-10s| %-10s| %-8s%n", "", client.getDateTime(), client.getFromClientAccNum(), client.getToClientAccNum(), client.getAmount(),
                client.getFromClientBalance(), client.getToClientBalance(), client.getUserName());
    }

    public static void showTransferLogScreen() {

        List<BankClient.TransferLogRecord> lClients = BankClient.getTransferLogList();

        String subTitle = "\t(" + lClients.size() + ") User(s).";
        drawScreenHeader("\t  Transfer Log List Screen" , subTitle);

        System.out.format("%-8s", "");
        System.out.println("\n\t________________________________________________________________________________________________\n");

        System.out.format("%-5s| %-32s| %-8s| %-8s| %-8s| %-10s| %-10s| %-8s", "", "Date/Time", "f.Acct", "t.Acct", "Amount", "f.Balance", "t.Balance", "User");

        System.out.format("%-8s", "");
        System.out.println("\n\t________________________________________________________________________________________________\n");
        for (BankClient.TransferLogRecord client: lClients)
            printLoginRegisterUsersRecordLine(client);

        System.out.println("\n\t________________________________________________________________________________________________\n");
    }
}
