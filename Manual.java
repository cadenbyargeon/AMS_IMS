public class Manual extends Item_Parent{
    private String revision;

    //constructor
    public Manual(String name, String model, String partNum, int qty, String revision){

        super(name, model, partNum, qty);
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
