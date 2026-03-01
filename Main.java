import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        
        //menu choice selection, ran through a separate function
        int choice = menuSelection();

        switch(choice){
        //exit
        case 1:
            System.out.println("Exiting ALM IMS........");
            break;

        //edit an item
        case 2:
            break;

        //create an item
        case 3:
            create();
            break;

        //view an item
        case 4:
            break;

        }
        return;
        
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

    //similar to menu in design, returns a choice as an integer to run through if statements
    //can maybe break the menu into a separate function here
public static void create(){
    Scanner input = new Scanner(System.in);
    System.out.println("========================");
    System.out.println("Select what item type");
    System.out.println("1. Non-Serialized");
    System.out.println("2. Serialized");
    System.out.println("3. Consumable");
    System.out.println("4. Manual");
    System.out.println("========================");
    int choice = input.nextInt();
    

    //The following are all similar, all share basicItem() to get the information all classes need
    //advanced() is information both non-serialized and serialized share and consumables (qty_semester, qty_next_semester)
    //Manuals, serialized, and consumables all have an additional function
    //Functions are a basic IO with the user asking the information
    //After the object is made checkAlert() is run and will display an alert if valid
    //Currently after creation it prints the object for T/S


    switch(choice){

    //Non-Serialized
    case 1:
        String basic[] = basicItem();
        int advanced[] = nonSerial();
        Non_Serialized newItem = new Non_Serialized(
        basic[0],
        basic[1],
        basic[2],
        Integer.parseInt(basic[3]),
        advanced[0],
        advanced[1]
        );
        checkAlert(newItem.checkAlert());
        System.out.println(newItem);
        break;

    //Serialized
    case 2:
        String basic[] = basicItem();
        int advanced[] = nonSerial();
        String serial = serial();
        Serialized newItem = new Serialized(
        basic[0],
        basic[1],
        basic[2],
        Integer.parseInt(basic[3]),
        advanced[0],
        advanced[1],
        serial
        );
        checkAlert(newItem.checkAlert());
        System.out.println(newItem);
        break;

    //Consumable
    case 3:
        String basic[] = basicItem();
        String consume = consumable();
        int advanced[] = nonSerial();
        Consumable newItem = new Consumable(
        basic[0],
        basic[1],
        basic[2],
        Integer.parseInt(basic[3]),
        advanced[0],
        advanced[1],
        consume
        );
        checkAlert(newItem.checkAlert());
        System.out.println(newItem);
        break;

    //Manual
    case 4:
        String basic[] = basicItem();
        String manual = manual();
        Manual newItem = new Manual(
        basic[0],
        basic[1],
        basic[2],
        Integer.parseInt(basic[3]),
        manual
        );
        System.out.println(newItem);
        break;
    }
    return;

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