import java.util.Scanner;

public class DeleteUserScreen extends Screen {
    public static void showDeleteUserScreen() {

        drawScreenHeader("\t  Delete User Screen", "");

        String userName = "";
        System.out.println("\nPlease enter user username: ");
        userName = Utility.readString();
        while (!User.isUserExist(userName)) {
            System.out.println("\nUser is not found, Choose another one: ");
            userName = Utility.readString();
        }

        User user = User.find(userName);
        System.out.println(user);

        System.out.println("\nAre you sure you want to delete this user y/n? ");

        char answer;
        Scanner answerScan = new Scanner(System.in);
        answer = answerScan.next().charAt(0);

        if (answer == 'y' || answer == 'Y') {
            if (user.delete())
            {
                System.out.println("\nClient Deleted Successfully :-)\n");

                System.out.println(user);
            }
            else
            {
                System.out.println("\nError Client Was not Deleted\n");
            }
        }
    }
}
