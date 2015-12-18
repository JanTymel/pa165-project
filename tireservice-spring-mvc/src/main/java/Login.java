import cz.muni.fi.pa165.tireservice.dto.UserDto;
import cz.muni.fi.pa165.tireservice.facade.UserFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;

public class Login extends HttpServlet {
        
    @Autowired
    private UserFacade userFacade;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("email");
        String pass = request.getParameter("pass");
        
        if(checkUser(name, pass))
        {
            response.sendRedirect("home.jsp");
        }
        else
        {
           out.println("Username or Password incorrect");
           response.sendRedirect("index.jsp?error=invalid");
        }
    }  
    
    public boolean checkUser(String name,String pass) 
    {
        List<UserDto> users = userFacade.getUsersByName(name);
        return ("password".equals(pass) && (!users.isEmpty()));               
    } 
}
