package Rail;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/ConfirmCancel")
public class ConfirmCancel extends HttpServlet{
	
	private static final long serialVersionUID=1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{HttpSession session = req.getSession();
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		Connection con=null;
		//String nam=(String)session.getAttribute("Username");
		//String uid=(String)session.getAttribute("Userid");
		//String psw=(String)session.getAttribute("Password");
		//int d=(int)session.getAttribute("pnum");
		String[] tno = req.getParameterValues("checkbox1");
		out.print("<body style='background-color:#1ced81;'>");
		if(tno==null)
		{
			out.print("Sorry You didn't select any train!!");
			out.print("<a href='UserLogout'>Go Back</a>");
		}else{
		int n_del=tno.length,r=0,p=0;
		out.print("<html><head><style>body{background-color:#1ced81;}</style></head><body>");
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
			for(int i=0;i<n_del;i++)
			{
				int pnr=Integer.parseInt(tno[i]);
				PreparedStatement ps=con.prepareStatement("delete from ticket where pnr=?");
			    ps.setInt(1,pnr);
			    PreparedStatement rs=con.prepareStatement("update booking B set no_seats=no_seats-1 where B_ID=(select b_id from ticket where pnr=?)");
			    rs.setInt(1,pnr);
			    //PreparedStatement qs=con.prepareStatement("update trainclass set c_seats=c_seats+1 ");
			   r=rs.executeUpdate();
				p=ps.executeUpdate();
			}
			if(r>0&&p>0)
			{
			out.println("<script type='text/javascript'>alert('Seat Cancelled Successfully!!')</script>");	
			req.getRequestDispatcher("menu.html").forward(req,res);
			/*System.out.println("\nOhk You are done cancelling");	
			out.println("\n<h1>Ohk You are done cancelling seat </h1><br>");*/
			}
			//System.out.println("Class="+clas);
			//req.getRequestDispatcher("CheckAccount").forward(req,res);
			/*out.print("<a href='menu.html'>Go Back to Menu</a>");
			out.print("<br><br><a href='CheckAccount'>Check Your Account</a>");*/
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
		}}
		//res.sendRedirect("/Practice/UserLogin.html");
		out.close();
	}
}
