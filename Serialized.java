public class Serialized extends Item_Parent{
    //extends Non_serialized bc has qty_semester and qty_next_semester
    private String serialNum;

    //constructor
    public Serialized(String name, String model, String partNum, String serialNum){

        super(name, model, partNum);
        this.serialNum = serialNum;
    }

    //getter
    public String getSerialNum(){
        return serialNum;
    }

    //setter
    public void setSerialNum(String serialNum){
        this.serialNum = serialNum;
    }

    //adding serial number to print out
    @Override
    public String toString(){
        return super.toString()+ "\nSerial Number: " + serialNum;
    }
}
