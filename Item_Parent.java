public class Item_Parent{
    protected String name;
    protected String model;
    protected String partNum;


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

    //for printing output
     @Override
    public String toString(){
        return "Name: " + name + "\nModel: " + model + "\nPart Number: " + partNum;
    }
}