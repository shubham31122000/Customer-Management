import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/save-record")
public class InsertRecordServlet extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String v1=req.getParameter("cid");
		String v2=req.getParameter("firstname");
		String v3=req.getParameter("lastname");
		String v4=req.getParameter("phone_no");
		String v5=req.getParameter("address");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdatabase","root","Sam@31122000");
			String query="select cid from customerinfo where cid=?";
			PreparedStatement ps1=cn.prepareStatement(query);
			ps1.setString(1,v1);
			ResultSet rst=ps1.executeQuery();
			if(rst.next()) {
				out.println("<h1 style='color:red;background-color:lightyellow;margin-top:250px' align='center'>Customer with id "+v1+" already exists....</h1>");
				out.println("<a href='index.html'>Go to main page</a>");
				return;
			}
			query="insert into customerinfo values(?,?,?,?,?)";
			PreparedStatement ps=cn.prepareStatement(query);
			ps.setString(1,v1);
			ps.setString(2,v2);
			ps.setString(3,v3);
			ps.setString(4,v4);
			ps.setString(5,v5);
			ps.executeUpdate();
			out.println("<h1 style='color:green;margin-top:250px;background-color:lightpink' align='center'>Customer record added successfully....</h1>");
			out.println("<a style='font-size:19px' href='/customer-management'>Back</a> to home");
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
}
