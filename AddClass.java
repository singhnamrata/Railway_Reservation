package Rail;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

//@WebServlet("/AddClass")
public class AddClass extends HttpServlet
{
	private final static long serialVersionUID=1L;
public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
	HttpSession asession=req.getSession();
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	Connection con=null;
	String nam=null;
	nam=(String)asession.getAttribute("Adminname");
	int tno;
	tno=(int)asession.getAttribute("tno");
	System.out.print("Tno="+tno);
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
			boolean choice=true;
			req.getRequestDispatcher("addclass.html").forward(req,res);
			/*System.out.print("choice="+choice+"Tno= "+tno);
		
			out.println("<html><head></head><body><form method='get' action='Addingclass'>");
			
            out.print("<input type='text' placeholder='Class' name='cls' />");
            out.println("	<input type='number' placeholder='Number of seats' name='no_seats' />");
            out.print("<input type='submit' value='Submit' />");
            out.print("</form></body></html>");*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.close();
			}
			catch(SQLException e1)
			{
				e1.printStackTrace();
			}
		}
	
	out.close();
}
}
