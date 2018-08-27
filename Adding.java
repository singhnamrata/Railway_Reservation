package Rail;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
//@WebServlet("/Adding")
public class Adding extends HttpServlet
{
	private static final long serialVersionUID=1L;
public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException
{
	HttpSession asession=req.getSession();
	Connection con=null;
	res.setContentType("text/html");
	
String tname=(String)req.getParameter("tname");
String tsrc=(String)req.getParameter("tsrc");
String tdate=(String)req.getParameter("tdate");
String tdest=(String)req.getParameter("tdest");
 int tno=Integer.parseInt(req.getParameter("tno1"));
System.out.println("tno="+tno);
try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
PreparedStatement ps=con.prepareStatement("Insert into train values(?,?,?,?,?)");
ps.setInt(1,tno);
ps.setString(2,tname);
ps.setString(3,tsrc);
ps.setString(4,tdate);
ps.setString(5,tdest);
int i=ps.executeUpdate();
System.out.println("i="+i);
if(i>0)
{
	asession.setAttribute("tno",tno);
	req.getRequestDispatcher("addclass.html").forward(req,res);
}
}
catch(Exception e)
{
	e.printStackTrace();
}
}
}
