package bosk.ovelser.ovelse9.ovelse92;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This object is used to connect to the database.
 * 
 * @author Jakob Melnyk & Frederik Roden Lysgaard
 * @version 1
 */
public class DbDataAccessObject {
	
	/**
	 * Saves a person to the database.
	 * 
	 * @param person The person to be saved to the database.
	 * @throws Exception If an error happens when trying to work with the database.
	 */
	public static void savePerson(Person person) throws Exception {
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
	
	/**
	 * Gets a person from the database from the given id.
	 * 
	 * @param id The ID of the person be loaded from the database.
	 * @return The person from the database.
	 * @throws Exception If an error happens when working with the database.
	 */
	public static Person loadPerson(int id) throws Exception {
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
