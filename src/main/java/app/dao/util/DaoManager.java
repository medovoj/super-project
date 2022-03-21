package app.dao.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoManager {
	
	private ConnectionManager instance;
	private static DaoManager daoManagerInstance; 
	
	private DaoManager() {
		instance = ConnectionManager.getInstance();
	}

	public static synchronized DaoManager getInstance(){
		if (daoManagerInstance == null) {
			daoManagerInstance = new DaoManager();
		}
		return daoManagerInstance;
	}

	public<T> T transaction(DaoCommand<T> command)  {
		Connection con = null;
		try {
			con = instance.getConnection();
			T returnValue = command.execute(con);
			con.commit();
			return returnValue;
		} catch (SQLException ex) {
			ConnectionManager.rollback(con);
			throw new RuntimeException(ex);
		} finally {
			ConnectionManager.close(con);
		}
	}
}
