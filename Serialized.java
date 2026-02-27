public class Serialized extends Non_Serialized{
    //extends Non_serialized bc has qty_semester and qty_next_semester
    private String serialNum;

    //constructor
    public Serialized(String name, String model, String partNum, int qty, int qty_semester, int qty_next_semester, String serialNum){

        super(name, model, partNum, qty, qty_semester, qty_next_semester);
        this.serialNum = serialNum;
    }

    //getter
    public String getSerialNum(){
        return serialNum;
    }

    //setter
    public void setSerialNum(){
        this.serialNum = serialNum;
    }

    //adding serial number to print out
    @Override
    public String toString(){
        return super.toString()+ "\nSerial Number: " + serialNum;
    }
}
