package Rail;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

//@WebServlet("/Passengers")
public class Passengers extends HttpServlet{
	
	private static final long serialVersionUID=1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{HttpSession session = req.getSession();
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		Connection con=null;
		String nam=(String)session.getAttribute("Username");
		String uid=(String)session.getAttribute("Userid");
		//String psw=(String)session.getAttribute("Password");
		int pnum=(int)session.getAttribute("pno");
		String a=req.getParameter("nam1");
		int b=Integer.parseInt(req.getParameter("adhr"));
		String c=req.getParameter("mail");
		int d=Integer.parseInt(req.getParameter("phn"));
		String e=(String)session.getAttribute("src");
		String f=(String)session.getAttribute("dest");
		String g=(String)session.getAttribute("date");
		String clas=(String)session.getAttribute("cls");
		out.print("<html><head><style>body{background-color:#1ced81;}</style></head><body>");
	try{
		int pnr=0;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
			PreparedStatement rs=con.prepareStatement("Select pnr_seq.nextval from dual");
			ResultSet j=rs.executeQuery();
			if(j.next())
			{
				pnr=j.getInt(1);
			}
			PreparedStatement ps=con.prepareStatement("insert into ticket values(?,?,ticket_seq.nextval,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,a);
			ps.setInt(2,b);
			ps.setString(3,c);
			ps.setInt(4,d);
			ps.setString(5,g);
			ps.setString(6,e);
			ps.setString(7,f);
			ps.setInt(8,pnr);
			ps.setString(9,uid);
			ps.setString(10,clas);
			ps.setInt(11,(int)session.getAttribute("bookid"));
			int i=ps.executeUpdate();
			System.out.println("i="+i);
			if(i>0)
			{
			out.println("<h1>"+nam+" You Successfully Booked!!!</h1>");	
			System.out.println("No of passenger= "+d);
			if(pnum>0)
			{pnum--;
			session.setAttribute("pno", pnum);
			out.print("<h2>for pnr no. :"+pnr+"</h2>");
			out.println("<a href='details.html'>Next</a>");
			//req.getRequestDispatcher("details.html").forward(req, res);
			}
			else
			{
				out.print("<h1>"+nam+"Your Booking Was successful</h1>");	
				out.print("</body></html>");
				out.print("<a href='menu.html'>Go Back To Menu</a>");
				//req.getRequestDispatcher("menu.html").forward(req,res);
			}
		}
}catch (Exception e2){
        System.out.println(e2);
		}
		finally{
			try{
				con.close();
			}
			catch (SQLException e1){
				e1.printStackTrace();
			}
		}
	out.close();
	}
}