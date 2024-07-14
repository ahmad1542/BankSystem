public class FindClientScreen extends Screen {
    public static void showFindClientScreen() {
        drawScreenHeader("\tFind Client Screen", "");

        String accountNumber;
        System.out.print("\nPlease Enter Account Number: ");
        accountNumber = Utility.readString();
        while (!BankClient.isClientExist(accountNumber)) {
            System.out.print("\nAccount number is not found, choose another one: ");
            accountNumber = Utility.readString();
        }
        BankClient client = BankClient.find(accountNumber);

        if (client.isEmpty()) {
            System.out.println("\nClient Was not Found :-(");
        }
        else
            System.out.println("\nClient Found :-)\n");
        System.out.println(client);
    }
}
