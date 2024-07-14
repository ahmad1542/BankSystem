import java.util.Scanner;

public class UpdateClientScreen extends Screen {
    private static void readClientInfo(BankClient client) {
        System.out.println("\nEnter first name: ");
        client.setFirstName(Utility.readString());
        System.out.println("\nEnter last name: ");
        client.setLastName(Utility.readString());
        System.out.println("\nEnter email: ");
        client.setEmail(Utility.readString());
        System.out.println("\nEnter phone: ");
        client.setPhoneNumber(Utility.readString());
        System.out.println("\nEnter pin code: ");
        client.setPinCode(Utility.readString());
        System.out.println("\nEnter account balance: ");
        client.setBalance(Utility.readDouble());
    }

    public static void showUpdateClientScreen() {
        drawScreenHeader("\tUpdate Client Screen", "");

        String accountNumber = "";
        System.out.println("\nPlease enter client account number: ");
        accountNumber = Utility.readString();
        while (!BankClient.isClientExist(accountNumber)) {
            System.out.println("\nAccount number is not found, choose another one: ");
            accountNumber = Utility.readString();
        }

        BankClient client = BankClient.find(accountNumber);
        System.out.println(client);

        char answer = 'n';
        System.out.print("\nAre you sure you want to update this client y/n? ");
        answer = new Scanner(System.in).next().charAt(0);

        if (answer == 'y' || answer == 'Y') {
            System.out.println("\n\nUpdate client info:");
            System.out.println("\n____________________");

            readClientInfo(client);

            BankClient.enSaveResults saveResults;
            saveResults = client.save();

            switch (saveResults) {
                case BankClient.enSaveResults.svSucceeded -> {
                    System.out.println("\nAccount Updated Successfully :-)");
                    System.out.println(client);
                }
                case BankClient.enSaveResults.svFailedEmptyObject -> {
                    System.out.println("Error account was not saved because it's Empty");
                }
            }
        }
    }
}
