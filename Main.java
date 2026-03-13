import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        UserDatabase userDB = new UserDatabase();

        int initialChoice = 0;
        do{
            initialChoice = initialOptions(scan);
            processInitialOption(scan, initialChoice, userDB);
        } while (initialChoice != 3);
        
        scan.close();


        //menu choice selection, ran through a separate function
        //add if statement when user works
        nonAdmin();
        //admin();
        return;
        
    }

    public static int initialOptions(Scanner scan)
    {
        System.out.println("1. Create an account.");
        System.out.println("2. Log in");
        String choiceStr = scan.nextLine();
        int choice = Integer.parseInt(choiceStr);
        return choice;

    }

    public static void processInitialOption(Scanner scan, int initialChoice, UserDatabase userDB)
    {
        switch(initialChoice)
        {
            case 1: createAccount(scan, userDB); break;
            case 2: User user = logIn(scan, userDB); break;
            default: System.out.println("Invalid selection"); break;
                     
        }
    }

    public static void createAccount(Scanner scan, UserDatabase userDB)
    {
        User user = null;
        System.out.print("Are you a professor or TA? (type P for professor and T for TA): ");
        String role = scan.nextLine().toUpperCase();
        while(!(role.equals("P")) && !(role.equals("T")))
        {
            System.out.println("Enter only P or T: ");
            role = scan.nextLine().toUpperCase();
        }

        System.out.print("Enter your first name: ");
        String firstName = scan.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scan.nextLine();

        System.out.print("Enter your username: ");
        String username = scan.nextLine();

        System.out.print("Enter your password: ");
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

        if(role.equals("P"))
        {
            user = new Administrator(firstName, lastName, username);
            user.setPassword(password);

        }
        else if(role.equals("T"))
        {
            user = new NonAdministrator(firstName, lastName, username);
            user.setPassword(password);
        }
        else{

            user = null;
            System.out.println("Type of user not specified. No account created.");
            
        }

    }

    public static void admin(){
        int choice = menuSelectionAdmin();
        switch(choice){
        //exit
        case 1:
            System.out.println("Exiting ALM IMS........");
            break;

        //edit an item
        case 2:
            edit();
            break;

        //create an item
        case 3:
            create();
            break;

        //view an item
        case 4:
            break;

        }
    }

    public static void nonAdmin(){
        int choice = menuSelection();
        switch(choice){
        //exit
        case 1:
            System.out.println("Exiting ALM IMS........");
            break;

        //edit an item
        case 2:
            edit();
            break;

        //create an item
        case 3:
            create();
            break;

        //view an item
        case 4:
            break;

        }
}

    public static User logIn(Scanner scan, UserDatabase userDB)
    {
        System.out.print("Username: ");
        String username = scan.nextLine();
        

        System.out.print("Password: ");
        String password = scan.nextLine();

        int userIndex = userDB.findUser(username);
        User user = userDB.findUserByIndex(userIndex);


        if(user.getUsername() != username || user.getPassword() != password)
        {
            System.out.println("Incorrect login credentials.");
            return null;
            
        }

        return user;

    }





    //menu selection, returns an int for choice of the following, ran in main
public static int menuSelection(){
    Scanner input = new Scanner(System.in);
    System.out.println("========================");
    System.out.println("Select one of the following:");
    System.out.println("1. Exit IMS");
    System.out.println("2. Edit an item");
    System.out.println("3. Create an item");
    System.out.println("4. View an item");
    System.out.println( "========================");
    int menu = input.nextInt();
    return menu;
}

public static int menuSelectionAdmin(){
    Scanner input = new Scanner(System.in);
    System.out.println("========================");
    System.out.println("Select one of the following:");
    System.out.println("1. Exit IMS");
    System.out.println("2. Edit an item");
    System.out.println("3. Create an item");
    System.out.println("4. View an item");
    System.out.println("5. View a user");
    System.out.println("6. Change a user");
    System.out.println( "========================");
    int menu = input.nextInt();
    return menu;
}

public static int item_type_selection(){
    Scanner input = new Scanner(System.in);
    System.out.println("========================");
    System.out.println("Select what item type");
    System.out.println("1. Non-Serialized");
    System.out.println("2. Serialized");
    System.out.println("3. Consumable");
    System.out.println("4. Manual");
    System.out.println("========================");
    int choice = input.nextInt();
    return choice;
}
    //similar to menu in design, returns a choice as an integer to run through if statements
    //can maybe break the menu into a separate function here
