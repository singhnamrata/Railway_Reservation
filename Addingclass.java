package Rail;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

//@WebServlet("/Addingclass")
public class Addingclass extends HttpServlet
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
			System.out.print("choice="+choice+"Tno= "+tno);
			String Class=req.getParameter("cls");
			int nseats=Integer.parseInt(req.getParameter("no_seats"));
			System.out.println("Tno="+tno+"Class="+Class+"No Of Seats="+nseats);
			PreparedStatement ps=con.prepareStatement("insert into trainclass values(?,?,?)");
			ps.setInt(1,tno);
			ps.setString(2,Class);
			ps.setInt(3,nseats);
			int i=ps.executeUpdate();
			if(i>0)
			{out.print("<body style='background-color:#1ced81;'>");
				out.print("<br><br><h2>Want to add more classes in the train?</h2>");
				out.println("<br><br><a href='addclass.html'>Yes</a>");
				out.println("<br><br><a href='addtrainsuccess.html'>No</a>");
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
