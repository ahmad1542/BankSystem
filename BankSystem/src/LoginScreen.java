import java.util.Scanner;

public class LoginScreen extends Screen {
    private static boolean login() {
        boolean loginFailed = false;
        int failedLoginCount = 0;
        String userName, password;
        Scanner scan = new Scanner(System.in);
        do {
            if (loginFailed) {
                failedLoginCount++;
                System.out.println("\nInvalid Username/Password!");
                System.out.println("You have " + (3 - failedLoginCount) + " trials to login");
            }

            if (failedLoginCount == 3) {
                System.out.println("\n\nYou are locked after 3 failed trials");
                return false;
            }

            System.out.print("Enter Username: ");
            userName = scan.nextLine();
            System.out.print("Enter Password: ");
            password = scan.nextLine();

            User.currentUser = User.find(userName, password);

            loginFailed = User.currentUser.isEmpty();
        } while (loginFailed);
        User.currentUser.registerLogIn();
        MainScreen.showMainMenu();
        return true;
    }

    public static boolean showLoginScreen() {
        Utility.clearConsole();
        drawScreenHeader("\t  Login Screen", "");
        return login();
    }
}
