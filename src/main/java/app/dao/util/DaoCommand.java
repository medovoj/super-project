package app.dao.util;


import java.sql.Connection;

public interface DaoCommand<T> {
	 T execute(Connection con);

}
