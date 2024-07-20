import java.util.Scanner;

public class LoginScreen extends Screen {
    private static void login() {
        boolean loginFailed = false;
        String userName, password;
        Scanner scan = new Scanner(System.in);
        do {
            if (loginFailed) {
                System.out.println("\nInvalid Username/Password!\n");
            }
            System.out.print("Enter Username: ");
            userName = scan.nextLine();
            System.out.print("Enter Password: ");
            password = scan.nextLine();

            User.currentUser = User.find(userName, password);

            loginFailed = User.currentUser.isEmpty();
        } while (loginFailed);
        MainScreen.showMainMenu();
    }

    public static void showLoginScreen() {
        Utility.clearConsole();
        drawScreenHeader("\t  Login Screen", "");
        login();
    }
}
