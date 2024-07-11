public class UpdateClient extends BankClient {

    private void ReadClientInfo(BankClient client) {
        System.out.println("\nEnter first name: ");
        client.setFirstName(readString());
        System.out.println("\nEnter last name: ");
        client.setLastName(readString());
        System.out.println("\nEnter email: ");
        client.setEmail(readString());
        System.out.println("\nEnter phone: ");
        client.setPhoneNumber(readString());
        System.out.println("\nEnter pin code: ");
        client.setPinCode(readString());
        System.out.println("\nEnter account balance: ");
        client.setAccountNumber(readString());
    }

    public void updateClient() {
        String accountNumber = "";
        System.out.println("\nPlease enter client account number: ");
        accountNumber = readString();
        while (!isClientExist(accountNumber)) {
            System.out.println("\nAccount number is not found, choose another one: ");
            accountNumber = readString();
        }

        BankClient client = find(accountNumber);
        toString();

        System.out.println("\n\nUpdate client info:");
        System.out.println("\n____________________");
    }
}
