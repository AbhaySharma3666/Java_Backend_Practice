package JDBC;

// 1. import package
import java.sql.*;
import java.util.Scanner;

public class Result_Set {
    // Declare constants at class level
    private static final String url = "jdbc:mysql://localhost:3306/college";
    private static final String userName = "root";
    private static final String password = "mysql";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // 2. Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 3. Create connection
            Connection con = DriverManager.getConnection(url, userName, password);

            // 4. Create PreparedStatement
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM student");

            // 5. Execute Query
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(id + " --> " + name);
            }

            // 6. Close resources
            pstmt.close();
            con.close();
            sc.close();

            System.out.println("Program exited.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}