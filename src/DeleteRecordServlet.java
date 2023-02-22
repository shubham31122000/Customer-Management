import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/delete-record")
public class DeleteRecordServlet extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String v1=req.getParameter("cid");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdatabase","root","Sam@31122000");
			String query="delete from customerinfo where cid=?";
			PreparedStatement ps=cn.prepareStatement(query);
			ps.setString(1,v1);
			int x=ps.executeUpdate();
			RequestDispatcher rd=req.getRequestDispatcher("delete.html");
			rd.include(req,res);
			out.println("<h2 align='center'>Customer record with cid "+v1+" deleted successfully....</h2>");
			out.println("<a style='font-size:19px' href='/customer-management'>Back</a> to home");
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
}
