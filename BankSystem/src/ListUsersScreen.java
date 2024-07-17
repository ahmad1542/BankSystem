import java.util.List;

public class ListUsersScreen extends Screen {

    private static void printUserRecordLine(User user) {
        System.out.format("%-8s| %-12s| %-25s| %-12s| %-20s| %-10s| %-12s%n", "", user.getUserName(), user.getFullName(), user.getPhoneNumber(), user.getEmail(), user.getPassword(), user.getPermissions());
    }

    public static void showUsersList() {
        List <User> lUsers = User.getUsersList();

        String title = "\t  User List Screen";
        String subTitle = "\t(" + lUsers.size() + ") User(s).";

        drawScreenHeader(title, subTitle);

        System.out.format("%-8s%n\t__________________________________________________________________________________________________________%n", "");
        System.out.format("%-8s| %-12s| %-25s| %-12s| %-20s| %-10s| %-12s", "", "UserName", "Full Name", "Phone", "Email", "Password", "Permissions");
        System.out.format("%-8s%n\t__________________________________________________________________________________________________________%n", "");

        if (lUsers.isEmpty()) {
            System.out.println("\t\t\t\tNo Users Available In the System!");
        } else {
            for (User user : lUsers) {
                printUserRecordLine(user);
            }
        }

        System.out.print("\t__________________________________________________________________________________________________________\n");

    }
}
