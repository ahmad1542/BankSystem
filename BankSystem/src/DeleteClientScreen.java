import java.util.Scanner;

public class DeleteClientScreen extends Screen {
    public static void showDeleteClientScreen() {

        drawScreenHeader("\t  Delete Client Screen", "");

        String accountNumber = "";
        System.out.println("\nPlease enter client account number: ");
        accountNumber = Utility.readString();
        while (!BankClient.isClientExist(accountNumber)) {
            System.out.println("\nAccount number is not found, Choose another one: ");
            accountNumber = Utility.readString();
        }

        BankClient client = BankClient.find(accountNumber);
        System.out.println(client);

        System.out.println("\nAre you sure you want to delete this client y/n? ");

        char answer;
        Scanner answerScan = new Scanner(System.in);
        answer = answerScan.next().charAt(0);

        if (answer == 'y' || answer == 'Y') {
            if (client.delete())
            {
                System.out.println("\nClient Deleted Successfully :-)\n");

                System.out.println(client);
            }
            else
            {
                System.out.println("\nError Client Was not Deleted\n");
            }
        }
    }

}
