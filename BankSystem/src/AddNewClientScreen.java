public class AddNewClientScreen extends Screen {
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
        client.setBalance(Utility.readDoubleNumber());
    }

    public static void ShowAddNewClientScreen() {

        if (!checkAccessRights(User.enPermissions.pAddNewClient)) {
            return;
        }

        drawScreenHeader("\t  Add New Client Screen", "");

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
}
