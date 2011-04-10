package bosk.ovelse09.ovelse93;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface QueryCallBack {
	
	/**
	 * @param rs The resultset to be processed.
	 * @throws SQLException If an error happens when working with the database.
	 */
	void processRecord(ResultSet rs) throws SQLException;
	
}
