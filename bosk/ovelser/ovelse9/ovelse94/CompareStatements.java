package bosk.ovelser.ovelse9.ovelse94;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CompareStatements {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		PreparedStatement psTestIns = null;
		PreparedStatement psTestSel = null;

		try{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			String url = "jdbc:derby:PersonDB;create=true";
			con = DriverManager.getConnection(url);
			double time = System.currentTimeMillis();
			stmt = con.createStatement();
			System.out.println("Took " + (System.currentTimeMillis() - time) + " to create Statement.");
			time = System.currentTimeMillis();
			psTestIns = con.prepareStatement("INSERT INTO PERSON VALUES(?,'Random','Person')");
			psTestSel = con.prepareStatement("SELECT * FROM PERSON WHERE person_pk = ?");
			System.out.println("Took " + (System.currentTimeMillis() - time) + " to prepare Statement.");
		}catch(Exception e){
			System.exit(0);
		}

		dropTable(stmt);
		createTable(stmt);
		

		System.out.println("Statement test of INSERT took: " + testStatementIns(stmt) + " milliseconds to complete.");

		dropTable(stmt);
		createTable(stmt);

		System.out.println("PreparedStatement test of INSERT took: " + testPreparedStatementIns(psTestIns) + " milliseconds to complete.");
		
		System.out.println("Statement test of SELECT took : " + testStatementSel(stmt) + " milliseconds to complete.");
		System.out.println("PreparedStatement test of SELECT took : " + testPreparedStatementSel(psTestSel) + " milliseconds to complete.");
	}

	public static double testStatementIns(Statement stmt){
		double time = System.currentTimeMillis();
		for(int i = 1; i <= 1000; i++){
			try {
				String sql = "INSERT INTO PERSON VALUES(" + i + ",'Random','Person')";
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println("Failed to insert row into table PERSON");
				System.exit(0);
			}
		}
		return System.currentTimeMillis() - time;
	}

	public static double testStatementSel(Statement stmt){
		double time = System.currentTimeMillis();
		for(int i = 1; i <= 1000; i++){
			try {
				String sql = "SELECT * FROM PERSON WHERE person_pk = " + i;
				stmt.executeQuery(sql);
			} catch (SQLException e) {
				System.out.println("Failed to select person from table PERSON");
				System.exit(0);
			}
		}
		return System.currentTimeMillis() - time;
	}
	
	public static double testPreparedStatementIns(PreparedStatement stmt){
		double time = System.currentTimeMillis();
		for(int i = 1; i <= 1000; i++){
			try {
				stmt.setInt(1, i);
				stmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Failed to insert row into table PERSON");
				System.exit(0);
			}
		}
		return System.currentTimeMillis() - time;
	}

	public static double testPreparedStatementSel(PreparedStatement stmt){
		double time = System.currentTimeMillis();
		for(int i = 1; i <= 1000; i++){
			try {
				stmt.setInt(1, i);
				stmt.executeQuery();
			} catch (SQLException e) {
				System.out.println("Failed to select person from table PERSON");
				System.exit(0);
			}
		}
		return System.currentTimeMillis() - time;
	}
	
	private static void dropTable(Statement stmt) {
		try {
			String sql = "DROP TABLE PERSON";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Failed to drop table PERSON");
		}
	}

	private static void createTable(Statement stmt) {
		try {
			String sql = "";

			sql += "CREATE TABLE PERSON";
			sql += "(";
			sql += "PERSON_PK INTEGER NOT NULL PRIMARY KEY,";
			sql += "FIRST_NAME VARCHAR(50) NOT NULL,";
			sql += "LAST_NAME VARCHAR(50) NOT NULL";
			sql += ")";

			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Failed to create table PERSON");
			e.printStackTrace();
		}
	}

}
