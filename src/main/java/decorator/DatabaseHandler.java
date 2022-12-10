package decorator;

import java.sql.*;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;

public class DatabaseHandler {
    private static DatabaseHandler dbconnection;

    private Connection connection;

    @SneakyThrows
    DatabaseHandler() {
        connection = DriverManager.getConnection("jdbc:sqlite:smart.cache.sqlite");
    }

    @SneakyThrows
    public void executeQuery(String query) {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
    }

    @SneakyThrows
    public void insertData(String url, String data) {
        String request = "insert into cached_data (url,data) values (?,?);";
        PreparedStatement ps = connection.prepareStatement(request);
        ps.setString(1, url);
        ps.setString(2, data);
        ps.executeUpdate();
        ps.close();
    }

    public static DatabaseHandler getInstance() {
        if (dbconnection == null) {
            dbconnection = new DatabaseHandler();
        }
        return dbconnection;
    }

    public byte[] getData(String url){
        String sql = "SELECT data FROM cached_data where url = '"+url+"'";
        try {
            Connection conn = connection;
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            if(!rs.next()){
                return "".getBytes();
            }
            else{
                return rs.getBytes("data");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "".getBytes();
    }
    public boolean checkData(String url){
        return getData(url).length != 0;
    }
    public static void main(String[] args) {
        DatabaseHandler db = new DatabaseHandler();
        db.executeQuery("insert into cached_data (url,data) values ('my_test_url', 'somedata');");
        System.out.println(Arrays.toString(db.getData("test")));
        System.out.println(db.checkData("test5"));
    }
}  