package Rail;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/AddTrains")
public class AddTrains extends HttpServlet
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
	System.out.print(nam+"You are welcome to add train");
	if(nam==null||nam=="")
	{
		System.out.println("You,need to login first");
		req.getRequestDispatcher("adminlogin.html").forward(req,res);
		
	}
	
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
			System.out.println(nam+"You,are welcome");
			
			/*try{
			req.getRequestDispatcher("addtrain.html").include(req,res);
			}
			catch(Exception e)
			{
			e.printStackTrace();	
			}*/
			int tno=0;
			if(tno==0){
				//System.out.println("Haha1");
				//req.getRequestDispatcher("addtrain.html").forward(req,res);
			/*out.println("<html><head></head><body><form action='Adding' method='get'>");
			out.println("	<input type='number' placeholder='Train_no' name='tno1' />");
            out.print("<input type='text' placeholder='Train_Name' name='tname' />");
            out.print("<input type='text' placeholder='From' name='tsrc' />");
            out.print("<input type='text' placeholder='Date' name='tdate' />");
            out.print("<input type='text' placeholder='Destination' name='tdest' />");
            out.print("<input type='submit' value='Submit' />");
            out.print("</form></body></html>");*/
            }
		
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
