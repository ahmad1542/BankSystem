import java.util.Scanner;

public class WithdrawScreen extends Screen {
    private static String readAccountNumber() {
        System.out.print("\nPlease Enter Account Number: ");
        String accountNumber = Utility.readString();
        while (!BankClient.isClientExist(accountNumber)) {
            System.out.print("\nAccount number is not found, choose another one: ");
            accountNumber = Utility.readString();
        }
        return accountNumber;
    }

    public static void showWithdrawScreen() {
        drawScreenHeader("\t   Withdraw Screen", "");

        String accountNumbner = readAccountNumber();

        BankClient client = BankClient.find(accountNumbner);
        System.out.println(client);

        double amount = 0;

        System.out.print("\nPlease enter withdraw amount? ");
        amount = Utility.readDoubleNumber();

        System.out.print("\nAre you sure you want to perform this transaction? ");
        char answer = 'n';
        answer = new Scanner(System.in).next().charAt(0);
        if (answer == 'Y' || answer == 'y') {
            if (client.withdraw(amount)) {
                System.out.println("\nAmount withdrew successfully.");
                System.out.println("\nNew balance is: " + client.getBalance());
            }
            else {
                System.out.println("\nCannot withdraw, Insufficient balance!");
                System.out.println("\nAmount to withdraw is: " + amount);
                System.out.println("\nYour balance is: " + client.getBalance());
            }
        }
        else
            System.out.println("Operation was cancelled.");
    }
}
