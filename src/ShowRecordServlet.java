import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/show-record")
public class ShowRecordServlet extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdatabase","root","Sam@31122000");
			String query="select * from customerinfo";
			Statement st=cn.createStatement();
			ResultSet rst=st.executeQuery(query);
			out.println("<table border='1' style='border-collapse:collapse;margin-top:250px;height:200px;width:600px' align='center'>");
			out.println("<tr>");
			out.println("<th>Customer Id</th>");
			out.println("<th>First Name</th>");
			out.println("<th>Last Name</th>");
			out.println("<th>Phone Number</th>");
			out.println("<th>Address</th>");
			out.println("</tr>");
			while(rst.next()) {
				out.println("<tr>");
				out.println("<td align='center'>"+rst.getString(1)+"</td>");
				out.println("<td align='center'>"+rst.getString(2)+"</td>");
				out.println("<td align='center'>"+rst.getString(3)+"</td>");
				out.println("<td align='center'>"+rst.getString(4)+"</td>");
				out.println("<td align='center'>"+rst.getString(5)+"</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<a style='font-size:19px' href='/customer-management'>Back</a> to home");
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
}
