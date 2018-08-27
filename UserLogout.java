 package Rail;
import java.io.*;
//import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
@WebServlet("/UserLogout")
public class UserLogout extends HttpServlet
{
private static final long serialVersionUID=1L;
public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
res.setContentType("text/html");
HttpSession session=req.getSession();
System.out.println("User is : "  + session.getAttribute("Username"));
session.removeAttribute("Userid");
//session.setAttribute("Username",null);
session.removeAttribute("Username");
session.removeAttribute("Password");
session.invalidate();
res.sendRedirect("homepage.html");
}
}