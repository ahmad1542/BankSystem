import java.util.Scanner;

public class DepositScreen extends Screen {
    private static String readAccountNumber() {
        System.out.print("\nPlease Enter Account Number: ");
        String accountNumber = Utility.readString();
        while (!BankClient.isClientExist(accountNumber)) {
            System.out.print("\nAccount number is not found, choose another one: ");
            accountNumber = Utility.readString();
        }
        return accountNumber;
    }

    public static void showDepositScreen() {
        drawScreenHeader("\t   Deposit Screen", "");

        String accountNumbner = readAccountNumber();

        BankClient client = BankClient.find(accountNumbner);
        System.out.println(client);

        double amount = 0;

        System.out.print("\nPlease enter deposit amount? ");
        amount = Utility.readDoubleNumber();

        System.out.print("\nAre you sure you want to perform this amount? ");
        char answer = 'n';
        answer = new Scanner(System.in).next().charAt(0);
        if (answer == 'Y' || answer == 'y') {
            client.deposit(amount);
            System.out.println("\nAmount deposited successfully.");
            System.out.println("\nNew balance is: " + client.getBalance());
        }
        else
            System.out.println("Operation was cancelled.");
    }
}
