import java.sql.*;
import java.util.Scanner;
/**
 * The app assumes a default admin account exists
 * Requires successful login of the admin account to do anything
 * The app is currently not connected to the database
 * Working functionalities: login, account creation, view, and modification,
 * item creation
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
        }

        //int initialChoice = 0;
        //do{
        //    initialChoice = initialOptions(scan);
        //    processInitialOption(scan, initialChoice, userDB);
        //} while (initialChoice != 3);


        User user = logIn(scan, userDB);

        //menu choice selection, ran through a separate function
        //add if statement when user works, WIP
        if(user instanceof Administrator){
        admin(scan, userDB);
        }
        if(user instanceof NonAdministrator){
        nonAdmin(scan);
        }
        scan.close();
        return;
        
    }

    /*public static int initialOptions(Scanner scan)
    {
        System.out.println("1. Create an account.");
        System.out.println("2. Log in");
        String choiceStr = scan.nextLine();
        int choice = Integer.parseInt(choiceStr);
        return choice;

    }*/



    //delete later, will change way it works
    /*public static void processInitialOption(Scanner scan, int initialChoice, UserDatabase userDB)
    {
        switch(initialChoice)
        {
            case 1: createAccount(scan, userDB); break;
            case 2: User user = logIn(scan, userDB); break;
            default: System.out.println("Invalid selection"); break;
                     
        }
    }*/

    public static void createAccount(Scanner scan, UserDatabase userDB)
    {
        User user = null;
        
        System.out.print("Enter the first name: ");
        String firstName = scan.nextLine();

        System.out.print("Enter the last name: ");
        String lastName = scan.nextLine();

        System.out.print("Enter the username: ");
        String username = scan.nextLine();

        System.out.print("Enter the password: ");
        String password = scan.nextLine();
        while(password.length() < 16)
        {
            System.out.print("Password must be at least 16 characters: ");
            password = scan.nextLine();
        }
        while(userDB.findUser(username) != -1)
        {
            System.out.print("There is already a user with that username. Try again: ");
            password = scan.nextLine();
        }

        
        user = new NonAdministrator(firstName, lastName, username);
        user.setPassword(password);
        
    }

    public static User logIn(Scanner scan, UserDatabase userDB)
    {
        try{
            System.out.print("Username: ");
            String username = scan.nextLine();
        

            System.out.print("Password: ");
            String password = scan.nextLine();

            int userIndex = userDB.findUser(username);

            if (userIndex == -1) 
            {
                System.out.println("Incorrect login credentials. Exiting ALM IMS...");
                return null;
            }

            User user = userDB.findUserByIndex(userIndex);

            if (user == null || !user.getPassword().equals(password))
            {
                System.out.println("Incorrect login credentials. Exiting ALM IMS...");
                return null;
            }



            return user;

        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
        


        

    }

    //add other cases
    public static void admin(Scanner scan, UserDatabase userDatabase){
        int choice = menuSelectionAdmin(scan);
        switch(choice){
        //exit
        case 1:
            System.out.println("Exiting ALM IMS........");
            break;

        //edit an item
        case 2:
            edit(scan);
            break;

        //create an item
        case 3:
            create(scan);
            break;

        //view an item
        case 4:
            break;

        //view a user
        case 5:
            //viewUser(scan, userDatabase);
            break;

        //change a user
        case 6:
            //changeUser(scan, userDatabase);
            break;

        //add an account
        case 7:
            createAccount(scan, userDatabase);
            break;
        }
    }

    public static void nonAdmin(Scanner scan){
        int choice = menuSelection(scan);
        switch(choice){
        //exit
        case 1:
            System.out.println("Exiting ALM IMS........");
            break;

        //edit an item
        case 2:
            edit(scan);
            break;

        //create an item
        case 3:
            create(scan);
            break;

        //view an item
        case 4:
            break;

        }
}


    //menu selection, returns an int for choice of the following, ran in main
public static int menuSelection(Scanner scan){
    System.out.println("========================");
    System.out.println("Select one of the following:");
    System.out.println("1. Exit IMS");
    System.out.println("2. Edit an item");
    System.out.println("3. Create an item");
    System.out.println("4. View an item");
    System.out.println( "========================");
    int menu = scan.nextInt();
    return menu;
}

public static int menuSelectionAdmin(Scanner scan){
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
    int menu = scan.nextInt();
    return menu;
}

public static int item_type_selection(Scanner scan){
    System.out.println("========================");
    System.out.println("Select what item type");
    System.out.println("1. Non-Serialized");
    System.out.println("2. Serialized");
    System.out.println("3. Consumable");
    System.out.println("4. Manual");
    System.out.println("========================");
    int choice = scan.nextInt();
    return choice;
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
        int advancedNS[] = nonSerial(scan);
        Non_Serialized newItemNS = new Non_Serialized(
        basicNS[0],
        basicNS[1],
        basicNS[2],
        Integer.parseInt(basicNS[3]),
        advancedNS[0],
        advancedNS[1]
        );
        checkAlert(newItemNS.checkAlert());
        System.out.println(newItemNS);
        break;

    //Serialized
    case 2:
        String basicS[] = basicItem(scan);
        int advancedS[] = nonSerial(scan);
        String serial = serial(scan);
        Serialized newItemS = new Serialized(
        basicS[0],
        basicS[1],
        basicS[2],
        Integer.parseInt(basicS[3]),
        advancedS[0],
        advancedS[1],
        serial
        );
        checkAlert(newItemS.checkAlert());
        System.out.println(newItemS);
        break;

    //Consumable
    case 3:
        String basicC[] = basicItem(scan);
        String consume = consumable(scan);
        int advancedC[] = nonSerial(scan);
        Consumable newItemC = new Consumable(
        basicC[0],
        basicC[1],
        basicC[2],
        Integer.parseInt(basicC[3]),
        advancedC[0],
        advancedC[1],
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
        Integer.parseInt(basicM[3]),
        manual
        );
        System.out.println(newItemM);
        break;
    }
    return;

}

