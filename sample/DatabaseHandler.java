package sample;
import javax.swing.text.Style;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort +"/" +dbName + "?" + "autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser,dbPass);
        return dbConnection;
    }
    public void signUpUser(String firstName, String lastName, String userName, String password, String location, String gender) {
        String insert = "INSERT INTO " + Constant.USER_TABLE + "(" + Constant.USER_FIRSTNAME + "," + Constant.USER_LASTNAME + ","
                + Constant.USER_USERNAME + "," + Constant.USER_PASSWORD + "," + Constant.USER_LOCATION + "," + Constant.USER_GENDER
                + ")" + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement prST = getDbConnection().prepareStatement(insert);
        prST.setString(1, firstName);
        prST.setString(2, lastName);
        prST.setString(3, userName);
        prST.setString(4, password);
        prST.setString(5, location);
        prST.setString(6, gender);

            prST.executeUpdate();
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }
    public ResultSet getUser(String firstName, String lastName, String userName, String password, String location, String gender){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Constant.USER_TABLE + " WHERE " + Constant.USER_USERNAME + "=? AND " + Constant.USER_PASSWORD
                + "=?";

        try {
            PreparedStatement prST = getDbConnection().prepareStatement(select);
            prST.setString(1, userName);
            prST.setString(2, password);


            resSet = prST.executeQuery();
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return resSet;
    }

 /*   public ResultSet getUser(User user) {
    }*/
}
