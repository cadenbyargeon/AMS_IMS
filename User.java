public abstract class User {
    private String firstName;
    private String lastName;
    private String username; 
    private String password;
    private boolean isAdmin;


    public User(String firstName, String lastName, String username, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
       
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setUsername(String username)
    {
        this.username = username;
        
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


    public String toString()
    {
        return "First Name: " + firstName + "\nLast Name: " + lastName + "\nUsername: " + 
        username + "\nPassword: " + password + "\nAdmin Status: ";
    }

}
