import java.util.Scanner;

public class MainScreen extends Screen {
    private enum enMainMenuOptions {
        eListClients,
        eAddNewClient,
        eDeleteClient,
        eUpdateClient,
        eFindClient,
        eShowTransactionsMenu,
        eManageUsers,
        eExit
    }

    private static enMainMenuOptions readMainMenuOption() {
        System.out.printf("%-37s%s", "", "Choose what do you want to do? [1 to 8]? ");
        short choice = Utility.readShortNumberBetween((short) 1, (short) 8);
        switch (choice) {
            case 1 -> {
                return enMainMenuOptions.eListClients;
            }
            case 2 -> {
                return enMainMenuOptions.eAddNewClient;
            }
            case 3 -> {
                return enMainMenuOptions.eDeleteClient;
            }
            case 4 -> {
                return enMainMenuOptions.eUpdateClient;
            }
            case 5 -> {
                return enMainMenuOptions.eFindClient;
            }
            case 6 -> {
                return enMainMenuOptions.eShowTransactionsMenu;
            }
            case 7 -> {
                return enMainMenuOptions.eManageUsers;
            }
            default ->  {
                return enMainMenuOptions.eExit;
            }
        }
    }

    public static void goBackToMainMenu() {
        System.out.printf("%-37s%s\n", "", "\n\tPress any key to go back to Main Menu...\n");
        new Scanner(System.in).nextLine(); // Wait for the user to press Enter
        showMainMenu();
    }

    private static void showAllClientsScreen() {
        ClientListScreen.showClientsList();
    }
    private static void showAddNewClientsScreen() {
        AddNewClientScreen.ShowAddNewClientScreen();
    }
    private static void showDeleteClientScreen() {
        DeleteClientScreen.showDeleteClientScreen();
    }
    private static void showUpdateClientScreen() {
        UpdateClientScreen.showUpdateClientScreen();
    }
    private static void showFindClientScreen() {
        FindClientScreen.showFindClientScreen();
    }
    private static void showTransactionsMenue() {
        TransactionScreen.showTransactionsMenu();
    }
    private static void showManageUsersMenue() {
        System.out.println("\nUsers Menu Will be here...\n");
    }
    private static void showEndScreen() {
        System.out.println("\nEnd Screen Will be here...\n");
    }

    private static void performMainMenuOption(enMainMenuOptions mainMenuOptions) {
        switch (mainMenuOptions) {
            case enMainMenuOptions.eListClients -> {
                Utility.clearConsole();
                showAllClientsScreen();
                goBackToMainMenu();
                break;
            }
            case enMainMenuOptions.eAddNewClient -> {
                Utility.clearConsole();
                showAddNewClientsScreen();
                goBackToMainMenu();
                break;
            }
            case enMainMenuOptions.eDeleteClient -> {
                Utility.clearConsole();
                showDeleteClientScreen();
                goBackToMainMenu();
                break;
            }
            case enMainMenuOptions.eUpdateClient -> {
                Utility.clearConsole();
                showUpdateClientScreen();
                goBackToMainMenu();
                break;
            }
            case enMainMenuOptions.eFindClient -> {
                Utility.clearConsole();
                showFindClientScreen();
                goBackToMainMenu();
                break;
            }
            case enMainMenuOptions.eShowTransactionsMenu -> {
                Utility.clearConsole();
                showTransactionsMenue();
                break;
            }
            case enMainMenuOptions.eManageUsers -> {
                Utility.clearConsole();
                showManageUsersMenue();
                break;
            }
            case enMainMenuOptions.eExit -> {
                Utility.clearConsole();
                showEndScreen();
                break;
            }
        }
    }

    public static void showMainMenu() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        drawScreenHeader("\t\tMain Screen", "");

        System.out.printf("%-37s%s\n", "", "===========================================");
        System.out.printf("%-37s%s\n", "", "\t\t\t\tMain Menu");
        System.out.printf("%-37s%s\n", "", "===========================================");
        System.out.printf("%-37s%s\n", "", "\t[1] Show Client List.");
        System.out.printf("%-37s%s\n", "", "\t[2] Add New Client.");
        System.out.printf("%-37s%s\n", "", "\t[3] Delete Client.");
        System.out.printf("%-37s%s\n", "", "\t[4] Update Client Info.");
        System.out.printf("%-37s%s\n", "", "\t[5] Find Client.");
        System.out.printf("%-37s%s\n", "", "\t[6] Transactions.");
        System.out.printf("%-37s%s\n", "", "\t[7] Manage Users.");
        System.out.printf("%-37s%s\n", "", "\t[8] Logout.");
        System.out.printf("%-37s%s\n", "", "===========================================");

        performMainMenuOption(readMainMenuOption());
    }
}