package bosk.ovelser.ovelse9.ovelse93;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface QueryCallBack {

	void processRecord(ResultSet rs) throws SQLException;
	
}
