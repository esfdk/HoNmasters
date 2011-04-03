package bosk.ovelser.ovelse9.ovelse93;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
	public static void main(String[] args){
		executeQuery("select * from Person", new QueryCallBack() {
			public void processRecord(ResultSet rs) throws SQLException {
			System.out.println(rs.getObject(1) + ", " + rs.getObject(2) + ", " + rs.getObject(3));
			}
			});
	}

	public static void executeQuery(String sql, QueryCallBack callBack){
		Connection con = null;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			String url = "jdbc:derby:PersonDB;create=true";
			con = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				callBack.processRecord(rs);
			}
			stmt.close();
			con.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
