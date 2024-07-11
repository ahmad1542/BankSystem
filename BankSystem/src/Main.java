public class Main {
    public static void main(String[] args) {
        BankClient client1 = new BankClient();
        System.out.println(client1.find("A10"));
        System.out.println(BankClient.isClientExist("A10"));

    }
}