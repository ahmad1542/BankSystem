import java.util.Scanner;

public class TransfersScreen extends Screen {
    private static String readAccountNumber(String Message) {
        System.out.print(Message);
        String accountNumber = Utility.readString();
        while (!BankClient.isClientExist(accountNumber)) {
            System.out.print("\nAccount number is not found, choose another one: ");
            accountNumber = Utility.readString();
        }
        return accountNumber;
    }

    public static void showTransfersScreen() {
        drawScreenHeader("\t  Transfer Screen", "");

        Scanner scan = new Scanner(System.in);
        String accountNumber = readAccountNumber("\nPlease enter account number to transfer from: ");

        BankClient fromClient = BankClient.find(accountNumber);
        System.out.println(fromClient);

        accountNumber = readAccountNumber("\nPlease enter account number to transfer to: ");

        BankClient toClient = BankClient.find(accountNumber);
        System.out.println(toClient);

        System.out.println("\nEnter transfer amount: ");
        double transferAmount = Utility.readDoubleNumber();

        while (transferAmount > fromClient.getBalance()) {
            System.out.println("Amount exceeds the available balance, Enter another amount: ");
            transferAmount = Utility.readDoubleNumber();
        }
        System.out.println("Are you sure you want to perform this operation? y/n: ");
        char answer = scan.next().charAt(0);
        if (answer == 'y' || answer == 'Y') {
            System.out.println("Transfer done successfully");
            fromClient.withdraw(transferAmount);
            toClient.deposit(transferAmount);
            System.out.println(fromClient);
            System.out.println(toClient);
        }
    }
}
