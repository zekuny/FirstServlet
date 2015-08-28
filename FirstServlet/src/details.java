// Import required java libraries
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Extend HttpServlet class
@WebServlet("/details")
public class details extends HttpServlet {
	private String table;
	private static Connection conn = null;
	public void init() throws ServletException{
		// Do required initialization
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
		String custId = request.getParameter("customerID");
		response.setContentType("text/html");
		ResultSet result = null;
		try {
			result = getDetailByID(custId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table = "";
		table += "<h1>Customer ID: " + custId + "</h1>";
		// here we go!
		table += "<br><br>";
		
		try {
			if(result.next()){
				table += "<form action=\"HelloWorld\" id=\"\" class=\"form-horizontal\">" + 
			    "<div class=\"form-group\">" +
			        "<label class=\"col-xs-3 control-label\">cust_first_name</label>" +
			        "<div class=\"col-xs-5\">" +
			            "<label class=\"col-xs-3 control-label\">" + result.getString("cust_first_name")+ "</label>" + 
			        "</div>" + 
			    "</div>" + 

			    "<div class=\"form-group\">" + 
			        "<label class=\"col-xs-3 control-label\">cust_last_name</label>" + 
			        "<div class=\"col-xs-5\">" + 
			            "<label class=\"col-xs-3 control-label\">" + result.getString("cust_last_name")+ "</label>" + 
			        "</div>" + 
			    "</div>" + 

			    "<div class=\"form-group\">" + 
			        "<label class=\"col-xs-3 control-label\">cust_street_address1</label>" + 
			        "<div class=\"col-xs-5\">" + 
			            "<label class=\"col-xs-3 control-label\">" + result.getString("cust_street_address1")+ "</label>" + 
			        "</div>" + 
			    "</div>" + 

			    "<div class=\"form-group\">" + 
			        "<label class=\"col-xs-3 control-label\">cust_street_address2</label>" + 
			        "<div class=\"col-xs-5\">" + 
			            "<label class=\"col-xs-3 control-label\">" + result.getString("cust_street_address2")+ "</label>" + 
			        "</div>" + 
			    "</div>" + 

			    "<div class=\"form-group\">" + 
			        "<label class=\"col-xs-3 control-label\">cust_city</label>" + 
			        "<div class=\"col-xs-5\">" + 
			            "<label class=\"col-xs-3 control-label\">" + result.getString("cust_city")+ "</label>" + 
			        "</div>" + 
			    "</div>" + 
			        
			    "<div class=\"form-group\">" + 
		        "<label class=\"col-xs-3 control-label\">cust_state</label>" + 
		        "<div class=\"col-xs-5\">" + 
		            "<label class=\"col-xs-3 control-label\">" + result.getString("cust_state")+ "</label>" + 
		        "</div>" + 
		        "</div>" + 
		        
			    "<div class=\"form-group\">" + 
		        "<label class=\"col-xs-3 control-label\">cust_postal_code</label>" + 
		        "<div class=\"col-xs-5\">" + 
		            "<label class=\"col-xs-3 control-label\">" + result.getString("cust_postal_code")+ "</label>" + 
		        "</div>" + 
		        "</div>" + 
		        
			    "<div class=\"form-group\">" + 
		        "<label class=\"col-xs-3 control-label\">phone_number1</label>" + 
		        "<div class=\"col-xs-5\">" + 
		            "<label class=\"col-xs-3 control-label\">" + result.getString("phone_number1")+ "</label>" + 
		        "</div>" + 
		        "</div>" + 
		        
			    "<div class=\"form-group\">" + 
		        "<label class=\"col-xs-3 control-label\">phone_number2</label>" + 
		        "<div class=\"col-xs-5\">" + 
		            "<label class=\"col-xs-3 control-label\">" + result.getString("phone_number2")+ "</label>" + 
		        "</div>" + 
		        "</div>" + 
		        
			    "<div class=\"form-group\">" + 
		        "<label class=\"col-xs-3 control-label\">credit_limit</label>" + 
		        "<div class=\"col-xs-5\">" + 
		            "<label class=\"col-xs-3 control-label\">" + result.getString("credit_limit")+ "</label>" + 
		        "</div>" + 
		        "</div>" + 
		        
			    "<div class=\"form-group\">" + 
		        "<label class=\"col-xs-3 control-label\">cust_email</label>" + 
		        "<div class=\"col-xs-5\">" + 
		            "<label class=\"col-xs-3 control-label\">" + result.getString("cust_email")+ "</label>" + 
		        "</div>" + 
		        "</div>" + 

			    "<div class=\"form-group\">" + 
			        "<div class=\"col-xs-9 col-xs-offset-3\">" + 
			            "<button type=\"submit\" class=\"btn btn-primary\" name=\"\" value=\"\">Return</button>" + 
			        "</div>" + 
			    "</div></form>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("table", table);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	} 
	
	public void destroy(){ 
		// do nothing. 
	} 
	
	public static ResultSet getDetailByID(String custId) throws SQLException{
		int id = Integer.valueOf(custId);
		return DBOperation.getDetailByID(id, conn);
	}
}