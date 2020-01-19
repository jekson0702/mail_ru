package dataBase;

import java.sql.*;

public class User {
    private String login;
    private String password;

    public User(String parameters) {
        this.setUserDataFromDataBase(parameters);
    }

    private User() {

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    private void setUserDataFromDataBase(String parameters) {
        String url = "jdbc:mysql://localhost:3306/user?useSSL=false&useUnicode=true&serverTimezone=UTC";
        String user = "root";
        String password = "123456";
        String query = "SELECT * FROM user.userdata WHERE parameters='" + parameters + "';";
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                this.login = rs.getString(2);
                this.password = rs.getString(3);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}