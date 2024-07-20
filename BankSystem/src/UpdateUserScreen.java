import java.util.Scanner;

public class UpdateUserScreen extends Screen {
    private static void readUserInfo(User user) {
        System.out.println("\nEnter first name: ");
        user.setFirstName(Utility.readString());
        System.out.println("\nEnter last name: ");
        user.setLastName(Utility.readString());
        System.out.println("\nEnter email: ");
        user.setEmail(Utility.readString());
        System.out.println("\nEnter phone: ");
        user.setPhoneNumber(Utility.readString());
        System.out.println("\nEnter password: ");
        user.setPassword(Utility.readString());
        System.out.println("\nEnter Permission: ");
        user.setPermissions(readPermissionsToSet());
    }

    private static int readPermissionsToSet() {
        int permissions = 0;
        char answer = 'n';
        System.out.print("\nDo you want to give full access? y/n? ");
        Scanner scan = new Scanner(System.in);
        answer = scan.next().charAt(0);
        if (answer == 'y' || answer == 'Y') {
            return -1;
        }

        System.out.print("\nDo you want to give access to : \n ");

        System.out.print("\nShow Client List? y/n? ");
        answer = scan.next().charAt(0);
        if (answer == 'y' || answer == 'Y') {
            permissions += 1;
        }

        System.out.print("\nAdd New Client? y/n? ");
        answer = scan.next().charAt(0);
        if (answer == 'y' || answer == 'Y') {
            permissions += 2;
        }

        System.out.print("\nDelete Client? y/n? ");
        answer = scan.next().charAt(0);
        if (answer == 'y' || answer == 'Y') {
            permissions += 4;
        }

        System.out.print("\nUpdate Client? y/n? ");
        answer = scan.next().charAt(0);
        if (answer == 'y' || answer == 'Y') {
            permissions += 8;
        }

        System.out.print("\nFind Client? y/n? ");
        answer = scan.next().charAt(0);
        if (answer == 'y' || answer == 'Y') {
            permissions += 16;
        }

        System.out.print("\nTransactions? y/n? ");
        answer = scan.next().charAt(0);
        if (answer == 'y' || answer == 'Y') {
            permissions += 32;
        }

        System.out.print("\nManage Users? y/n? ");
        answer = scan.next().charAt(0);
        if (answer == 'y' || answer == 'Y') {
            permissions += 64;
        }

        return permissions;
    }

    public static void showUpdateUserScreen() {
        drawScreenHeader("\tUpdate User Screen", "");

        String userName = "";
        System.out.println("\nPlease enter username: ");
        userName = Utility.readString();
        while (!User.isUserExist(userName)) {
            System.out.println("\nUser is not found, choose another one: ");
            userName = Utility.readString();
        }

        User user = User.find(userName);
        System.out.println(user);

        char answer = 'n';
        System.out.print("\nAre you sure you want to update this user y/n? ");
        answer = new Scanner(System.in).next().charAt(0);

        if (answer == 'y' || answer == 'Y') {
            System.out.println("\n\nUpdate user info:");
            System.out.println("\n____________________");

            readUserInfo(user);

            User.enSaveResults saveResults;
            saveResults = user.save();

            switch (saveResults) {
                case User.enSaveResults.svSucceeded -> {
                    System.out.println("\nUser Updated Successfully :-)");
                    System.out.println(user);
                }
                case User.enSaveResults.svFailedEmptyObject -> {
                    System.out.println("Error user was not saved because it's Empty");
                }
            }
        }
    }
}
