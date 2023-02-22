import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/edit-record")
public class EditRecordServlet extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String v1=req.getParameter("cid");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdatabase","root","Sam@31122000");
			String query="select * from customerinfo where cid=?";
			PreparedStatement ps=cn.prepareStatement(query);
			ps.setString(1,v1);
			ResultSet rst=ps.executeQuery();
			if(rst.next()) {
				out.println("<form action='update-record' method='post'>");
				out.println("<input type='hidden' name='cid' value='"+rst.getString(1)+"'>");
				out.println("<table border='1' style='border-collapse:collapse;margin-top:250px;height:200px;width:600px' align='center'>");
				out.println("<tr>");
				out.println("<th>Customer Id</th>");
				out.println("<td align='center'>"+rst.getString(1)+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<th>First Name</th>");
				out.println("<td><input type='text' name='firstname' value='"+rst.getString(2)+"'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<th>Last Name</th>");
				out.println("<td><input type='text' name='lastname' value='"+rst.getString(3)+"'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<th>Phone Number</th>");
				out.println("<td><input type='text' name='phone' value='"+rst.getString(4)+"'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<th>Address</th>");
				out.println("<td><input type='text' name='address' value='"+rst.getString(5)+"'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td colspan='2' align='center'><button style='color:red'>Update Record</button></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<form>");
				out.println("<a style='font-size:19px' href='/customer-management'>Back</a> to home");
			}
			else {
				RequestDispatcher rd=req.getRequestDispatcher("edit.html");
				rd.include(req,res);
				out.println("<h2 align='center'>Customer id "+v1+" you entered doesn't exists...</h2>");
				out.println("<a style='font-size:19px' href='/customer-management'>Back</a> to home");
			}
//			out.println("<a href='index.html'>Go to main page</a>");
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
}
