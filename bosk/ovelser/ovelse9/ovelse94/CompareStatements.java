package bosk.ovelser.ovelse9.ovelse94;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Our class for comparing Statement and PreparedStatement in terms of performance.
 * 
 * @author Jakob Melnyk & Frederik Roden Lysgaard
 * @version 1
 */
public class CompareStatements {

	public static void main(String[] args) {
		//Initializes the connection and the different statements to null,
		//so that we don't have to include every line of code in a try-catch.
		Connection con = null;
		Statement stmt = null;
		PreparedStatement psTestIns = null;
		PreparedStatement psTestSel = null;

		try{
			//Opens the connection
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			String url = "jdbc:derby:PersonDB;create=true";
			con = DriverManager.getConnection(url);
			
			//Tests how long it takes to create a Statement for use in the test.
			double time = System.currentTimeMillis();
			stmt = con.createStatement();
			System.out.println("Took " + (System.currentTimeMillis() - time) + " to create Statement.");
			
			//Tests how long it takes to prepare the PreparedStatements used in the test..
			time = System.currentTimeMillis();
			psTestIns = con.prepareStatement("INSERT INTO PERSON VALUES(?,'Random','Person')");
			psTestSel = con.prepareStatement("SELECT * FROM PERSON WHERE person_pk = ?");
			System.out.println("Took " + (System.currentTimeMillis() - time) + " to prepare Statement.");
			
		}catch(Exception e){
			System.exit(0);
		}

		//Resets the database before use.
		dropTable(stmt);
		createTable(stmt);		
		
		//Test of Statements
		System.out.println("Statement test of INSERT took: " + testStatementIns(stmt) + " milliseconds to complete.");
		System.out.println("Statement test of SELECT took : " + testStatementSel(stmt) + " milliseconds to complete.");
		
		//Resets the database before use.
		dropTable(stmt);
		createTable(stmt);

		//Test of PreparedStatements
		System.out.println("PreparedStatement test of INSERT took: " + testPreparedStatementIns(psTestIns) + " milliseconds to complete.");
		System.out.println("PreparedStatement test of SELECT took : " + testPreparedStatementSel(psTestSel) + " milliseconds to complete.");
		
		//Tries to close the connection.
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Could not close the connection.");
			e.printStackTrace();
		}
	}

	/**
	 * Tests how long it takes to perform a number of INSERT operations using a Statement.
	 * 
	 * @param stmt The Statement to execute the Update on.
	 * @return The time it took to test the Statement in milliseconds.
	 */
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

	/**
	 * Tests how long it takes to perform a number of SELECT operations using a Statement.
	 * 
	 * @param stmt The Statement to execute the Query on.
	 * @return The time it took to test the Statement in milliseconds.
	 */
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
	
	/**
	 * Tests how long it takes to perform a number of INSERT operations using a PreparedStatement.
	 * 
	 * @param stmt The statement to be tested.
	 * @return The time it took to test the PreparedStatement in milliseconds.
	 */
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

	/**
	 * Tests how long it takes to perform a number of SELECT operations using a PreparedStatement.
	 * 
	 * @param stmt The statement to be tested.
	 * @return The time it took to test the PreparedStatement in milliseconds.
	 */
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
	
	//Methods borrowed from previous assignment.
	
	/**
	 * Used to drop the Person table in the database.
	 * 
	 * @param stmt The statement to execute the dropTable update on.
	 */
	private static void dropTable(Statement stmt) {
		try {
			String sql = "DROP TABLE PERSON";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Failed to drop table PERSON");
		}
	}

	/**
	 * Used to create the Person table in the database.
	 * 
	 * @param stmt The statement to execute the createTable update on.
	 */
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
