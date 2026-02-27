public class Consumable extends Non_Serialized{
    private String qtyType;

    public Consumable(String name, String model, String partNum, int qty, int qty_semester, int qty_next_semester, String qtyType){

        super(name, model, partNum, qty, qty_semester, qty_next_semester);
        this.qtyType = qtyType;
    }

    public String getQtyType(){
        return qtyType;
    }

    public void setQtyType(){
        this.qtyType = qtyType;
    }


    //printing out info, can't inherant due to added qty type
    @Override
    public String toString(){
        return "Name: " + name + "\nModel: " + model + "\nPart Number: " + partNum + 
        "\nQuantity in Stock: " + qty + " " + qtyType + "\nQuantity for this Semester: " + qty_semester + " " + qtyType + "\nQuantity for Next Semester: " 
        + qty_next_semester + " " + qtyType;
    }
}
