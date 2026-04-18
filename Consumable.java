public class Consumable extends Item_Parent{
    protected double qty;
    protected double qty_semester;
    protected double qty_next_semester;
    protected int alert;//0 for no alert, 1 for not enough next semester, 2 for not enough this semester
    private String qtyType;

    public Consumable(String name, String model, String partNum, double qty, double qty_semester, double qty_next_semester, String qtyType){

        super(name, model, partNum);
        this.qty = qty;
        this.qty_semester = qty_semester;
        this.qty_next_semester = qty_next_semester;
        this.qtyType = qtyType;

        checkAlert();
    }

    public double getQty(){
        return qty;
    }

    public double getQtySemester(){
        return qty_semester;
    }

    public double getQtyNextSem(){
        return qty_next_semester;
    }

    public int getAlert(){
        return alert;
    }

    public String getQtyType(){
        return qtyType;
    }

    public void setQty(double qty){
        this.qty = qty;
        checkAlert();
    }

    public void setQtySemester(double qty_semester){
        this.qty_semester = qty_semester;
        checkAlert();
    }

    public void setQtyNextSem(double qty_next_semester){
        this.qty_next_semester = qty_next_semester;
        checkAlert();
    }

    public void setAlert(int alert){
        this.alert = alert;
    }

    public void setQtyType(String qtyType){
        this.qtyType = qtyType;
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

    //printing out info, can't inherant due to added qty type
    @Override
    public String toString(){
        return "Name: " + name + "\nModel: " + model + "\nPart Number: " + partNum + 
        "\nQuantity in Stock: " + qty + " " + qtyType + "\nQuantity for this Semester: " + qty_semester + " " 
        + qtyType + "\nQuantity for Next Semester: " 
        + qty_next_semester + " " + qtyType;
    }
}
