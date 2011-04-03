package bosk.ovelser.ovelse9.ovelse92;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbDataAccessObject {

	public static void savePerson(Person person) throws SQLException, Exception {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		String url = "jdbc:derby:PersonDB;create=true";
		Connection con = DriverManager.getConnection(url);
		
		PreparedStatement savePerson = null;
		String saveP = "INSERT INTO PERSON" + " VALUES (?, ?, ?)";
		savePerson = con.prepareStatement(saveP);
		savePerson.setInt(1, person.getId());
		savePerson.setString(2, person.getFirstName());
		savePerson.setString(3, person.getLastName());
		savePerson.execute();
		con.commit();
	}

	public static Person loadPerson(int id) throws SQLException, Exception {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		String url = "jdbc:derby:PersonDB;create=true";
		Connection con = DriverManager.getConnection(url);
		
		PreparedStatement loadPerson = null;
		String load = "SELECT * FROM PERSON WHERE PERSON_PK = ?";
		loadPerson = con.prepareStatement(load);
		loadPerson.setInt(1, id);
		
		ResultSet rs = loadPerson.executeQuery();
		if(!rs.next()){
			System.out.println("Ingen person");
		}
		return new Person(rs.getInt(1), rs.getString(2), rs.getString(3));
	}
}
