import java.util.List;
import java.util.Scanner;

public class Main {

    void readClientInfo(BankClient client) {
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

    void updateClient() {
        String accountNumber = "";
        System.out.println("\nPlease enter client account number: ");
        accountNumber = Utility.readString();
        while (!BankClient.isClientExist(accountNumber)) {
            System.out.println("\nAccount number is not found, choose another one: ");
            accountNumber = Utility.readString();
        }

        BankClient client = BankClient.find(accountNumber);
        System.out.println(client);

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

    void addNewClient() {
        String accountNumber = "";
        System.out.println("\nPlease enter client account number: ");
        accountNumber = Utility.readString();
        while (BankClient.isClientExist(accountNumber)) {
            System.out.println("\nAccount number is already used, Choose another one: ");
            accountNumber = Utility.readString();
        }

        BankClient newClient = BankClient.getAddNewClientObject(accountNumber);

        readClientInfo(newClient);

        BankClient.enSaveResults saveResults;
        saveResults = newClient.save();

        switch (saveResults) {
            case BankClient.enSaveResults.svSucceeded -> {
                System.out.println("\nAccount Addeded Successfully :-)");
                System.out.println(newClient);
            }
            case BankClient.enSaveResults.svFailedEmptyObject -> {
                System.out.println("\nError account was not saved because it's Empty");
            }
            case BankClient.enSaveResults.svFaildAccountNumberExists -> {
                System.out.println("\nError account was not saved because account number is used!");
            }
        }

    }

    void deleteClient() {
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

    void printClientRecordLine(BankClient client) {
        System.out.printf("| %-15s", client.getAccountNumber());
        System.out.printf("| %-20s", client.getFullName());
        System.out.printf("| %-12s", client.getPhoneNumber());
        System.out.printf("| %-25s", client.getEmail());
        System.out.printf("| %-10s", client.getPinCode());
        System.out.printf("| %-12.2f", client.getBalance());
        System.out.println();
    }

    void showClientsList() {
        List<BankClient> clients = BankClient.getClientsList();

        System.out.printf("\n\t\t\t\t\tClient List (%d) Client(s).\n", clients.size());
        System.out.print("_______________________________________________________");
        System.out.println("_________________________________________\n");

        System.out.printf("| %-15s", "Account Number");
        System.out.printf("| %-20s", "Client Name");
        System.out.printf("| %-12s", "Phone");
        System.out.printf("| %-25s", "Email");
        System.out.printf("| %-10s", "Pin Code");
        System.out.printf("| %-12s", "Balance");
        System.out.println();
        System.out.print("_______________________________________________________");
        System.out.println("_________________________________________\n");

        if (clients.isEmpty()) {
            System.out.println("\t\t\t\tNo Clients Available In the System!");
        } else {
            for (BankClient client : clients) {
                printClientRecordLine(client);
            }
        }

        System.out.print("_______________________________________________________");
        System.out.println("_________________________________________\n");
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.showClientsList();
    }
}