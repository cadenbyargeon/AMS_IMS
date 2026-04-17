import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class UserDatabase {
    private ArrayList<User> users;

    public UserDatabase() {
        users = new ArrayList<User>();
    }

    public void addUser(User user) {
        if (user == null) {
            return;

        }

        if (findUser(user.getUsername()) != -1) {
            return;
        }

        users.add(user);
    }

    public int findUser(String username) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (username.equals(user.getUsername())) {
                return i;
            }
        }

        return -1;

    }

    public User findUserByIndex(int index) {
        if (index < 0 || index >= users.size()) {
            return null;
        } else {
            return users.get(index);
        }
    }

    public void loadUsersFromFile() {
        try (Scanner infile = new Scanner(new File("users.csv"))) {

            while ((infile.hasNext())) {
                String line = infile.nextLine();
                String[] parts = line.split(",");
                String firstName = parts[0];
                String lastName = parts[1];
                String username = parts[2];
                String password = parts[3];
                boolean isAdmin = Boolean.parseBoolean(parts[4]);

                User user;

                if (isAdmin) {
                    user = new Administrator(firstName, lastName, username);
                } else {
                    user = new NonAdministrator(firstName, lastName, username);
                }

                user.setPassword(password);
                users.add(user);
            }

        } catch (IOException e) {
            System.out.println("No saved users found. Starting fresh.");
        }
    }

    public void saveUsersToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("users.csv"))) {

            for (User user : users) {
                pw.println(
                        user.getFirstName() + "," +
                                user.getLastName() + "," +
                                user.getUsername() + "," +
                                user.getPassword() + "," +
                                user.isAdmin());
            }

        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

}
