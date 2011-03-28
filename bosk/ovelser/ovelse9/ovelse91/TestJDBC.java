package bosk.ovelser.ovelse9.ovelse91;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC {
	static final String dbName = "MyTestDB";
	static final String url = "jdbc:derby:" + dbName + ";create=true"; //opret databasen hvis den ikke findes
	public static void main(String[] args) throws Exception {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		Connection con = DriverManager.getConnection(url);
		Statement stmt = con.createStatement();
		// vi sletter tabellen hvis den allerede findes
		String sql = "DROP TABLE Person ";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// vi ignorerer bevidst denne exception
		}
		// opret person-tabellen
		sql = "CREATE TABLE Person ";
		sql += "(person_pk INTEGER NOT NULL PRIMARY KEY, name VARCHAR(64))";
		stmt.executeUpdate(sql);
		// indsæt 3 personer
		sql = "INSERT INTO Person (person_pk, name)";
		sql += "VALUES (1, 'Bo')";
		stmt.executeUpdate(sql);
		sql = "INSERT INTO Person (person_pk, name)";
		sql += "VALUES (2, 'Kurt')";
		stmt.executeUpdate(sql);
		sql = "INSERT INTO Person (person_pk, name)";
		sql += "VALUES (3, 'Signe')";
		stmt.executeUpdate(sql);
		// læs alle personer
		sql = "SELECT person_pk, name FROM Person";
		ResultSet rs = stmt.executeQuery(sql);
		System.out.println("Selected all rows");
		while (rs.next()) {
			int pk = rs.getInt("person_pk");
			String name = rs.getString("name");
			System.out.println("\t" + pk + " " + name);
		}
		// afslut pænt
		rs.close();
		stmt.close();
		con.close();
	}
}