public class Manual extends Item_Parent{
    private String revision;
    private double qty;

    //constructor
    public Manual(String name, String model, String partNum, double qty, String revision){

        super(name, model, partNum);
        this.qty = qty;
        this.revision = revision;
    }

    //getter
    public String getRevision(){
        return revision;
    }

    //setter
    public void setRevision(String revision){
        this.revision = revision;
    }

    //adding revision to print out
    @Override
    public String toString(){
        return super.toString()+ "\nRevision: " + revision;
    }
}
