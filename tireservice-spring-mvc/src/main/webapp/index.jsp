<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>login form</title>
    </head>
    <body>
        <%
            if (request.getParameter("error") == null) {
                out.println("Please enter your login.");
            } else {
                out.println("Invalid login attempt!");
            }
        %>
        <br>
        <form method="post" action="login">
        Email:<input type="text" name="email" /><br/>
        Password:<input type="password" name="pass" /><br/>
        <input type="submit" value="login" />
        </form>
    </body>
</html>
