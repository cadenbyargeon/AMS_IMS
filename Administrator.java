public class Administrator extends User
{
    private boolean isAdmin;

    public Administrator(String firstName, String lastName, String username)
    {
        super(firstName, lastName, username);
        isAdmin = true;

    }

    
    @Override
    public String toString(){
        return super.toString()+ isAdmin;
    }    

}