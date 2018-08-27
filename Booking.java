package Rail;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

//@WebServlet("/Booking")
public class Booking extends HttpServlet{
	
	private static final long serialVersionUID=1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{HttpSession session = req.getSession();
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		Connection con=null;
		String nam=(String)session.getAttribute("Username");
		if(nam==null || nam.isEmpty())
		{System.out.println("You need to logn first");
			req.getRequestDispatcher("userslogin.html").forward(req,res);
		}
		else{
		System.out.print("in booking"+nam);
		out.println("<h1>"+nam+"  You Want to book!!<h1><br><Fill The  details :><br>");
	try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
			System.out.print(nam+"hii");
			req.getRequestDispatcher("ShowTrains").forward(req,res);
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
		}
		//res.sendRedirect("/Practice/UserLogin.html");
		out.close();
	}

}