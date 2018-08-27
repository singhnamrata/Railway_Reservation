package Rail;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmDeleteUser extends HttpServlet{
	private static final long  serialVersionUID =1l;
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
	{
	response.setContentType("text/html");	
	PrintWriter out=response.getWriter();
	Connection con=null;
	PreparedStatement ps=null;
	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
		String check[]=request.getParameterValues("check");
		if(check==null)
		{
			out.print("\nNo user has been selected for deletion");
			out.print("<a href='DeleteUser'>Go back to select user for deletion</a>");
			out.print("<a href='adminmenu.html'>Go back to main menu</a>");
		}
		for(int i=0;i<check.length;i++)
		{
		ps=con.prepareStatement("delete from userlog where usid=?");
		ps.setString(1,check[i]);
		ps.executeUpdate();
		}
		out.print("<script type='text/javascript'>alert('User(s) deleted successfully')</script>");
		request.getRequestDispatcher("adminmenu.html").forward(request,response);
		
	}
	catch(Exception e)
	{
	e.printStackTrace();	
	}
	}

}
