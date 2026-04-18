public class Item_Parent{
    protected String name;
    protected String model;
    protected String partNum;
    protected int ID;


    //constructor

    public Item_Parent(String name, String model, String partNum){
        this.name = name;
        this.model = model;
        this.partNum = partNum;

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

    public int getID(){
        return ID;
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

    public void setID(int ID){
        this.ID = ID;
    }

    //for printing output
     @Override
    public String toString(){
        return "Name: " + name + "\nModel: " + model + "\nPart Number: " + partNum;
    }
}