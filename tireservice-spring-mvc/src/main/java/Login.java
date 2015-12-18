import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet {
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        
        if(Validate.checkUser(email, pass))
        {
            response.sendRedirect("home.jsp");
        }
        else
        {
           out.println("Username or Password incorrect");
           response.sendRedirect("index.jsp?error=invalid");
        }
    }  
}
