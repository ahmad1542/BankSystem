import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class User extends Person {
    private enum enMode {
        emptyMode,
        updateMode,
        addNewMode
    }
    private enMode mode;
    private String userName;
    private String password;
    private int permissions;
    private boolean markedForDelete = false;

    public User(enMode mode, String firstName, String lastName, String email, String phoneNumber, String userName, String password, int permissions){
        super(firstName, lastName, email, phoneNumber);
        this.mode = mode;
        this.userName = userName;
        this.password = password;
        this.permissions = permissions;
    }

    public boolean isMarkedForDelete() {
        return markedForDelete;
    }

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private static User convertLineToUserObject(String line) {
        Scanner lineScanner = new Scanner(line);
        lineScanner.useDelimiter("#//#");

        String firstName = lineScanner.next();
        String lastName = lineScanner.next();
        String email = lineScanner.next();
        String phoneNumber = lineScanner.next();
        String userName = lineScanner.next();
        String password = lineScanner.next();
        int permissions = lineScanner.nextInt();

        lineScanner.close();

        return new User(enMode.updateMode, firstName, lastName, email, phoneNumber, userName, password, permissions);
    }

    private String convertUserObjectToLine(User user) {
        return user.getFirstName() + "#//#" + user.getLastName() + "#//#" + user.getEmail() + "#//#" + user.getPhoneNumber() +
                "#//#" + user.userName + "#//#" + user.password + "#//#" + user.permissions;
    }

    private static Vector<User> loadUsersDataFromFile() {
        Vector<User> vClient = new Vector<>();
        try (Scanner fileScanner = new Scanner(new FileReader("Users.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                User user = convertLineToUserObject(line);
                vClient.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vClient;
    }

    private void saveUsersDataToFile(List<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Users.txt"))) {
            for (User user : users) {
                if (!user.isMarkedForDelete()) {
                    bw.write(user.convertUserObjectToLine(user));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        List <User> lClient;
        lClient = loadUsersDataFromFile();
        for(int i = 0; i <= lClient.size(); i++) {
            if(lClient.get(i).userName.equals(this.userName)) {
                lClient.set(i, this);
                break;
            }
        }
        saveUsersDataToFile(lClient);
    }

    private void addNew() {
        addDataLineToFile(convertUserObjectToLine(this));
    }

    private void addDataLineToFile(String dataLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Users.txt", true))) {
            writer.newLine();
            writer.write(dataLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static User getEmptyUserObject() {
        return new User(enMode.emptyMode, "", "", "", "", "", "", 0);
    }

    public boolean isEmpty() {
        return (this.mode == enMode.emptyMode);
    }

    public static User find(String userName) {
        try (Scanner fileScanner = new Scanner(new FileReader("Users.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                User user = convertLineToUserObject(line);

                if (user.getUserName().equals(userName))
                    return user;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getEmptyUserObject();
    }

    public static User find(String userName, String password) {
        try (Scanner fileScanner = new Scanner(new FileReader("Users.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                User user = convertLineToUserObject(line);

                if (user.getUserName().equals(userName) && user.getPassword().equals(password))
                    return user;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getEmptyUserObject();
    }

    public enum enSaveResults {
        svFailedEmptyObject,
        svSucceeded,
        svFailedUserExists
    }

    public enSaveResults save() {
        switch (mode) {
            case emptyMode -> {
                if (isEmpty())
                    return enSaveResults.svFailedEmptyObject;
            }
            case updateMode ->   {
                update();
                return enSaveResults.svSucceeded;
            }
            default -> {
                if (isUserExist(userName))
                    return enSaveResults.svFailedUserExists;
                else {
                    addNew();
                    mode = enMode.updateMode;
                    return enSaveResults.svSucceeded;
                }
            }
        }
        return null;
    }

    public static boolean isUserExist(String userName) {
        User user = find(userName);
        return (!user.isEmpty());
    }

    public boolean delete() {
        List <User> lClients;
        lClients = loadUsersDataFromFile();

        for(User user: lClients) {
            if (user.getUserName().equals(userName)) {
                user.markedForDelete = true;
                break;
            }
        }

        saveUsersDataToFile(lClients);

        this.copyFrom(getEmptyUserObject());

        return true;
    }

    private void copyFrom(User other) {
        this.userName = other.userName;
        this.password = other.password;
        this.permissions = other.permissions;
        this.markedForDelete = other.markedForDelete;
        this.setFirstName(other.getFirstName());
        this.setLastName(other.getLastName());
        this.setEmail(other.getEmail());
        this.setPhoneNumber(other.getPhoneNumber());
    }

    public static User getAddNewUserObject(String userName) {
        return new User(enMode.addNewMode, "", "", "", "", userName, "", 0);
    }

    public static List <User> getUsersList() {
        return loadUsersDataFromFile();
    }
}
