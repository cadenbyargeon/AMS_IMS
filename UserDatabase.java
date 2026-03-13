import java.util.ArrayList;
public class UserDatabase {
    ArrayList<User> users;

    public UserDatabase()
    {
        users = new ArrayList<User>();
    }

    public void addUser(User user)
    {
        if (user == null)
        {
            return;

        }

        if(findUser(user.getUsername()) != -1)
        {
            return;
        }

        users.add(user);
    }

    public int findUser(String username)
    {
        for (int i = 0; i < users.size(); i++)
        {
            User user = users.get(i);
            if(username.equals(user.getUsername()))
            {
                return i;
            }
        }

        return -1;

    }

    public User findUserByIndex(int index)
    {
        if (index < 0 || index >= users.size())
        {
            return null;
        }
        else 
        {
            return users.get(index);
        }
    }

    
}
