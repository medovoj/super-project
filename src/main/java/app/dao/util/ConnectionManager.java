package app.dao.util;


import org.postgresql.ds.PGSimpleDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class ConnectionManager {

    private static ConnectionManager instance;

    private final PGSimpleDataSource ds;

    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    private ConnectionManager() {
        ds = new PGSimpleDataSource();
        ds.setDatabaseName("postgres");
        ds.setUser("postgres");
        ds.setPassword("4321");

    }


    public Connection getConnection() {
        Connection con = null;
        try {
            con = ds.getConnection();
            con.setAutoCommit(false);
        } catch (SQLException ex) {
            throw new RuntimeException("Can't receive connection", ex);
        }
        return con;
    }

    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Can't receive connection", ex);
            }
        }
    }


    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Can't close statement", ex);
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Can't close result set", ex);
            }
        }
    }

    public static void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }


    public static void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException("Cannot rollback transaction", ex);
            }
        }

    }
}
