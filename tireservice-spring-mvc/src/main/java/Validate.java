import org.springframework.beans.factory.annotation.Autowired;

public class Validate
{
    @Autowired
    private UserFacade userFacade;
    
    public static boolean checkUser(String email,String pass) 
    {
        return "password".equals(pass) && categoryFacade.;               
    }   
}
