package Rail;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
@WebServlet("/CancelTrain")
public class CancelTrain extends HttpServlet
{
private static final long serialVersionUID=1L;
public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
	HttpSession session=req.getSession();
res.setContentType("text/html");	
Connection con=null;
PrintWriter out=res.getWriter();
String nam=(String)session.getAttribute("Adminname");
String aid=(String)session.getAttribute("Adminid");
/*if(nam==null||nam=="")
{
	req.getRequestDispatcher("AdminLogin").forward(req,res);
}*/
out.print("<h1 align='center'>CANCEL TRAIN</h1>");

try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
	PreparedStatement ps=con.prepareStatement("Select t_no,t_name,t_start,t_depdate,t_dest from train ");
	//ps.setString(1,usid);
	ResultSet rs=ps.executeQuery();
	if(rs!=null)
	{
		System.out.println("Your id :"+aid);
	}
	//System.out.println(nam+" Welcome to yoour Account:");
	out.print("<html><head></head><body><style>body{background-color:#1ced81;}th,td{border: 1px solid black;}</style><table style='border: 1px solid black' align='center'>");
	out.print("<tr><th>Train_No</th><th>Train_name</th><th>Source</th><th>Destination</th><th>Date</th></tr>");
	out.println("<form action='ConfirmTrainCancel' method='get'");
	while(rs.next())
	{
		String name=rs.getString(2);
		int trainno=Integer.parseInt(rs.getString(1));
		//int seatno=Integer.parseInt(rs.getString(3));
		//String email=rs.getString(4);
		//int phn=Integer.parseInt(rs.getString(5));
		String depdt=rs.getString(4);
		String frm=rs.getString(3);
		String to=rs.getString(5);
	//	int pnr=Integer.parseInt(rs.getString(9));
		//String class1=rs.getString(6);
	out.print("<tr><td>"+trainno+"</td><td>"+name+"</td><td>"+frm+"</td><td>"+to+"</td><td>"+depdt+"</td>");
	out.print("<td><input type='checkbox' name='checkbox1' value="+trainno+" /></td></tr>");
	
	}
	out.print("<input type='Submit' name='sub' value='Cancel Train'>");
	out.print("</form></table>");
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
}
out.close();
}
}
