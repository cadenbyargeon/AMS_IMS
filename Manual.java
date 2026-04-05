public class Manual extends Item_Parent{
    private String revision;
    private int qty;

    //constructor
    public Manual(String name, String model, String partNum, int qty, String revision){

        super(name, model, partNum);
        this.qty = qty;
        this.revision = revision;
    }

    //getter

    public int getQty(){
        return qty;
    }

    public String getRevision(){
        return revision;
    }

    //setter
    public void setRevision(String revision){
        this.revision = revision;
    }

    public void setQty(int qty){
        this.qty = qty;
    }

    //adding revision to print out
    @Override
    public String toString(){
        return super.toString()+ "\nQuantity: " + qty + "\nRevision: " + revision;
    }
}
