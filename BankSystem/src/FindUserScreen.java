public class FindUserScreen extends Screen {
    public static void showFindUserScreen() {
        drawScreenHeader("\tFind User Screen", "");

        String userName;
        System.out.print("\nPlease Enter username: ");
        userName = Utility.readString();
        while (!User.isUserExist(userName)) {
            System.out.print("\nUser is not found, choose another one: ");
            userName = Utility.readString();
        }
        User user = User.find(userName);

        if (user.isEmpty()) {
            System.out.println("\nUser Was not Found :-(");
        }
        else
            System.out.println("\nUser Found :-)\n");
        System.out.println(user);
    }
}
