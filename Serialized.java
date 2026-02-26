public class Serialized extends Non_Serialized{
    private String serialNum;

    public Serialized(String name, String model, String partNum, int qty, int qty_semester, int qty_next_semester, String serialNum){

        super(name, model, partNum, qty, qty_semester, qty_next_semester);
        this.serialNum = serialNum;
    }

    public String getSerialNum(){
        return serialNum;
    }

    public void setSerialNum(){
        this.serialNum = serialNum;
    }

    @Override
    public String toString(){
        return super.toString()+ "\nSerial Number: " + serialNum;
    }
}
