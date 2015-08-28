import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOperation {
	public static String getNameByID(int id, Connection conn) throws SQLException{
		String sql = "select CUST_FIRST_NAME, CUST_LAST_NAME from Demo_Customers where Customer_ID = " + id;
		PreparedStatement preStatement = conn.prepareStatement(sql);
		ResultSet result = preStatement.executeQuery();
		String name = "";
		if(result.next()){
			name = result.getString("CUST_FIRST_NAME") + " " + result.getString("CUST_LAST_NAME");
		}
		return name;
	}
	
	
	public static ResultSet getCustomers(Connection conn) throws SQLException{
		String sql = "select * from Demo_Customers";
		PreparedStatement preStatement = conn.prepareStatement(sql);
		ResultSet result = preStatement.executeQuery();
		return result;
	}
	
	public static ResultSet getDetailByID(Integer id, Connection conn) throws SQLException{
		String sql = "select * from Demo_Customers where customer_id = " + id;
		PreparedStatement preStatement = conn.prepareStatement(sql);
		ResultSet result = preStatement.executeQuery();
		return result;
	}
}
