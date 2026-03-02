public class Administrator extends User
{
    private boolean isAdmin;

    public Administrator(String firstName, String lastName, String username, String password)
    {
        super(firstName, lastName, username, password);
        isAdmin = true;

    }

    
    @Override
    public String toString(){
        return super.toString()+ isAdmin;
    }    

}