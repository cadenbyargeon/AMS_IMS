public class Item_Parent{
    protected String name;
    protected String model;
    protected String partNum;
    protected int qty;


    //constructor

    public Item_Parent(String name, String model, String partNum, int qty){
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

    public int getQty(){
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

    public void setQty(int qty){
        this.qty = qty;
    }

     @Override
    public String toString(){
        return "Name: " + name + "\nModel: " + model + "\nPart Number: " + partNum + 
        "\nQuantity in Stock: " + qty;
    }
}