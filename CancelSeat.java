package Rail;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
@WebServlet("/CancelSeat")
public class CancelSeat extends HttpServlet
{
private static final long serialVersionUID=1L;
public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
	HttpSession session=req.getSession();
res.setContentType("text/html");	
Connection con=null;
PrintWriter out=res.getWriter();
String nam=(String)session.getAttribute("Username");
String usid=(String)session.getAttribute("Userid");
//String pass=(String)session.getAttribute("Password");
if(nam==null)
{
	System.out.println("You need to logn first Cancel seat");
	//req.getRequestDispatcher("userslogin.html").forward(req,res);
}

else
{try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
	PreparedStatement ps=con.prepareStatement("Select name,adhar_no,seat_no,email,phone,depdate,from1,to1,pnr,clas from ticket where usid=?");
	ps.setString(1,usid);
	ResultSet rs=ps.executeQuery();
	if(rs!=null)
	{
		System.out.println("Your id :"+usid);
	}
	out.println("<h1>"+nam+"Welcome to your Account:</h1>");
	out.print("<h3>Choose the seat to be cancelled :</h3>");
	out.print("<html><head></head><body><style>body{background-color:#1ced81;}th,td{border: 1px solid black;}</style><table style='border: 1px solid black' align='center'><tr><th>Passenger Name</th><th>Adhar No</th><th>Seat No.</th>");
	out.print("<th>Email</th><th>Phone</th><th>Date</th><th>From</th><th>To</th><th>PNR</th><th>Class</th><th>Check To cancel</th></tr>");
	out.println("<form action='ConfirmCancel' method='get'");
	while(rs.next())
	{
		String name=rs.getString(1);
		int adhr=Integer.parseInt(rs.getString(2));
		int seatno=Integer.parseInt(rs.getString(3));
		String email=rs.getString(4);
		int phn=Integer.parseInt(rs.getString(5));
		String depdt=rs.getString(6);
		String frm=rs.getString(7);
		String to=rs.getString(8);
		int pnr=Integer.parseInt(rs.getString(9));
		String class1=rs.getString(10);
	out.print("<tr><td>"+name+"</td><td>"+adhr+"</td><td>"+seatno+"</td><td>"+email+"</td><td>"+phn+"</td><td>"+depdt+"</td><td>"+frm+"</td><td>"+to+"</td>");
	out.print("<td>"+pnr+"</td><td>"+class1+"</td><td><input type='checkbox' name='checkbox1' value="+pnr+" /></td></tr>");
	
	}
	out.print("<input type='Submit' name='sub' value='Cancel Seat'>");
	out.print("</form></table>");
	out.print("<a href='menu.html'>Go Back to Menu</a>");
out.print("</body></html>");
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
	catch(Exception e1)
	{
		e1.printStackTrace();
	}
}
out.close();
}
}
}
