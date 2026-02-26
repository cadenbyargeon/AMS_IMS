import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        //Statement statement = connection.createStatement();
        //ResultSet non_serialized = statement.executeQuery();
        //ResultSet serialized = statement.executeQuery();
        //ResultSet consumable = statement.executeQuery();
        //ResultSet manual = statement.executeQuery();
        
        int choice = menuSelection();

        //exit
        if(choice == 1){
            return;
        }

        //edit an item
        if(choice == 2){
            return;
        }

        //create an item
        if(choice == 3){
            create();
            return;
        }

        //view an item
        if(choice == 4){
            return;
        }
        
    }

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
    
    //Non-Serialized
    if(choice == 1){
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
        
        return;
    }

    //Serialized
    if(choice == 2){
        return;
    }

    //Consumable
    if(choice == 3){
        return;
    }

    //Manual
    if(choice ==4){
        return;
    }

}

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

public static int[] nonSerial(){
    Scanner input = new Scanner(System.in);
    System.out.println("What is the quantity required per semester?");
    int this_sem = input.nextInt();
    System.out.println("What is the quantity required for next semester?");
    int next_sem = input.nextInt();

    return new int[]{this_sem, next_sem};
}

public static void checkAlert(int alert){
        if(alert==2){
            System.out.println("NOT ENOUGH FOR THIS SEMESTER");
        }else if(alert==1){
            System.out.println("NOT ENOUGH FOR NEXT SEMESTER");
        }
    }

}
