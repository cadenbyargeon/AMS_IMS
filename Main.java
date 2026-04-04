import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;
/*
 * The app assumes a default admin account exists
 * Requires successful login of the admin account to do anything
 * The app is currently not connected to the database
 * Working functionalities: login, account creation, view user, and item creation,
 * 
 */
public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        boolean testUsers = true;
        UserDatabase userDB = new UserDatabase();

        if(testUsers){
        Administrator testAdmin = new Administrator("admin", "admin", "admin");
        testAdmin.setPassword("admin");
        userDB.addUser(testAdmin);
        NonAdministrator testUser = new NonAdministrator("user", "user", "user" );
        testUser.setPassword("user");
        userDB.addUser(testUser);
        }

        
        initialLogin(scan, userDB);
            


        /*User user = logIn(scan, userDB);

        //menu choice selection, ran through a separate function
        //add if statement when user works, WIP
        if(user.isAdmin() == true){
        admin(scan, userDB);
        }
        if(user.isAdmin() == false){
        nonAdmin(scan);
        }*/
        scan.close();
        return;
        
    }

    public static void initialLogin(Scanner scan, UserDatabase userDB)
    {
        while(true)
        {
            System.out.print("Press any key to log in: ");
            String input = scan.nextLine();
          
            User user = logIn(scan, userDB); 
            if(user.isAdmin() == true)
            {
                admin(scan, userDB);
            }
            if(user.isAdmin() == false)
            {
                nonAdmin(scan);
            }

            break;

            
        }

    }
        

    


    //delete later, will change way it works
    /*public static void processInitialOption(Scanner scan)
    {
        switch(initialChoice)
        {
            case 1: User user = logIn(scan, userDB); 
                    if(user.isAdmin() == true)
                    {
                        admin(scan, userDB);
                    }
                    if(user.isAdmin() == false)
                    {
                        nonAdmin(scan);
                    }
                    break;
            case 2: createAccount(scan, userDB); break;
            default: System.out.println("Invalid selection"); break;
                     
        }
    }*/

    public static void createAccount(Scanner scan, UserDatabase userDB)
    {
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

        System.out.print("Enter the password: ");
        String password = scan.nextLine();
        System.out.println();
        while(password.length() < 8)
        {
            System.out.print("Password must be at least 8 characters: ");
            password = scan.nextLine();
        }
        while(userDB.findUser(username) != -1)
        {
            System.out.print("There is already a user with that username. Try again: ");
            username = scan.nextLine();
        }

        
        user = new NonAdministrator(firstName, lastName, username);
        user.setPassword(password);
        userDB.addUser(user);

        System.out.println(user.getFirstName() + " " + user.getLastName() + " has been successfully added to the system.");
        
    }

    public static User logIn(Scanner scan, UserDatabase userDB)
    {
        try{
            while(true)
            {
                System.out.print("Username: ");
                String username = scan.nextLine();
        

                System.out.print("Password: ");
                String password = scan.nextLine();

                int userIndex = userDB.findUser(username);

                if (userIndex == -1) 
                {
                    System.out.println("Incorrect login credentials. Try again.");
                    continue;
                }

                User user = userDB.findUserByIndex(userIndex);

                if (user == null || !user.getPassword().equals(password))
                {
                    System.out.println("Incorrect login credentials. Try again.");
                    continue;
                
                }

                return user;

            }
            



            

        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
        


        

    }

    //add other cases
    public static void admin(Scanner scan, UserDatabase userDatabase){
        boolean run = true;
        while(run)
        {
            int choice = menuSelectionAdmin(scan);
            switch(choice){
            //exit
            case 1:
                System.out.println("Exiting ALM IMS........");
                run = false;

            //edit an item
            case 2:
                //edit(scan);
                break;

            //create an item
            case 3:
                create(scan);
                break;

            //view an item
            case 4:
                view(scan);
                break;

            //view a user
            case 5:
                viewUser(scan, userDatabase);
                break;

            //change a user
            case 6:
                changeUser(scan, userDatabase);
                break;

            //add an account
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

    public static void nonAdmin(Scanner scan){
        boolean run = true;
    while(run)
    {
        int choice = menuSelection(scan);
        switch(choice){
        //exit
        case 1:
            System.out.println("Exiting ALM IMS........");
            run = false;
        //edit an item
        case 2:
            //edit(scan);
            break;

        //create an item
        case 3:
            create(scan);
            break;

        //view an item
        case 4:
            view(scan);
            break;
        default:
            System.out.println("Invalid selection.");
            break;

        }
    }
    return;
        
}


    //menu selection, returns an int for choice of the following, ran in main
public static int menuSelection(Scanner scan){
    int menu;
    while(true){
    System.out.println("========================");
    System.out.println("Select one of the following:");
    System.out.println("1. Exit IMS");
    System.out.println("2. Edit an item");
    System.out.println("3. Create an item");
    System.out.println("4. View an item");
    System.out.println( "========================");
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

public static int menuSelectionAdmin(Scanner scan){
    int menu;
    while(true){
    System.out.println("========================");
    System.out.println("Select one of the following:");
    System.out.println("1. Exit IMS");
    System.out.println("2. Edit an item");
    System.out.println("3. Create an item");
    System.out.println("4. View an item");
    System.out.println("5. View a user");
    System.out.println("6. Change a user");
    System.out.println("7. Add an account");
    System.out.println( "========================");
        try {
            menu = scan.nextInt();
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

public static User viewUser(Scanner scan, UserDatabase userDB)
{   try 
    {
        User user = null;
        System.out.print("Enter the username: "); 
        String username= scan.nextLine();
        if(username.equals("") || username.equals(null))
        {
            System.out.println("No username entered. Returning to main menu...");
            return null;
        }
        else if(userDB.findUser(username) == -1)
        {
            System.out.println("No user with that username exists. Returning to main menu...");
            return null;
        }
        else{
            user = userDB.findUserByIndex(userDB.findUser(username));
            System.out.println(user);
        }

        return user;
    } catch (Exception e) {
        System.out.println(e);
        return null;
    }
    

}

//finish changeUser method next sprint:

public static User changeUser(Scanner scan, UserDatabase userDB)
{
    try {
        User user = null;
        scan.nextLine();
        System.out.print("Enter the username: "); 
        String username = scan.nextLine();
        if(username.equals("") || username.equals(null))
        {
            System.out.println("No username entered. Returning to main menu...");
            return null;
        }
        else if(userDB.findUser(username) == -1)
        {
            System.out.println("No user with that username exists. Returning to main menu...");
            return null;
        }
        else{
            user = userDB.findUserByIndex(userDB.findUser(username));
            System.out.print("Enter the new username: ");
            String newUsername = scan.nextLine();
                
            while(userDB.findUser(newUsername) != -1)
            {
                System.out.print("There is already a user with that username. Try again: ");
                newUsername = scan.nextLine();
            }

            user.setUsername(newUsername);
            System.out.print("Enter the new password: ");
            String newPassword = scan.nextLine();
            while(newPassword.length() < 8)
            {
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


public static int item_type_selection(Scanner scan){
    int menu;
    while(true){
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
    //similar to menu in design, returns a choice as an integer to run through if statements
    //can maybe break the menu into a separate function here
public static void create(Scanner scan){

    int choice = item_type_selection(scan);    

    //The following are all similar, all share basicItem() to get the information all classes need
    //advanced() is information both non-serialized and serialized share and consumables (qty_semester, qty_next_semester)
    //Manuals, serialized, and consumables all have an additional function
    //Functions are a basic IO with the user asking the information
    //After the object is made checkAlert() is run and will display an alert if valid
    //Currently after creation it prints the object for T/S


    switch(choice){

    //Non-Serialized
    case 1:
        String basicNS[] = basicItem(scan);
        double advancedNS[] = nonSerial(scan);
        Non_Serialized newItemNS = new Non_Serialized(
        basicNS[0],
        basicNS[1],
        basicNS[2],
        advancedNS[0],
        advancedNS[1],
        advancedNS[2]
        );
        checkAlert(newItemNS.checkAlert());
        System.out.println(newItemNS);
        break;

    //Serialized
    case 2:
        String basicS[] = basicItem(scan);
        String serial = serial(scan);
        Serialized newItemS = new Serialized(
        basicS[0],
        basicS[1],
        basicS[2],
        serial
        );
        System.out.println(newItemS);
        break;

    //Consumable
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
        consume
        );
        checkAlert(newItemC.checkAlert());
        System.out.println(newItemC);
        break;

    //Manual
    case 4:
        String basicM[] = basicItem(scan);
        String manual = manual(scan);
        Manual newItemM = new Manual(
        basicM[0],
        basicM[1],
        basicM[2],
        (int)(Double.parseDouble(basicM[3])),
        manual
        );
        System.out.println(newItemM);
        break;
    }
    return;

}

//Non - Serialized and Serialized Parts
/*public static void edit(Scanner scan){
    int choice = item_type_selection(scan);

    //maybe separate method below
    System.out.println("Which variable would you like to change?");
    int change = scan.nextInt();

        //search for an object here*************
        //Print object here with numbered lines*************
        switch(choice){
        //non-serial
        case 1:
            Non_Serialized editNon_Serialized = getItemFromDB();//call method for DB item here 
            System.out.println(editNon_Serialized);
            if(choice <=3)
            {
                editBasic(scan, change, editNon_Serialized);
            }else
            {
                editQts(scan, change, editNon_Serialized);
            } 
            updateItemInDB(editNon_Serialized);
            System.out.println(editNon_Serialized);
            break;
            //pass back to DB
        //serialized
        case 2:
            Serialized editSerialized = getItemFromDB();//call method for DB item
            System.out.println(editSerialized);
            if(choice <=3)
            {
                editBasic(scan, change, editSerialized);
            }else{
                editSerial(scan, editSerialized);
            }
            updateItemInDB(editSerialized);
            System.out.println(editSerialized);
            break;
        //consumables
        case 3:
            Consumable editConsumable = getItemFromDB();
            System.out.println(editConsumable);
            if(choice <=3){
            editBasic(scan, change, editConsumable);
            }else{
            editQts(scan, choice, editConsumable);
            }
            updateItemInDB(editConsumable);
            System.out.println(editConsumable);
            break;
        //manual
        case 4:
            Manual editManual = getItemFromDB();
            System.out.println(editManual);
            if(choice <=3){
            editBasic(scan, choice, editManual);
            }else{
            editRev(scan, editManual);
            }
            updateItemInDB(editManual);
            System.out.println(editManual);
            break;
        }
}*/

public static Item_Parent editBasic(Scanner scan, int choice, Item_Parent editItem){
    switch(choice){
        case 1:
            System.out.println("What is the new name?");
            String name = scan.nextLine();
            editItem.setName(name);
            break;
        case 2:
            System.out.println("What is the new model?");
            String model = scan.nextLine();
            editItem.setModel(model);
            break;
        case 3:
            System.out.println("What is the new part number?");
            String partNum = scan.nextLine();
            editItem.setPartNum(partNum);
            break;
    }
    return editItem;
}

public static void editSerial(Scanner scan, Serialized editSerial){//serialized class
    System.out.println("What is the new serial number?");
    String serial = scan.nextLine();
    editSerial.setSerialNum(serial);
}

public static void editRev(Scanner scan, Manual editManual){//manuals
    System.out.println("What is the new revision number?");
    String rev = scan.nextLine();
    editManual.setRevision(rev);
}

public static int editQts(Scanner scan, int choice, Non_Serialized editItem){//non-serialized class
    switch(choice+4){
        case 1:
            System.out.println("What is the new quantity?");
            while(true){
            double qty = 0;
            try {
                qty = scan.nextDouble();
                while(qty<0){
                    System.out.println("Please enter a positive number: ");
                    qty = scan.nextDouble();
                }
                scan.nextLine();
                break;
                }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scan.nextLine();
            }
            editItem.setQty(qty);
        }
            break;
        case 2:
            double qtyThis = 0;
            while(true){
            System.out.println("What is the new quantity for this semester?");
            try {
                qtyThis = scan.nextDouble();
                scan.nextLine();
                break;
                }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scan.nextLine();
            }
            editItem.setQtySemester(qtyThis);
            }
            break;
        case 3:
            double qtyNext = 0;
            while(true){
            System.out.println("What is the new quantity for next semester?");
            try {
                qtyNext = scan.nextDouble();
                scan.nextLine();
                break;
                }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scan.nextLine();
            }
            editItem.setQtyNextSem(qtyNext);
            }
            break;
    }
    return 0;

}

public static String search(Scanner scan)
{
    String item;
    System.out.println("Enter the item name: ");
    String input = scan.nextLine();

    // run select query with the value of item on the itemName column

    item = ""; // string format which displays the row in the table with the item that was searched

    return item;



}

//Stores data in a string[], the int for qty is set to a string, then back to an int in the object creation
//qty is still inputed as an int by the user to disallow invalid input
public static String[] basicItem(Scanner scan){
    scan.nextLine();
    System.out.println("What is the name of the item?");
    String name = scan.nextLine();
    System.out.println("What is the model number of the item");
    String model = scan.nextLine();
    System.out.println("What is the part number of the item?");
    String part = scan.nextLine();
    return new String[]{name, model, part};
}

    //add qty_semester and qty_next_semester
public static double[] nonSerial(Scanner scan){
    double this_sem = 0;
    double next_sem = 0;
    double qty = 0;
        while(true){
    try {
                qty = scan.nextDouble();
                break;
                }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scan.nextLine();
            }
        }
    while(true){
    System.out.println("What is the quantity required per semester?");
    try {
                this_sem = scan.nextDouble();
                scan.nextLine();
                break;
                }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scan.nextLine();
            }
        }
    System.out.println("What is the quantity required for next semester?");
    while(true){
    try {
                next_sem = scan.nextDouble();
                scan.nextLine();
                break;
                }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scan.nextLine();
            }
        }
    return new double[]{qty, this_sem, next_sem};
}

    //add serial number
public static String serial(Scanner scan){
    System.out.println("What is the serial number?");
    String cereal = scan.nextLine();
    return cereal;
}

    //add qty type
public static String consumable(Scanner scan){
    scan.nextLine();
    System.out.println("What is the unit quantity type?");
    String type = scan.nextLine();
    return type;
}

    //add revision number
public static String manual(Scanner scan){
    scan.nextLine();
    System.out.println("What is the revision number?");
    String rev = scan.nextLine();
    return rev;
}

public static void view(Scanner scan){
    System.out.println("Enter item to view: ");
    String item = scan.nextLine();
    //newItem = searchDB(item);
    //System.out.println(newItem);
}

    //checks the alert status of an object, and prints the message associated
public static void checkAlert(int alert){
        if(alert==2){
            System.out.println("NOT ENOUGH FOR THIS SEMESTER");
        }else if(alert==1){
            System.out.println("NOT ENOUGH FOR NEXT SEMESTER");
        }
    }

public static boolean integerCheck(Integer input){
    if(input == null){
        return false;
    }else{
        return true;
    }
} 

public static Non_Serialized convertNon_Serialized (String[] database){
    Non_Serialized converted= new Non_Serialized(database[1],database[2], database[3], Double.parseDouble(database[4]), Double.parseDouble(database[5]), Double.parseDouble(database[6]));
    return converted;
}

public static Serialized convertSerialized (String[] database){
    Serialized converted= new Serialized(database[1],database[2], database[3], database[7]);
    return converted;
}

public static Consumable convertConsumable (String[] database){
    Consumable converted= new Consumable(database[1],database[2], database[3], Double.parseDouble(database[4]), Double.parseDouble(database[5]), Double.parseDouble(database[6]), database[7]);
    return converted;
}

/*public static Manual convertManual (String[] database){
   // Manual converted= new Manual(database[1],database[2], database[3], Double.parseDouble(database[4]), database[5]);
    //return converted;
}*/
}