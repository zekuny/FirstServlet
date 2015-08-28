// Import required java libraries
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Extend HttpServlet class
@WebServlet("/HelloWorld")
public class HelloWorld extends HttpServlet {
	private String message;
	private String name;
	private String table;
	private static Connection conn = null;
	public void init() throws ServletException{
		// Do required initialization
		message = "Hello";
		name = "";
		table = "";
		try {
			conn = DBConnection.connectDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// Set response content type
		response.setContentType("text/html");
		ResultSet result = null;
		try {
			result = getCustomers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table = "";
		table += "<div class=\"container\">" + "<h2>All Customers</h2>" + "<table class=\"table table-condensed\">"
		    + "<thead>" + "<tr>" + "<th>ID</th>" + "<th>FirstName</th>" + "<th>LastName</th>" + "<th>City</th>" + "<th>State</th>" + "</tr>"
		    +   "</thead>" + "<tbody>";	    
    	try {
			while(result.next()){
				table += "<tr>";
				//String custId = request.getParameter("customerID");
				table += "<td><a href = \"details?customerID=" + result.getString("customer_id") + "\">" + result.getString("customer_id") + "</a></td>";
				table += "<td>" + result.getString("cust_first_name") + "</td>";
				table += "<td>" + result.getString("cust_last_name") + "</td>";
				table += "<td>" + result.getString("cust_city") + "</td>";
				table += "<td>" + result.getString("cust_state") + "</td>";
				table += "</tr>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		table += "</tbody>" + "</table>" + "</div>";
		
		// Actual logic goes here.
		//PrintWriter out = response.getWriter();
		//out.println("<h1>" + message + "</h1>"); 
		//doPost(request, response);
		request.setAttribute("table", table);
		request.setAttribute("message", message); // the first is the jsp attribute, the second is the servlet attribute
		try {
			name = getNameByID();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("username", name);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	} 
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResultSet result = null;
		try {
			result = getCustomers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<div class=\"container\">");
		  out.println("<h2>All Customers</h2>");          
		  out.println("<table class=\"table table-condensed\">");
		    out.println("<thead>");
		    out.println("<tr>");
		    	out.println("<th>FirstName</th>");
		    	out.println("<th>LastName</th>");
		    	out.println("<th>City</th>");
		    	out.println("<th>State</th>");
		    out.println("</tr>");
		    out.println("</thead>");
		    out.println("<tbody>");
		    
		    	try {
					while(result.next()){
						out.println("<tr>");
						out.println("<td>" + result.getString("cust_first_name") + "</td>");
						out.println("<td>" + result.getString("cust_last_name") + "</td>");
						out.println("<td>" + result.getString("cust_city") + "</td>");
						out.println("<td>" + result.getString("cust_state") + "</td>");
						out.println("</tr>");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     
		    out.println("</tbody>");
		   out.println("</table>");
		out.println("</div>");
		
	}
	

	public void destroy(){ 
		// do nothing. 
	} 
	
	public static String getNameByID() throws SQLException{
		return DBOperation.getNameByID(2, conn);
	}
	
	public static ResultSet getCustomers() throws SQLException{
		return DBOperation.getCustomers(conn);
	}
}