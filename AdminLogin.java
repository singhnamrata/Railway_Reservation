package Rail;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

//@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet{
	
	private static final long serialVersionUID=1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{HttpSession asession = req.getSession();
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	Connection con=null;
	
	//req.getRequestDispatcher("userlogin.html").include(req,res);
	String n=req.getParameter("aid");
	String p=req.getParameter("psw");
		System.out.print("aid   ="+n+"pass  ="+p);
	if(n==null || p=="")
	{
		req.getRequestDispatcher("adminlogin.html").forward(req,res);
	}
	else{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
			System.out.println("uid="+n+"  psw="+p);
			PreparedStatement ps=con.prepareStatement("select aname from adminlog where aid=? and apsw=?");
			ps.setString(1,n);
			ps.setString(2,p);
			
			ResultSet s=ps.executeQuery();
					//i=ps.executeQuery();
			boolean val=s.next();
			String sname=s.getString("aname");
			System.out.println("\nbool="+val);
			if(val)
			{asession.setAttribute("Adminname",sname);
			asession.setAttribute("Adminid", n);
			asession.setAttribute("Password", p);
			System.out.println("\nSucessful"+sname+"  "+n+"  "+p);
			//out.println("<br><h1>"+s.getString("uname")+"You successfully logged in!!"+"<h1>");
			//req.getRequestDispatcher("adminmenu.html").forward(req,res);	
			}
			//System.out.println("\nNot Sucessful");
			req.getRequestDispatcher("adminmenu.html").forward(req,res);
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
		out.close();
	}

}