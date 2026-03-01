public class NonAdministrator extends User{
    
    private boolean isAdmin;
    
    
    public NonAdministrator(String firstName, String lastName, String username, String password)
    {
        super(firstName, lastName, username, password);
        isAdmin = false;

    }

    


}
    

