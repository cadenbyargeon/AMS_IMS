public class Item_Parent{
    protected String name;
    protected String model;
    protected String partNum;
    protected double qty;


    //constructor

    public Item_Parent(String name, String model, String partNum, double qty){
        this.name = name;
        this.model = model;
        this.partNum = partNum;
        this.qty = qty;

    }

    //getters

    public String getName(){
        return name;
    }

    public String getModel(){
        return model;  
    }

    public String getPartNum(){
        return partNum;
    }

    public double getQty(){
        return qty;
    }



    //setters

    public void setName(String name){
        this.name = name;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setPartNum(String partNum){
        this.partNum = partNum;
    }

    public void setQty(double qty){
        this.qty = qty;
    }

    //for printing output
     @Override
    public String toString(){
        return "Name: " + name + "\nModel: " + model + "\nPart Number: " + partNum + 
        "\nQuantity in Stock: " + qty;
    }
}