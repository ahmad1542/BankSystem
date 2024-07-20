import java.util.Scanner;

public class ManageUsersScreen extends Screen {
    private enum enManageUsersMenuOptions {
        eListUsers,
        eAddNewUser,
        eDeleteUser,
        eUpdateUser,
        eFindUser,
        eMainMenu
    }
    private static enManageUsersMenuOptions readManageUsersMenuOption() {
        System.out.printf("%-37s%s", "", " Choose what do you want to do? [1 to 6]? ");
        short input = Utility.readShortNumberBetween((short)1, (short)6);
        switch (input) {
            case 1 -> {
                return enManageUsersMenuOptions.eListUsers;
            }
            case 2 -> {
                return enManageUsersMenuOptions.eAddNewUser;
            }
            case 3 -> {
                return enManageUsersMenuOptions.eDeleteUser;
            }
            case 4 -> {
                return enManageUsersMenuOptions.eUpdateUser;
            }
            case 5 -> {
                return enManageUsersMenuOptions.eFindUser;
            }
            case 6 -> {
                return enManageUsersMenuOptions.eMainMenu;
            }
            default -> {
                throw new IllegalArgumentException("Invalid menu option");
            }
        }
    }

    private static void goBackToManageUsersMenu() {
        System.out.print("\n\nPress any key to go back to Manage Users Menu...");
        new Scanner(System.in).nextLine();
        showManageUsersMenu();
    }
    private static void showListUsersScreen() {
        ListUsersScreen.showUsersList();
    }
    private static void showAddNewUserScreen() {
        AddNewUserScreen.ShowAddNewUserScreen();
    }
    private static void showDeleteUserScreen() {
        DeleteUserScreen.showDeleteUserScreen();
    }
    private static void showUpdateUserScreen() {
        UpdateUserScreen.showUpdateUserScreen();
    }
    private static void showFindUserScreen() {
        FindUserScreen.showFindUserScreen();
    }

    private static void performManageUsersMenuOption(enManageUsersMenuOptions ManageUsersMenuOption) {
        switch (ManageUsersMenuOption) {
            case eListUsers -> {
                Utility.clearConsole();
                showListUsersScreen();
                goBackToManageUsersMenu();
                break;
            }
            case eAddNewUser -> {
                Utility.clearConsole();
                showAddNewUserScreen();
                goBackToManageUsersMenu();
                break;
            }
            case eDeleteUser -> {
                Utility.clearConsole();
                showDeleteUserScreen();
                goBackToManageUsersMenu();
                break;
            }
            case eUpdateUser -> {
                Utility.clearConsole();
                showUpdateUserScreen();
                goBackToManageUsersMenu();
                break;
            }
            case eFindUser -> {
                Utility.clearConsole();
                showFindUserScreen();
                goBackToManageUsersMenu();
                break;
            }
            case eMainMenu -> {

            }
        }
    }

    public static void showManageUsersMenu() {

        if (!checkAccessRights(User.enPermissions.pManageUsers)) {
            return;
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();

        Utility.clearConsole();
        drawScreenHeader("\t Manage Users Screen", "");

        System.out.printf("%-37s%s", "", "===========================================\n");
        System.out.printf("%-37s%s", "", "\t\t\t  Manage Users Menu\n");
        System.out.printf("%-37s%s", "", "===========================================\n");
        System.out.printf("%-37s%s", "", "\t[1] List Users.\n");
        System.out.printf("%-37s%s", "", "\t[2] Add New User.\n");
        System.out.printf("%-37s%s", "", "\t[3] Delete User.\n");
        System.out.printf("%-37s%s", "", "\t[4] Update User.\n");
        System.out.printf("%-37s%s", "", "\t[5] Find User.\n");
        System.out.printf("%-37s%s", "", "\t[6] Main Menu.\n");
        System.out.printf("%-37s%s", "", "===========================================\n");

        performManageUsersMenuOption(readManageUsersMenuOption());
    }
}
