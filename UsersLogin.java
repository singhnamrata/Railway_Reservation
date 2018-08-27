package Rail;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

//@WebServlet("/servlet/LoginUser")
public class UsersLogin extends HttpServlet{
	
	private static final long serialVersionUID=1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{
		HttpSession session=req.getSession();
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		Connection con=null;
		String n=req.getParameter("uid");
		String p=req.getParameter("psw");
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
			
			PreparedStatement ps=con.prepareStatement("select * from userlog where usid=? and upsw=?");
			ps.setString(1,n);
			ps.setString(2,p);
			
			ResultSet i=null;
					i=ps.executeQuery();
			if(i!=null)
				{
				if(i.next()){
				session.setAttribute("Userid",i.getString(2));
				session.setAttribute("Username",i.getString(1));
				session.setAttribute("Password",i.getString(1));
				out.print("Logged in Successfully");
                 req.getRequestDispatcher("menu.html").forward(req,res);
				}}
				
			
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
