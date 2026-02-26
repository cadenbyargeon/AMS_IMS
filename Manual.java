public class Manual extends Item_Parent{
    private String revision;

    public Manual(String name, String model, String partNum, int qty, String revision){

        super(name, model, partNum, qty);
        this.revision = revision;
    }

    public String getRevision(){
        return revision;
    }

    public void setRevision(){
        this.revision = revision;
    }

    @Override
    public String toString(){
        return super.toString()+ "\nRevision: " + revision;
    }
}
