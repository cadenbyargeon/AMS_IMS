public class NonAdministrator extends User{
    
    
    
    
    public NonAdministrator(String firstName, String lastName, String username)
    {
        super(firstName, lastName, username);

    }

    @Override
    public boolean isAdmin()
    {
        return false;
    }

    @Override
    public String toString(){
        return super.toString()+ this.isAdmin();
    }    


}
    