public static void create(){

    int choice = item_type_selection();    

    //The following are all similar, all share basicItem() to get the information all classes need
    //advanced() is information both non-serialized and serialized share and consumables (qty_semester, qty_next_semester)
    //Manuals, serialized, and consumables all have an additional function
    //Functions are a basic IO with the user asking the information
    //After the object is made checkAlert() is run and will display an alert if valid
    //Currently after creation it prints the object for T/S


    switch(choice){

    //Non-Serialized
    case 1:
        String basicNS[] = basicItem();
        int advancedNS[] = nonSerial();
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
        String basicS[] = basicItem();
        int advancedS[] = nonSerial();
        String serial = serial();
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
        String basicC[] = basicItem();
        String consume = consumable();
        int advancedC[] = nonSerial();
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
        String basicM[] = basicItem();
        String manual = manual();
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

public static void edit(){
    int choice = item_type_selection();

    //maybe separate method below
    System.out.println("Which variable would you like to change?");
    Scanner input = new Scanner(System.in);
    int change = input.nextInt();

        //search for an object here*************
        //Print object here with numbered lines*************
        switch(choice){
        //non-serial
        case 1:
            Non_Serialized editNon_Serialized = //call method for DB item here 
            if(choice <=4){
            editBasic(change, editNon_Serialized);
            }else{
            editQts(change);
            } 
            //pass back to DB
        //serialized
        case 2:
            Serialized editSerialized = //call method for DB item
            if(choice <=4){
            editBasic(change);
            }else if(choice <=6){
            editQts(change);
            }else{
            editSerial();
            }   
        //consumables
        case 3:
            Consumable editConsumable = //call method for DB item
            if(choice <=4){
            editBasic(change);
            }else{
            editQts(choice);
            }
        //manual
        case 4:
            Manual editManual = //call method for DB item
            if(choice <=4){
            editBasic(choice);
            }else{
            editRev();
            }
        }
}

public static Item_Parent editBasic(int choice, Item_Parent editItem){
    Scanner input = new Scanner(System.in);
    switch(choice){
        case 1:
            System.out.println("What is the new name?");
            String name = input.nextLine();
            editItem.setName(name);
        case 2:
            System.out.println("What is the new model?");
            String model = input.nextLine();
            return model;
        case 3:
            System.out.println("What is the new part number?");
            String partNum = input.nextLine();
            return partNum;
        case 4:
            System.out.println("What is the new quantity?");
            int qty = input.nextInt();
            return Qty;
    }
    return editItem;
}

public static int editSerial(){//serialized class
    Scanner input = new Scanner(System.in);
    System.out.println("What is the new serial number?");
    int serial = input.nextInt();
    return serial;
}

public static int editRev(){//manuals
    Scanner input = new Scanner(System.in);
    System.out.println("What is the new revision number?");
    int rev = input.nextInt();
    return rev;
}

public static int editQts(int choice){//non-serialized class
    Scanner input = new Scanner(System.in);
    switch(choice+4){
        case 1:
            System.out.println("What is the new quantity for this semester?");
            int qtyThis = input.nextInt();
            return qtyThis;
        case 2:
            System.out.println("What is the new quantity for next semester?");
            int qtyNext = input.nextInt();
            return qtyNext;
    }
    return void;

}

//Stores data in a string[], the int for qty is set to a string, then back to an int in the object creation
//qty is still inputed as an int by the user to disallow invalid input
public static String[] basicItem(){
    Scanner input = new Scanner(System.in);
    System.out.println("What is the name of the item?");
    String name = input.nextLine();
    System.out.println("What is the model number of the item");
    String model = input.nextLine();
    System.out.println("What is the part number of the item?");
    String part = input.nextLine();
    System.out.println("What is the quantity of the item?");
    int qty = input.nextInt();
    String strQty = String.valueOf(qty);

    return new String[]{name, model, part, strQty};
}

    //add qty_semester and qty_next_semester
public static int[] nonSerial(){
    Scanner input = new Scanner(System.in);
    System.out.println("What is the quantity required per semester?");
    int this_sem = input.nextInt();
    System.out.println("What is the quantity required for next semester?");
    int next_sem = input.nextInt();

    return new int[]{this_sem, next_sem};
}

    //add serial number
public static String serial(){
    Scanner input = new Scanner(System.in);
    System.out.println("What is the serial number?");
    String cereal = input.nextLine();
    return cereal;
}

    //add qty type
public static String consumable(){
    Scanner input = new Scanner(System.in);
    System.out.println("What is the unit quantity type?");
    String type = input.nextLine();
    return type;
}

    //add revision number
public static String manual(){
    Scanner input = new Scanner(System.in);
    System.out.println("What is the revision number?");
    String rev = input.nextLine();
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
