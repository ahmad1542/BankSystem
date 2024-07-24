import java.util.List;

public class LoginRegisterScreen extends Screen {

    private static void printLoginRegisterUsersRecordLine(User.LoginRegisterRecord user) {
        System.out.format("%-8s| %-35s| %-12s| %-12s| %-20s%n", "", user.getDateTime(), user.getUserName(), user.getPassword(), user.getPermissions());
    }

    public static void showLoginRegister() {
        if (!checkAccessRights(User.enPermissions.pShowLoginRegister)) {
            return;
        }
        List<User.LoginRegisterRecord> lUsers = User.getLoginRegisterList();

        String subTitle = "\t(" + lUsers.size() + ") User(s).";
        drawScreenHeader("\t  Login Register Screen", subTitle);

        System.out.format("%-8s%n\t__________________________________________________________________________________________________________%n", "");
        System.out.format("%-8s| %-35s| %-12s| %-12s| %-20s", "", "Date/Time", "User Name", "Password", "Permissions");
        System.out.format("%-8s%n\t__________________________________________________________________________________________________________%n", "");

        if (lUsers.isEmpty()) {
            System.out.println("\t\t\t\tNo Users Already Registered In the System!");
        } else {
            for (User.LoginRegisterRecord user : lUsers) {
                printLoginRegisterUsersRecordLine(user);
            }
        }

        System.out.print("\t__________________________________________________________________________________________________________\n");
    }
}
