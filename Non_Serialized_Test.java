public class Non_Serialized_Test {
    public static void main (String[] args){
        Non_Serialized test1 = new Non_Serialized(
            "test1",
            "model",
            "part number",
            100,
            10,
            20
        );
        System.out.println(test1);
        checkAlert(test1.checkAlert());
       //System.out.println("Would you like to edit the item? Enter y/n:")
        
    }

    public static void checkAlert(int alert){
        if(alert==2){
            System.out.println("NOT ENOUGH FOR THIS SEMESTER");
        }else if(alert==1){
            System.out.println("NOT ENOUGH FOR NEXT SEMESTER");
        }
    }
}
