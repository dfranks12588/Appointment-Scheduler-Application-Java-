package DAO;

import main.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {


    public static User getUserByName(String username) throws SQLException{
        String sql = "SELECT * FROM users WHERE User_name = ?";
        PreparedStatement p_s = JDBC.connection.prepareStatement(sql);
        p_s.setString(1, username);
        ResultSet r_s = p_s.executeQuery();

        if (r_s.next()){
            return new User(
                    r_s.getInt("User_ID"),
                    r_s.getString("User_Name"),
                    r_s.getString("Password")
            );
        }
        return null;
    }

    public  static boolean validateLogin(String username, String password) throws SQLException{
        String sql = "SELECT * FROM users WHERE User_name = ? AND Password = ?";
        PreparedStatement p_s = JDBC.connection.prepareStatement(sql);
        p_s.setString(1, username);
        p_s.setString(2, password);
        ResultSet r_s = p_s.executeQuery();

        return r_s.next();
    }
}
