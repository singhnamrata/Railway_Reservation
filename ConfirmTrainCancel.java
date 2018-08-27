package Rail;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/ConfirmTrainCancel")
public class ConfirmTrainCancel extends HttpServlet{
	
	private static final long serialVersionUID=1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{HttpSession asession = req.getSession();
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		Connection con=null;
		//String nam=(String)session.getAttribute("Username");
		//String uid=(String)session.getAttribute("Userid");
		//String psw=(String)session.getAttribute("Password");
		//int d=(int)session.getAttribute("pnum");
		String[] tno = req.getParameterValues("checkbox1");
		int n_del=tno.length,r=0,p=0;
		out.print("<html><head><style>body{background-color:#1ced81;}</style></head><body>");
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
			for(int i=0;i<n_del;i++)
			{
				int tn=Integer.parseInt(tno[i]);
				PreparedStatement ps=con.prepareStatement("delete from trainclass where c_no=?");
			    ps.setInt(1,tn);
			    PreparedStatement rs=con.prepareStatement("delete from train where t_no=?");
			    rs.setInt(1,tn);
			    p=ps.executeUpdate();
			   r=rs.executeUpdate();
				
			}
			if(r>0&&p>0)
			{
			System.out.println("\nOhk You are done cancelling");	
			}
			//System.out.println("Class="+clas);
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
		//res.sendRedirect("/Practice/UserLogin.html");
		out.close();
	}
}