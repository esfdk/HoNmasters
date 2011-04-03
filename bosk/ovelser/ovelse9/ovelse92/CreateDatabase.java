package bosk.ovelser.ovelse9.ovelse92;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {

	public static void main(String[] args) throws Exception {

		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		// As we append ;create=true to the URL, a new database will be created
		// if it doesn't exist already.
		String url = "jdbc:derby:PersonDB;create=true";
		Connection con = DriverManager.getConnection(url);
		Statement stmt = con.createStatement();

		dropTable(stmt);
		createTable(stmt);
		insertData(stmt);
		selectAndPrintAll(stmt, "PERSON");
	}

	private void printTable(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();

		for (int i = 0; i < metaData.getColumnCount(); i++) {
			System.out.print("\"" + metaData.getColumnName(i) + "\"");
			System.out.print(i < metaData.getColumnCount() - 1 ? "," : "");
		}
	}

	private static void dropTable(Statement stmt) {
		try {
			String sql = "DROP TABLE PERSON";
			stmt.executeUpdate(sql);
			System.out.println("Dropped table PERSON");
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
			System.out.println("Created table PERSON");
		} catch (SQLException e) {
			System.out.println("Failed to create table PERSON");
			e.printStackTrace();
		}
	}

	private static void insertData(Statement stmt) {

		try {
			String sql = "";

			sql += "INSERT INTO PERSON VALUES";
			sql += "(";
			sql += "12,";
			sql += "'Kurt',";
			sql += "'Olufsen'";
			sql += ")";

			stmt.executeUpdate(sql);
			System.out.println("Inserted row into table PERSON");
		} catch (SQLException e) {
			System.out.println("Failed to insert row into table PERSON");
			e.printStackTrace();
		}

		try {
			String sql = "";

			sql += "INSERT INTO PERSON VALUES";
			sql += "(";
			sql += "45,";
			sql += "'Jens',";
			sql += "'Jensen'";
			sql += ")";

			stmt.executeUpdate(sql);
			System.out.println("Inserted row into table PERSON");
		} catch (SQLException e) {
			System.out.println("Failed to insert row into table PERSON");
		}

		try {
			String sql = "";

			sql += "INSERT INTO PERSON VALUES";
			sql += "(";
			sql += "67,";
			sql += "'Sigurdson'";
			sql += ")";

			sql += "'Sif',";
			stmt.executeUpdate(sql);
			System.out.println("Inserted row into table PERSON");
		} catch (SQLException e) {
			System.out.println("Failed to insert row into table PERSON");
		}
	}

	private static void selectAndPrintAll(Statement stmt, String table) {
		try {
			String sql;
			sql = "SELECT * FROM " + table;
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("****" + table + "****");

			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			System.out.print("|");
			for (int n = 1; n <= count; n++) {
				System.out.print(rsmd.getColumnName(n) + "|");
			}
			System.out.println();
			while (rs.next()) {
				System.out.print("|");
				for (int n = 1; n <= count; n++) {
					System.out.print(rs.getString(n) + "|");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to select table content from " + table);
		}
	}
}
