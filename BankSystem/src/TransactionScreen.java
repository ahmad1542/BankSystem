import java.util.Scanner;

public class TransactionScreen extends Screen {
    private enum enTransactionMenuOptions {
        eDeposit,
        eWithdraw,
        eShowTotalBalance,
        eTransfers,
        eTransferLog,
        eShowMainMenu
    }

    private static enTransactionMenuOptions readTransactionsMenuOption() {
        System.out.printf("%-37s%s", "", "Choose what do you want to do? [1 to 6]? ");
        short input = Utility.readShortNumberBetween((short) 1, (short) 6);
        switch (input) {
            case 1 -> {
                return enTransactionMenuOptions.eDeposit;
            }
            case 2 -> {
                return enTransactionMenuOptions.eWithdraw;
            }
            case 3 -> {
                return enTransactionMenuOptions.eShowTotalBalance;
            }
            case 4 -> {
                return enTransactionMenuOptions.eTransfers;
            }
            case 5 -> {
                return enTransactionMenuOptions.eTransferLog;
            }
            case 6 -> {
                return enTransactionMenuOptions.eShowMainMenu;
            }
            default -> {
                throw new IllegalArgumentException("Invalid menu option");
            }
        }
    }

    private static void showDepositScreen() {
        DepositScreen.showDepositScreen();
    }
    private static void showWithdrawScreen() {
        WithdrawScreen.showWithdrawScreen();
    }
    private static void showTotalBalancesScreen() {
        TotalBlancesScreen.showTotalBalancesScreen();
    }
    private static void showTransfersScreen() {
        TransfersScreen.showTransfersScreen();
    }
    private static void showTransfersLogScreen() {
        TransferLogScreen.showTransferLogScreen();
    }
    private static void goBackToTransactionsMenu() {
        System.out.println("\n\nPress any key to go back to Transactions Menue...");
        new Scanner(System.in).nextLine();
        showTransactionsMenu();
    }

    private static void performTransactionsMenuOption(enTransactionMenuOptions transactionsMenuOption) {
        switch (transactionsMenuOption) {
            case eDeposit -> {
                Utility.clearConsole();
                showDepositScreen();
                goBackToTransactionsMenu();
                break;
            }
            case eWithdraw -> {
                Utility.clearConsole();
                showWithdrawScreen();
                goBackToTransactionsMenu();
                break;
            }
            case eShowTotalBalance -> {
                Utility.clearConsole();
                showTotalBalancesScreen();
                goBackToTransactionsMenu();
                break;
            }
            case eTransfers -> {
                Utility.clearConsole();
                showTransfersScreen();
                goBackToTransactionsMenu();
                break;
            }
            case eTransferLog -> {
                Utility.clearConsole();
                showTransfersLogScreen();
                goBackToTransactionsMenu();
                break;
            }
            case eShowMainMenu -> {
                Utility.clearConsole();
                MainScreen.showMainMenu();
            }
        }
    }

    public static void showTransactionsMenu() {

        if (!checkAccessRights(User.enPermissions.pTransactions)) {
            return;
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();

        Utility.clearConsole();
        drawScreenHeader("\t  Transactions Screen", "");

        System.out.printf("%-37s%s", "", "===========================================\n");
        System.out.printf("%-37s%s", "", "\t\t  Transactions Menu\n");
        System.out.printf("%-37s%s", "", "===========================================\n");
        System.out.printf("%-37s%s", "", "\t[1] Deposit.\n");
        System.out.printf("%-37s%s", "", "\t[2] Withdraw.\n");
        System.out.printf("%-37s%s", "", "\t[3] Total Balances.\n");
        System.out.printf("%-37s%s", "", "\t[4] Transfer.\n");
        System.out.printf("%-37s%s", "", "\t[5] Transfer Log.\n");
        System.out.printf("%-37s%s", "", "\t[6] Main Menu.\n");
        System.out.printf("%-37s%s", "", "===========================================\n");

        performTransactionsMenuOption(readTransactionsMenuOption());
    }
}