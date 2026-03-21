public class Administrator extends User
{

    public Administrator(String firstName, String lastName, String username)
    {
        super(firstName, lastName, username);

    }

    @Override
    public boolean isAdmin()
    {
        return true;
    }

    
    @Override
    public String toString(){
        return super.toString()+ this.isAdmin();
    }    

}