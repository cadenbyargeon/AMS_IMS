public class Non_Serialized extends Item_Parent {
    protected int qty_semester;
    protected int qty_next_semester;
    protected int alert;//0 for no alert, 1 for not enough next semester, 2 for not enough this semester

    //Constructor 

    public Non_Serialized(String name, String model, String partNum, int qty, int qty_semester, int qty_next_semester){
        super(name, model, partNum, qty);
        this.qty_semester = qty_semester;
        this.qty_next_semester = qty_next_semester;
        
        checkAlert();

    }
    
    //getters 

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



    //returns an alert value, and only one based off severity (nested)
    //checkAlert() is ran after every qty setter to update if any information changes
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

    //adding on the new variables to the printing statement
    @Override
    public String toString(){
        return super.toString() + "\nQuantity for this Semester: " + qty_semester + "\nQuantity for Next Semester: " + qty_next_semester;
    }
}
