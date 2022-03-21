package app.dao;

import app.dao.util.ConnectionManager;
import app.entities.Role;
import app.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static final String GET_BY_ID_QUERY = "SELECT * FROM my_shop.users " +
            "join my_shop.roles on users.role_id = roles.id " +
            "where users.login=?";

    public User getByLogin(Connection con, String login) {
        User user = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(GET_BY_ID_QUERY);
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                return null;
            }
            while (rs.next()) {
                user = extract(rs);
            }
            return user;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionManager.close(rs);
        }
    }


    private User extract(ResultSet rs) {
        User user = new User();
        try {
            user.setId(rs.getLong("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setRole(Role.getValue(rs.getString("name")));
        } catch (SQLException ex) {
            throw new RuntimeException("Cannot extract the user", ex);
        }
        return user;

    }

}
