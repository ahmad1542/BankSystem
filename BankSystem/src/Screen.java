import java.util.Objects;

public class Screen {
    protected static void drawScreenHeader(String title, String subTitle) {
        System.out.print("\t\t\t\t\t\t\t\t\t   _______________________________________");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t" + title);
        if (!Objects.equals(subTitle, ""))
        {
            System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t   " + subTitle);
        }
        System.out.print("\n\t\t\t\t\t\t\t\t\t   _______________________________________\n\n");
    }

    protected static boolean checkAccessRights(User.enPermissions permission) {
        if (!User.currentUser.checkAccessPermission(permission)) {
            System.out.print("\t\t\t\t\t______________________________________");
            System.out.print("\n\n\t\t\t\t\t  Access Denied! Contact your Admin.");
            System.out.print("\n\t\t\t\t\t______________________________________\n\n");
            return false;
        }
        else
            return true;
    }
}
