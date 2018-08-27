package Rail;
import java.io.*;
//import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
@WebServlet("/AdminLogout")
public class AdminLogout extends HttpServlet
{
private static final long serialVersionUID=1L;
public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
res.setContentType("text/html");
HttpSession asession=req.getSession();
asession.removeAttribute("Adminid");
asession.removeAttribute("Adminname");
asession.removeAttribute("Password");
asession.invalidate();
res.sendRedirect("homepage.html");
}
}
