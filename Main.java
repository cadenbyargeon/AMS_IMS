
//import java.io.Serial;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

/*
 * The app assumes a default admin account exists
 * Requires successful login of the admin account to do anything
 * The app is currently not connected to the database
 * Working functionalities: login, account creation, view user, and item creation,
 * 
 */
public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean testUsers = true;
        UserDatabase userDB = new UserDatabase();
        ItemDatabase itemDB = new ItemDatabase();

        if (testUsers) {
            Administrator testAdmin = new Administrator("admin", "admin", "admin");
            testAdmin.setPassword("admin");
            userDB.addUser(testAdmin);
            NonAdministrator testUser = new NonAdministrator("user", "user", "user");
            testUser.setPassword("user");
            userDB.addUser(testUser);
        }

        initialLogin(scan, userDB, itemDB);
        scan.close();
        return;

    }

    

    public static void initialLogin(Scanner scan, UserDatabase userDB, ItemDatabase itemDB) {
        while (true) {
        System.out.println("\n==== ALM IMS ====");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Select an option: ");

        String input = scan.nextLine();
        int choice = Integer.parseInt(input);

        switch (choice) {
            case 1:
                User user = logIn(scan, userDB);

                if(user == null)
                {
                    continue;
                }

                if (user.isAdmin()) {
                    admin(scan, userDB, itemDB);
                } else {
                    nonAdmin(scan, itemDB);
                }
                break;
            case 2:
                System.out.println("Exiting ALM IMS...");
                return; // exits program

            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    }

    public static void createAccount(Scanner scan, UserDatabase userDB) {
        User user = null;

        System.out.print("Enter the first name: ");
        String firstName = scan.nextLine();
        System.out.println();

        System.out.print("Enter the last name: ");
        String lastName = scan.nextLine();
        System.out.println();

        System.out.print("Enter the username: ");
        String username = scan.nextLine();
        System.out.println();
        while (userDB.findUser(username) != -1) {
            System.out.print("There is already a user with that username. Try again: ");
            username = scan.nextLine();
        }

        System.out.print("Enter the password: ");
        String password = scan.nextLine();
        System.out.println();
        while (password.length() < 8) {
            System.out.print("Password must be at least 8 characters: ");
            password = scan.nextLine();
        }
        

        user = new NonAdministrator(firstName, lastName, username);
        user.setPassword(password);
        userDB.addUser(user);

        System.out.println(
                user.getFirstName() + " " + user.getLastName() + " has been successfully added to the system.");

    }

    public static User logIn(Scanner scan, UserDatabase userDB) {
        try {
            while (true) {
                System.out.print("Username (or type C to cancel): ");
                String username = scan.nextLine();

                if(username.equalsIgnoreCase("C"))
                {
                    return null;
                }

                System.out.print("Password: ");
                String password = scan.nextLine();

                int userIndex = userDB.findUser(username);

                if (userIndex == -1) {
                    System.out.println("Incorrect login credentials. Try again.");
                    continue;
                }

                User user = userDB.findUserByIndex(userIndex);

                if (user == null || !user.getPassword().equals(password)) {
                    System.out.println("Incorrect login credentials. Try again.");
                    continue;

                }

                return user;

            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    // add other cases
    public static void admin(Scanner scan, UserDatabase userDatabase, ItemDatabase itemDB) {
        boolean run = true;
        while (run) {
            int choice = menuSelectionAdmin(scan);
            switch (choice) {
                // exit
                case 1:
                    System.out.println("Logging out........");
                    run = false;
                    break;

                // edit an item
                case 2:
                    edit(scan, itemDB);
                    break;

                // create an item
                case 3:
                    create(scan, itemDB);
                    break;

                // view an item
                case 4:
                    view(scan, itemDB);
                    break;

                // view a user
                case 5:
                    viewUser(scan, userDatabase);
                    break;

                // change a user
                case 6:
                    changeUser(scan, userDatabase);
                    break;

                // add an account
                case 7:
                    createAccount(scan, userDatabase);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }

        }
        return;

    }

    public static void nonAdmin(Scanner scan, ItemDatabase itemDB) {
        boolean run = true;
        while (run) {
            int choice = menuSelection(scan);
            switch (choice) {
                // exit
                case 1:
                    System.out.println("Logging out........");
                    run = false;
                    break;
                // edit an item
                case 2:
                    edit(scan, itemDB);
                    break;

                // create an item
                case 3:
                    create(scan, itemDB);
                    break;

                // view an item
                case 4:
                    view(scan, itemDB);
                    break;
                default:
                    System.out.println("Invalid selection.");
                    break;

            }
        }
        return;

    }

    // menu selection, returns an int for choice of the following, ran in main
    public static int menuSelection(Scanner scan) {
        int menu;
        while (true) {
            System.out.println("========================");
            System.out.println("Select one of the following:");
            System.out.println("1. Exit IMS");
            System.out.println("2. Edit an item");
            System.out.println("3. Create an item");
            System.out.println("4. View an item");
            System.out.println("========================");
            try {
                menu = scan.nextInt();
                scan.nextLine();
                if (menu >= 1 && menu <= 4) {
                    break;
                } else {
                    System.out.println("Enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scan.nextLine();
            }
        }
        return menu;
    }

    public static int menuSelectionAdmin(Scanner scan) {
        int menu;
        while (true) {
            System.out.println("========================");
            System.out.println("Select one of the following:");
            System.out.println("1. Exit IMS");
            System.out.println("2. Edit an item");
            System.out.println("3. Create an item");
            System.out.println("4. View an item");
            System.out.println("5. View a user");
            System.out.println("6. Change a user");
            System.out.println("7. Add an account");
            System.out.println("========================");
            try {
                menu = scan.nextInt();
                scan.nextLine();
                if (menu >= 1 && menu <= 7) {
                    break;
                } else {
                    System.out.println("Enter a number between 1 and 7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scan.nextLine();
            }
        }
        return menu;
    }

    public static User viewUser(Scanner scan, UserDatabase userDB) {
        try {
            User user = null;
            System.out.print("Enter the username: ");
            String username = scan.nextLine();
            if (username.equals("") || username.equals(null)) {
                System.out.println("No username entered. Returning to main menu...");
                return null;
            } else if (userDB.findUser(username) == -1) {
                System.out.println("No user with that username exists. Returning to main menu...");
                return null;
            } else {
                user = userDB.findUserByIndex(userDB.findUser(username));
                System.out.println(user);
            }

            return user;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public static User changeUser(Scanner scan, UserDatabase userDB) {
        try {
            User user = null;
            // scan.nextLine();
            System.out.print("Enter the username: ");
            String username = scan.nextLine();
            if (username.equals("") || username.equals(null)) {
                System.out.println("No username entered. Returning to main menu...");
                return null;
            } else if (userDB.findUser(username) == -1) {
                System.out.println("No user with that username exists. Returning to main menu...");
                return null;
            } else {
                user = userDB.findUserByIndex(userDB.findUser(username));
                System.out.print("Enter the new username: ");
                String newUsername = scan.nextLine();

                while (userDB.findUser(newUsername) != -1) {
                    System.out.print("There is already a user with that username. Try again: ");
                    newUsername = scan.nextLine();
                }

                user.setUsername(newUsername);
                System.out.print("Enter the new password: ");
                String newPassword = scan.nextLine();
                while (newPassword.length() < 8) {
                    System.out.print("Password must be at least 8 characters: ");
                    newPassword = scan.nextLine();
                }
                user.setPassword(newPassword);
                System.out.println(user);
                return user;

            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public static int item_type_selection(Scanner scan) {
        int menu;
        while (true) {
            System.out.println("========================");
            System.out.println("Select what item type");
            System.out.println("1. Non-Serialized");
            System.out.println("2. Serialized");
            System.out.println("3. Consumable");
            System.out.println("4. Manual");
            System.out.println("========================");
            try {
                menu = scan.nextInt();
                if (menu >= 1 && menu <= 4) {
                    break;
                } else {
                    System.out.println("Enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scan.nextLine();
            }
        }
        return menu;
    }

    // similar to menu in design, returns a choice as an integer to run through if
    // statements
    // can maybe break the menu into a separate function here
    public static void create(Scanner scan, ItemDatabase itemDB) {

        int choice = item_type_selection(scan);

        // The following are all similar, all share basicItem() to get the information
        // all classes need
        // advanced() is information both non-serialized and serialized share and
        // consumables (qty_semester, qty_next_semester)
        // Manuals, serialized, and consumables all have an additional function
        // Functions are a basic IO with the user asking the information
        // After the object is made checkAlert() is run and will display an alert if
        // valid
        // Currently after creation it prints the object for T/S

        switch (choice) {

            // returns an for class type to DB

            // Non-Serialized
            case 1:
                String basicNS[] = basicItem(scan);
                double advancedNS[] = nonSerial(scan);
                Non_Serialized newItemNS = new Non_Serialized(
                        basicNS[0],
                        basicNS[1],
                        basicNS[2],
                        advancedNS[0],
                        advancedNS[1],
                        advancedNS[2]);
                checkAlert(newItemNS.checkAlert());
                System.out.println(newItemNS);
                System.out.println(RESET);
                try {
                    itemDB.createNonSerialized(newItemNS);

                } catch (SQLException e) {
                    System.out.println("Database error: " + e.getMessage());
                } catch (ClassNotFoundException e) {
                    System.out.println("Driver error: " + e.getMessage());
                }

                break;

            // Serialized
            case 2:
                String basicS[] = basicItem(scan);
                String serial = serial(scan);
                Serialized newItemS = new Serialized(
                        basicS[0],
                        basicS[1],
                        basicS[2],
                        serial);
                System.out.println(newItemS);
                System.out.println(RESET);
                try {
                    itemDB.createSerialized(newItemS);

                } catch (SQLException e) {
                    System.out.println("Database error: " + e.getMessage());
                } catch (ClassNotFoundException e) {
                    System.out.println("Driver error: " + e.getMessage());
                }
                break;

            // Consumable
            case 3:
                String basicC[] = basicItem(scan);
                String consume = consumable(scan);
                double advancedC[] = nonSerial(scan);
                Consumable newItemC = new Consumable(
                        basicC[0],
                        basicC[1],
                        basicC[2],
                        advancedC[0],
                        advancedC[1],
                        advancedC[2],
                        consume);
                checkAlert(newItemC.checkAlert());
                System.out.println(newItemC);
                System.out.println(RESET);
                try {
                    itemDB.createConsumable(newItemC);
                } catch (SQLException e) {
                    System.out.println("Database error: " + e.getMessage());
                } catch (ClassNotFoundException e) {
                    System.out.println("Driver error: " + e.getMessage());
                }
                break;

            // Manual
            case 4:
                String basicM[] = basicItem(scan);
                String manualIn[] = manual(scan);
                Manual newItemM = new Manual(
                        basicM[0],
                        basicM[1],
                        basicM[2],
                        Integer.parseInt(manualIn[0]),
                        manualIn[1]);
                System.out.println(newItemM);
                System.out.println(RESET);
                try {
                    itemDB.createManual(newItemM);
                } catch (SQLException e) {
                    System.out.println("Database error: " + e.getMessage());
                } catch (ClassNotFoundException e) {
                    System.out.println("Driver error: " + e.getMessage());
                }
                break;
        }
        return;

    }

//Non - Serialized and Serialized Parts
public static void edit(Scanner scan, ItemDatabase itemDB){
    int choice = item_type_selection(scan);
    scan.nextLine();
    System.out.println("What is the item to edit?");
    String search = scan.nextLine();
    int id = 1;
    try{
    switch(choice){
        case 1: 
        {
            ArrayList<Non_Serialized> returnList = itemDB.getNonSerialized(search);
            for(int i=0; i<returnList.size(); i++){
                System.out.println("****************************");
                System.out.println(i+1);
                System.out.println(returnList.get(i));
            }
            System.out.println("Which item is the one to edit?");
            int itemNumber = scan.nextInt();
            Non_Serialized item = returnList.get(itemNumber-1);
            System.out.println("\n1:  Name: " + item.getName()  + "\n2:  Model: " + item.getModel() + 
            "\n3:  Part Number: " + item.getPartNum() + "\n4:  Quantity: " + item.getQty() + 
            "\n5:  Quantity for This Semester: " + item.getQtySemester() + "\n6.  Quantity for Next Smester: " + item.getQtyNextSem());
            editValues(scan, item, choice, itemDB, id);
            break;
        }
        case 2: 
        {
            ArrayList<Serialized> returnList = itemDB.getSerialized(search);
            for(int i=0; i<returnList.size(); i++){
                System.out.println(i+1);
                System.out.println(returnList.get(i));
            }
            System.out.println("Which item is the one to edit?");
            int itemNumber = scan.nextInt();
            Serialized item = returnList.get(itemNumber-1);
            System.out.println("\n1:  Name: " + item.getName()  + "\n2:  Model: " + item.getModel() + 
            "\n3:  Part Number: " + item.getPartNum() + "\n4:  Serial Number: " + item.getSerialNum());
            editValues(scan, item, choice, itemDB, id);
            break;
        }
        case 3:
        {
            ArrayList<Consumable> returnList = itemDB.getConsumable(search);
            for(int i=0; i<returnList.size(); i++){
                System.out.println(i+1);
                System.out.println(returnList.get(i));
            }
            System.out.println("Which item is the one to edit?");
            int itemNumber = scan.nextInt();
            Consumable item = returnList.get(itemNumber-1);
            System.out.println("\n1:  Name: " + item.getName()  + "\n2:  Model: " + item.getModel() + 
            "\n3:  Part Number: " + item.getPartNum() + "\n4:  Quantity: " + item.getQty() + item.getQtyType() +
            "\n5:  Quantity for This Semester: " + item.getQtySemester() + item.getQtyType() + "\n6.  Quantity for Next Smester: " + 
            item.getQtyNextSem() + item.getQtyType());
            editValues(scan, item, choice, itemDB, id);
            break;
        }
        case 4:
        {
            ArrayList<Manual> returnList = itemDB.getManual(search);
            for(int i=0; i<returnList.size(); i++){
                System.out.println(i+1);
                System.out.println(returnList.get(i));
            }
            System.out.println("Which item is the one to edit?");
            int itemNumber = scan.nextInt();
            Manual item = returnList.get(itemNumber-1);
            System.out.println("\n1:  Name: " + item.getName()  + "\n2:  Model: " + item.getModel() + 
            "\n3:  Part Number: " + item.getPartNum() + "\n4:  Quantity: " + item.getQty() + "\n5.  Revision: " + item.getRevision());
            editValues(scan, item, choice, itemDB, id);
            break;
        }
    }
    }catch(SQLException | ClassNotFoundException e){
    System.out.println("Database error");
    }
}

public static void editValues(Scanner scan, Item_Parent item, int typeItem, ItemDatabase itemDB, int id){
    System.out.println("\nWhich variable would you like to change?");
    int choice;
    while (true) {
        try {
            choice = scan.nextInt();
            break;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Enter a number.");
            scan.nextLine(); // clear bad input
        }
    }
        if(choice <=3){
            String basic = editBasic(scan, choice);
            if(choice == 1){
                //name
                itemDB.changeGlobal("name", basic, id, "non_serialized");
            }
            if(choice == 2){
                //model
                itemDB.changeGlobal("model", basic, id, "non_serialized");
            }
            if(choice == 3){
                //part number
                itemDB.changeGlobal("partNum", basic, id, "non_serialized");
            }
        }else{
        switch(typeItem){
        //non-serial
        case 1:
            {
                double newQty = editQts(scan, choice);
                if(choice == 4){
                    //qty
                    itemDB.changeQty("qty", newQty, id, "non_serialized");
                }
                if(choice == 5){
                    //qty this sem
                    itemDB.changeQty("qty_semester", newQty, id, "non_serialized");
                }
                if(choice == 6){
                    //qty next sem
                    itemDB.changeQty("qty_next_semester", newQty, id, "non_serialized");
                }
            }

            break;
            //pass back to DB
        //serialized
        case 2:
            {
                String serial = editSerial(scan);
                //serial number
                itemDB.changeGlobal("serial", serial, id, "non_serialized");
            }
            break;
        //consumables
        case 3:
            {
            double newQty = editQts(scan, choice);
                if(choice == 4){
                    //qty
                    itemDB.changeQty("qty", newQty, id, "non_serialized");
                }
                if(choice == 5){
                    //qty this sem
                    itemDB.changeQty("qty_semester", newQty, id, "non_serialized");
                }
                if(choice == 6){
                    //qty next sem
                    itemDB.changeQty("qty_next_semester", newQty, id, "non_serialized");
                }
            }
            break;
        //manual
        case 4:
            {
            if(choice == 4){
                Double qty = editManQty(scan);
            //qty
            itemDB.changeQty("qty", qty, id, "non_serialized");
            }
            if(choice ==5){
                //rev
                String rev = editRev(scan);
                itemDB.changeGlobal("revision", rev, id, "non_serialized");
            }
            break;
            }
        }
    }
}

    public static String editBasic(Scanner scan, int choice) {
        String editItem = "";
        scan.nextLine();
        switch (choice) {
            case 1:
                System.out.println("What is the new name?");
                editItem = scan.nextLine();
                break;
            case 2:
                System.out.println("What is the new model?");
                editItem = scan.nextLine();
                break;
            case 3:
                System.out.println("What is the new part number?");
                editItem = scan.nextLine();
                break;
        }

        return editItem;
    }

    public static String editSerial(Scanner scan) {// serialized class
        scan.nextLine();
        System.out.println("What is the new serial number?");
        String serial = scan.nextLine();
        return serial;
    }

    public static String editRev(Scanner scan) {// manuals
        scan.nextLine();
        System.out.println("What is the new revision number?");
        String rev = scan.nextLine();
        return rev;
    }

    public static double editManQty(Scanner scan) {
        double qty;
        System.out.println("What is the new quantity?");
        while (true) {
            try {
                qty = scan.nextDouble();
                while (qty < 0) {
                    System.out.println("Please enter a positive number: ");
                    qty = scan.nextDouble();
                }
                scan.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scan.nextLine();
            }
        }
        return qty;
    }

    public static double editQts(Scanner scan, int choice) {// non-serialized class
        double qtyReturned = 0;
        switch (choice - 3) {
            case 1:
                System.out.println("What is the new quantity?");
                while (true) {
                    double qty = 0;
                    try {
                        qty = scan.nextDouble();
                        while (qty < 0) {
                            System.out.println("Please enter a positive number: ");
                            qty = scan.nextDouble();
                        }
                        scan.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Enter a number.");
                        scan.nextLine();
                    }
                    qtyReturned = qty;
                }
                break;
            case 2:
                double qtyThis = 0;
                while (true) {
                    System.out.println("What is the new quantity for this semester?");
                    try {
                        qtyThis = scan.nextDouble();
                        scan.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Enter a number.");
                        scan.nextLine();
                    }
                    qtyReturned = qtyThis;
                }
                break;
            case 3:
                double qtyNext = 0;
                while (true) {
                    System.out.println("What is the new quantity for next semester?");
                    try {
                        qtyNext = scan.nextDouble();
                        scan.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Enter a number.");
                        scan.nextLine();
                    }
                    qtyReturned = qtyNext;
                }
                break;
        }
        return qtyReturned;

    }

    // Stores data in a string[], the int for qty is set to a string, then back to
    // an int in the object creation
    // qty is still inputed as an int by the user to disallow invalid input
    public static String[] basicItem(Scanner scan) {
        scan.nextLine();
        System.out.println("What is the name of the item?");
        String name = scan.nextLine();
        System.out.println("What is the model number of the item");
        String model = scan.nextLine();
        System.out.println("What is the part number of the item?");
        String part = scan.nextLine();
        return new String[] { name, model, part };
    }

    // add qty_semester and qty_next_semester
    public static double[] nonSerial(Scanner scan) {
        double this_sem = 0;
        double next_sem = 0;
        double qty = 0;
        System.out.println("What is the quantity of the item?");
        while (true) {
            try {
                qty = scan.nextDouble();
                if (qty < 0) {
                    System.out.println("Must be a positive number. What is the quantity?");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scan.nextLine();
            }
        }
        System.out.println("What is the quantity required per semester?");
        while (true) {
            try {
                this_sem = scan.nextDouble();
                if (this_sem < 0) {
                    System.out.println("Must be a positive number. What is the quantity required per semester?");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scan.nextLine();
            }
        }
        System.out.println("What is the quantity required for next semester?");
        while (true) {
            try {
                next_sem = scan.nextDouble();
                if (next_sem < 0) {
                    System.out.println("Must be a positive number. What is the quantity required for next semester?");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scan.nextLine();
            }
        }
        return new double[] { qty, this_sem, next_sem };
    }

    // add serial number
    public static String serial(Scanner scan) {
        System.out.println("What is the serial number?");
        String cereal = scan.nextLine();
        return cereal;
    }

    // add qty type
    public static String consumable(Scanner scan) {
        System.out.println("What is the unit quantity type?");
        String type = scan.nextLine();
        return type;
    }

    // add revision/qty number
    public static String[] manual(Scanner scan) {
        String qty;
        int qtyInput;
        System.out.println("What is the quantity of the item?");
        while (true) {
            try {
                qtyInput = scan.nextInt();
                if (qtyInput < 0) {
                    System.out.println("Must be a positive number. What is the quantity?");
                    continue;
                }
                qty = Integer.toString(qtyInput);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scan.nextLine();
            }
        }
        scan.nextLine();
        System.out.println("What is the revision number?");
        String rev = scan.nextLine();
        return new String[] { qty, rev };
    }

    public static void view(Scanner scan, ItemDatabase itemDB) {
        ArrayList<String> foundItems = new ArrayList<String>();
        System.out.println("Enter the item name: ");
        String input = scan.nextLine();

        try {
            ArrayList<Consumable> consumables = itemDB.getConsumable(input);

            if (consumables != null) {
                for (Consumable c : consumables) {
                    if (c != null) {
                        foundItems.add(c.toString());
                    }
                }

            }

            ArrayList<Non_Serialized> nonSerialized = itemDB.getNonSerialized(input);

            if (nonSerialized != null) {
                for (Non_Serialized n : nonSerialized) {
                    if (n != null) {
                        foundItems.add(n.toString());

                    }
                }

            }

            ArrayList<Serialized> serialized = itemDB.getSerialized(input);
            if (serialized != null) {
                for (Serialized s : serialized) {
                    if (s != null) {
                        foundItems.add(s.toString());

                    }
                }

            }

            ArrayList<Manual> manuals = itemDB.getManual(input);
            if (manuals != null) {
                for (Manual m : manuals) {
                    if (m != null) {
                        foundItems.add(m.toString());

                    }
                }

            }

            if (foundItems.isEmpty()) {
                System.out.println("Item not found.");
            } else {
                for (int i = 0; i < foundItems.size(); i++) {
                    System.out.println(i + 1);
                    System.out.println(foundItems.get(i));
                    System.out.println("***************************");
                }

            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver error: " + e.getMessage());
        }

    }

    // checks the alert status of an object, and prints the message associated
    public static void checkAlert(int alert) {
        if (alert == 2) {
            System.out.println(RED + "NOT ENOUGH FOR THIS SEMESTER");
        } else if (alert == 1) {
            System.out.println(YELLOW + "NOT ENOUGH FOR NEXT SEMESTER");
        }
        else{
            System.out.println(GREEN + "GOOD FOR SEMESTER.");
        }
    }

}