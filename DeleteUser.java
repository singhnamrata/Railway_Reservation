package Rail;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		HttpSession asession=request.getSession();
		PrintWriter out=response.getWriter();
		String name=(String)asession.getAttribute("Adminname");
		System.out.println("ADMIN NAME:"+name);
		if(name==null|| name.isEmpty())
		{
			request.getRequestDispatcher("adminlogin.html").forward(request,response);
		}
		response.setContentType("text/html");
		
		Connection con=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
			PreparedStatement ps=con.prepareStatement("select * from userlog");
			ResultSet rs=ps.executeQuery();
			out.print("<html><body><form method='get' action='ConfirmDeleteUser'>");
			out.print("<table><tr><th>UserName</th><th>User ID</th><th>User Email</th><th>User Phone</th></tr>");
			while(rs.next())
			{
				out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td></tr>");
				out.print("<input type='checkbox' name='check' value="+rs.getString(2)+"/>");
			}
			out.print("<input type='submit' value='Delete User'></input>");
			out.println("</form></body></html>");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}

}
