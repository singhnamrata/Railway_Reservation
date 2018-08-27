package Rail;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
@WebServlet("/CheckPassengers")
public class CheckPassengers extends HttpServlet
{
private final static  long serialVersionUID=1L;
public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{   HttpSession asession=req.getSession();
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	Connection con=null;
	//String aid=(String)session.getAttribute("Adminid");
	String nam=(String)asession.getAttribute("Adminname");
	String [] trainno=req.getParameterValues("checkbox1");
	if(nam==null)
	{
	System.out.print("To check you need to login first");
	req.getRequestDispatcher("adminlogin.html").forward(req,res);
	}
	
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
		out.print("<html><head><style>body{background-color:#1ced81;}th,td{border: 1px solid black;}</style></head><body><table><tr><th>Passenger Name</th><th>Adhar No</th><th>Seat No.</th>");
		out.print("<th>Email</th><th>Phone</th><th>Date</th><th>From</th><th>To</th><th>PNR</th><th>Class</th></tr>");
		for(int i=0;i<trainno.length;i++){
			out.println("<br><br><h1>Paasenger List for Train :</h1>"+trainno[i]+"<br><br>");
		PreparedStatement ps=con.prepareStatement("Select T.name,T.adhar_no,T.seat_no,T.email,T.phone,T.depdate,T.from1,T.to1,T.pnr,T.clas from ticket T inner join booking B on T.b_id=B.b_id  where B.t_no=? ");
		ps.setInt(1,Integer.parseInt(trainno[i]));
		ResultSet rs=ps.executeQuery();
	
		
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
		out.print("<td>"+pnr+"</td><td>"+class1+"</td></tr>");
		}
		}
		out.print("</table>");
		out.print("<a href='adminmenu.html'>Go Back to Menu</a>");
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
	out.close();
	}
}
}