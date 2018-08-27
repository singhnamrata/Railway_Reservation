package Rail;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

//@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet{
	
	private static final long serialVersionUID=1L;
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		Connection con=null;
		String m=req.getParameter("uname");
		String n=req.getParameter("uid");
		String p=req.getParameter("upsw");
		String d=req.getParameter("uemail");
		int g=Integer.parseInt(req.getParameter("uphone"));
		String e=req.getParameter("Indicator");
		//System.out.println("user name: "+n);
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
			
			PreparedStatement ps=con.prepareStatement("insert into userlog values(?,?,?,?,?,?)");
			ps.setString(1,m);
			ps.setString(2,n);
			ps.setString(3,p);
			ps.setString(4,d);
			ps.setInt(5,g);
			ps.setString(6,e);
			int i=ps.executeUpdate();
			if(i>0)
			{
				out.print("<h1>You Registered Successfully!!</h1>");
				out.println("<a href='menu.html'>Go To Menu</a>");
				//res.sendRedirect("menu.html");
			
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