//Non - Serialized and Serialized Part
public static void edit(Scanner scan){
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
            if(choice <=4)
            {
                editBasic(scan, change, editNon_Serialized);
            }else
            {
                editQts(scan, change, editNon_Serialized);
            } 
            updateItemInDB(editNon_Serialized);
            break;
            //pass back to DB
        //serialized
        case 2:
            Serialized editSerialized = getItemFromDB();//call method for DB item
            if(choice <=4)
            {
                editBasic(scan, change, editSerialized);
            }else if(choice <=6)
            {
                editQts(scan, change, editSerialized);
            }else
            {
                editSerial(scan, editSerialized);
            }
            updateItemInDB(editSerialized);
            break;
        //consumables
        case 3:
            Consumable editConsumable = getItemFromDB();
            if(choice <=4){
            editBasic(scan, change, editConsumable);
            }else{
            editQts(scan, choice, editConsumable);
            }
            updateItemInDB(editConsumable);
            break;
        //manual
        case 4:
            Manual editManual = getItemFromDB();
            if(choice <=4){
            editBasic(scan, choice, editManual);
            }else{
            editRev(scan, editManual);
            }
            updateItemInDB(editManual);
            break;
        }
}

public static Item_Parent editBasic(Scanner scan, int choice, Item_Parent editItem){
    switch(choice){
        case 1:
            System.out.println("What is the new name?");
            String name = scan.nextLine();
            editItem.setName(name);
        case 2:
            System.out.println("What is the new model?");
            String model = scan.nextLine();
            editItem.setModel(model);
        case 3:
            System.out.println("What is the new part number?");
            String partNum = scan.nextLine();
            editItem.setPartNum(partNum);
        case 4:
            System.out.println("What is the new quantity?");
            int qty = scan.nextInt();
            editItem.setQty(qty);
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
            System.out.println("What is the new quantity for this semester?");
            int qtyThis = scan.nextInt();
            editItem.setQtySemester(qtyThis);
        case 2:
            System.out.println("What is the new quantity for next semester?");
            int qtyNext = scan.nextInt();
            editItem.setQtyNextSem(qtyNext);
    }
    return 0;

}

//Stores data in a string[], the int for qty is set to a string, then back to an int in the object creation
//qty is still inputed as an int by the user to disallow invalid input
public static String[] basicItem(Scanner scan){
    System.out.println("What is the name of the item?");
    String name = scan.nextLine();
    System.out.println("What is the model number of the item");
    String model = scan.nextLine();
    System.out.println("What is the part number of the item?");
    String part = scan.nextLine();
    System.out.println("What is the quantity of the item?");
    int qty = scan.nextInt();
    String strQty = String.valueOf(qty);

    return new String[]{name, model, part, strQty};
}

    //add qty_semester and qty_next_semester
public static int[] nonSerial(Scanner scan){
    System.out.println("What is the quantity required per semester?");
    int this_sem = scan.nextInt();
    System.out.println("What is the quantity required for next semester?");
    int next_sem = scan.nextInt();

    return new int[]{this_sem, next_sem};
}

    //add serial number
public static String serial(Scanner scan){
    System.out.println("What is the serial number?");
    String cereal = scan.nextLine();
    return cereal;
}

    //add qty type
public static String consumable(Scanner scan){
    System.out.println("What is the unit quantity type?");
    String type = scan.nextLine();
    return type;
}

    //add revision number
public static String manual(Scanner scan){
    System.out.println("What is the revision number?");
    String rev = scan.nextLine();
    return rev;
}

    //checks the alert status of an object, and prints the message associated
public static void checkAlert(int alert){
        if(alert==2){
            System.out.println("NOT ENOUGH FOR THIS SEMESTER");
        }else if(alert==1){
            System.out.println("NOT ENOUGH FOR NEXT SEMESTER");
        }
    }
}
