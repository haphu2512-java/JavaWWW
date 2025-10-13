package fit.se.haxuanphu;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3307/employee_db";
        String user = "root";
        String password = "sa";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to MariaDB successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}