public class Non_Serialized {
    private String name;
    private String model;
    private String partNum;
    private int qty;
    private int qty_semester;
    private int qty_next_semester;
    private int alert;//0 for no alert, 1 for not enough next semester, 2 for not enough this semester

    //Constructor 

    public Non_Serialized(String name, String model, String partNum, int qty, int qty_semester, int qty_next_semester){
        this.name = name;
        this.model = model;
        this.partNum = partNum;
        this.qty = qty;
        this.qty_semester = qty_semester;
        this.qty_next_semester = qty_next_semester;
        
        checkAlert();

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

    public int getQtySemester(){
        return qty_semester;
    }

    public int getQtyNextSem(){
        return qty_next_semester;
    }

    public int getAlert(){
        return alert;
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
        checkAlert();
    }

    public void setQtySemester(int qty_semester){
        this.qty_semester = qty_semester;
        checkAlert();
    }

    public void setQtyNextSem(int qty_next_semester){
        this.qty_next_semester = qty_next_semester;
        checkAlert();
    }

    public void setAlert(int alert){
        this.alert = alert;
    }



    public int checkAlert(){
        if (qty<qty_next_semester){
            if(qty<qty_semester){
                this.alert = 2;
                return alert;
            }
            this.alert = 1;
            return alert;
        }else{
            this.alert = 0;
            return alert;
        }
    }

    @Override
    public String toString(){
        return "Name: " + name + "\nModel: " + model + "\nPart Number: " + partNum + 
        "\nQuantity in Stock: " + qty + "\nQuantity for this Semester: " + qty_semester + 
        "\nQuantity for Next Semester: " + qty_next_semester;
    }
}
