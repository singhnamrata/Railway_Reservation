package Rail;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/Details")
public class Details extends HttpServlet{
	
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
		String a=req.getParameter("src");
		String b=req.getParameter("dest");
		String c=req.getParameter("dat");
		String clas=req.getParameter("cls");
		//int d=Integer.parseInt(req.getParameter("num"));
		int d=(int)session.getAttribute("pnum");
		int tno = Integer.parseInt(req.getParameter("radio1"));
		System.out.println("Train No="+tno+"\n Class="+clas);
		out.print("<html><head><style>body{background-color:#1ced81;}</style></head><body>");
		//out.print("form input[type='password']{-webkit-border-radius: 3px;-moz-border-radius: 3px;-ms-border-radius: 3px;-o-border-radius: 3px;border-radius: 3px;-webkit-box-shadow: 0 1px 0 #fff, 0 -2px 5px rgba(0,0,0,0.08) inset;-moz-box-shadow: 0 1px 0 #fff, 0 -2px 5px rgba(0,0,0,0.08) inset;-ms-box-shadow: 0 1px 0 #fff, 0 -2px 5px rgba(0,0,0,0.08) inset;-o-box-shadow: 0 1px 0 #fff, 0 -2px 5px rgba(0,0,0,0.08) inset;box-shadow: 0 1px 0 #fff, 0 -2px 5px rgba(0,0,0,0.08) inset;-webkit-transition: all 0.5s ease;-moz-transition: all 0.5s ease;-ms-transition: all 0.5s ease;-o-transition: all 0.5s ease;transition: all 0.5s ease;background: #eae7e7 url(https://cssdeck.com/uploads/media/items/8/8bcLQqF.png) no-repeat;border: 1px solid #c8c8c8;color: #777;font: 13px Helvetica, Arial, sans-serif;margin: 0 0 10px;padding: 15px 10px 15px 40px;width: 80%;} </style></head><body>");
		//out.println(nam+"<h1>You Want to book!!<h1><br><Fill The  details :><br>");
		//out.print
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","prakharsingh");
			PreparedStatement os=con.prepareStatement("Select book_seq.nextval from dual");
			int myId=0;
			synchronized( this ) {
				   ResultSet res1 = os.executeQuery();
				   if(res1.next())
				     myId = res1.getInt(1);
			}
			PreparedStatement ps=con.prepareStatement("insert into booking values(?,?,?,?,?,?,?)");
			ps.setString(1,uid);
			ps.setString(2,a);
			ps.setString(3,b);
			ps.setString(4,c);
			ps.setInt(5,d);
			ps.setInt(6,myId);
			ps.setInt(7,tno);
			session.setAttribute("bookid",myId);
			PreparedStatement rs=con.prepareStatement("update trainclass T set c_seats=c_seats-? where T.c_no=? and T.c_class=?");
			rs.setInt(1,d);
			rs.setInt(2,tno);
			rs.setString(3,clas);
			System.out.println("Class="+clas);
			int j=rs.executeUpdate();
			int i=ps.executeUpdate();
			System.out.println("i="+i+"j="+j);
			session.setAttribute("src",a);
			session.setAttribute("dest",b);
			session.setAttribute("date",c);
			session.setAttribute("cls",clas);
			if(i>0&&j>0)
			{
			out.println("<h1>"+nam+" You Successfully Booked!!!</h1><br><h2>Now Fill The Details of each Passenger</h2><br><br>");	
			System.out.println("No of passenger= "+d);
			session.setAttribute("pno", d);
			int num=(int)session.getAttribute("pno");
			if(num>0)
			{num--;
			session.setAttribute("pno", num);
			req.getRequestDispatcher("details.html").forward(req, res);
			}
			else
			{
				//res.sendRedirect("http://localhost:8080/Railway_Reservation/servlet/UserLogin");	
				res.sendRedirect("userslogin.html");
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
		//res.sendRedirect("/Practice/UserLogin.html");
		out.close();
	}
}