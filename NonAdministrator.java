public class NonAdministrator extends User{
    
    private boolean isAdmin;
    
    
    public NonAdministrator(String firstName, String lastName, String username)
    {
        super(firstName, lastName, username);
        isAdmin = false;

    }

    @Override
    public String toString(){
        return super.toString()+ isAdmin;
    }    


}
    

