package Rail;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/ShowTrains")
public class ShowTrains extends HttpServlet{
	
	private static final long serialVersionUID=1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{HttpSession session = req.getSession();
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		Connection con=null;
		String nam=(String)session.getAttribute("Username");
		if(nam==null)
		{
			System.out.print("You need to loin first showtrains");
			req.getRequestDispatcher("userslogin.html").forward(req,res);
		}
		//String uid=(String)session.getAttribute("Userid");
		else{
			System.out.println("Hello"+nam);
		String a=req.getParameter("src");
		String b=req.getParameter("dest");
		String c=req.getParameter("dat");
		String e=req.getParameter("cls");
		int d=Integer.parseInt(req.getParameter("num1"));
		session.setAttribute("pnum",d);
		System.out.println("d= "+d);
		out.print("<html><head><style>body{background-color:#1ced81;}th,td{border: 1px solid black;}</style></head><body>");
		//out.print("form input[type='password']{-webkit-border-radius: 3px;-moz-border-radius: 3px;-ms-border-radius: 3px;-o-border-radius: 3px;border-radius: 3px;-webkit-box-shadow: 0 1px 0 #fff, 0 -2px 5px rgba(0,0,0,0.08) inset;-moz-box-shadow: 0 1px 0 #fff, 0 -2px 5px rgba(0,0,0,0.08) inset;-ms-box-shadow: 0 1px 0 #fff, 0 -2px 5px rgba(0,0,0,0.08) inset;-o-box-shadow: 0 1px 0 #fff, 0 -2px 5px rgba(0,0,0,0.08) inset;box-shadow: 0 1px 0 #fff, 0 -2px 5px rgba(0,0,0,0.08) inset;-webkit-transition: all 0.5s ease;-moz-transition: all 0.5s ease;-ms-transition: all 0.5s ease;-o-transition: all 0.5s ease;transition: all 0.5s ease;background: #eae7e7 url(https://cssdeck.com/uploads/media/items/8/8bcLQqF.png) no-repeat;border: 1px solid #c8c8c8;color: #777;font: 13px Helvetica, Arial, sans-serif;margin: 0 0 10px;padding: 15px 10px 15px 40px;width: 80%;} </style></head><body>");
		out.println(nam+"<h1>You Want to book!!<h1><br><Fill The  details :><br>"+a+"  "+b+"  "+c+"  "+d+"  "+e);
		//out.print
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
			PreparedStatement ps1=con.prepareStatement("Select t_no,t_name,c_class from train T inner join trainclass C on T.t_no=C.c_no where T.t_start=? and T.t_dest=? and T.t_depdate=? and C.c_class=? and C.c_seats>=?");
			ps1.setString(1,a);
			ps1.setString(2,b);
			ps1.setString(3,c);
			ps1.setString(4,e);
			ps1.setInt(5,d);
			ResultSet j=null;
			j=ps1.executeQuery();
			if(j!=null){
				
			    out.print("<table style='border:1px solid black' align='center'>");
			    out.print("<form action='Details' method='get'>");
				out.print("<tr><th>Train_no</th>"+"<th>Train_name</th>"+"<th>Train_class</th>"+"<th>Book?</th></tr>");
				
			while(j.next()){
				 System.out.println("HI");
			       int tno=j.getInt("t_no");
			       String tname=j.getString("t_name");
			       String tclass=j.getString("c_class");
			       System.out.println("tno"+tno+"tnae"+tname+"tclass"+e);
			       out.print("<tr><td>"+tno+"</td><td>"+tname+"</td><td>"+tclass+"</td>");
			      out.println("<td><input type=\"radio\" name=\"radio1\" value="+tno+"></td>");
					out.println("</tr>");
		           }
			out.print("<input type='hidden'name='src' value="+a+" /><br><br>");
			out.print("<input type='hidden' name='dest' value="+b+" /><br><br>");	
			out.print("<input type='hidden'  name='dat' value="+c+" /><br><br>");
			out.print("<input type='hidden' name='cls' value="+e+" /><br><br>");
			out.print("</table>");
			
			out.print("<input type='submit' value='submit'>");
			out.print("</form>");
			
			}
			else
			{
				out.print("Sorry "+nam+" Required number of seats are not there");
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
		//res.sendRedirect("/Practice/UserLogin.html");
		out.println("</body></html>");
		}
		out.close();
	}
}
