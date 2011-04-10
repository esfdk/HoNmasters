package bosk.ovelse09.ovelse93;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Test class for testing our solution to the problem of closing ResulSet objects.
 * 
 * @author Jakob Melnyk & Frederik Roden Lysgaard
 * @version 1
 */
public class Test {
	
	/**
	 * Main method for the test.
	 */
	public static void main(String[] args){
		executeQuery("select * from Person", new QueryCallBack() {
			//Specified by the assignment.
			public void processRecord(ResultSet rs) throws SQLException {
			System.out.println(rs.getObject(1) + ", " + rs.getObject(2) + ", " + rs.getObject(3));
			}
			});
	}

	/**
	 * Our version of the executeQuery.
	 * 
	 * @param sql The sql query to be executed.
	 * @param callBack The implementation of QueryCallBack to be used.
	 */
	public static void executeQuery(String sql, QueryCallBack callBack){
		Connection con = null;
		try {
			//Attempts to open the connection.
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			String url = "jdbc:derby:PersonDB;create=true";
			con = DriverManager.getConnection(url);

			//Creates the statement and executes.
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				callBack.processRecord(rs);
			}
			//Closes the different objects.
			stmt.close();
			con.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
