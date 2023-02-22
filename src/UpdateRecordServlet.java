import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/update-record")
public class UpdateRecordServlet extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String v1=req.getParameter("cid");
		String v2=req.getParameter("firstname");
		String v3=req.getParameter("lastname");
		String v4=req.getParameter("phone");
		String v5=req.getParameter("address");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdatabase","root","Sam@31122000");
			String query="update customerinfo set firstname=?,lastname=?,phone_no=?,address=? where cid=?";
			PreparedStatement ps=cn.prepareStatement(query);
			ps.setString(1,v2);
			ps.setString(2,v3);
			ps.setString(3,v4);
			ps.setString(4,v5);
			ps.setString(5,v1);
			ps.executeUpdate();
			out.println("<h1 style='color:green;margin-top:250px;background-color:lightpink' align='center'>Customer record updated successfully....</h1>");
			out.println("<a style='font-size:19px' href='/customer-management'>Back</a> to home");
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
}
