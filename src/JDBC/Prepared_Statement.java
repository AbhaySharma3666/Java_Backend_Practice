package JDBC;

// 1. import package
import java.sql.*;
import java.util.Scanner;

public class Prepared_Statement {
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
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO student VALUES(?,?)");

            String choice;
            do {
                // 5. Execute Query
                System.out.print("Enter id : ");
                int id = sc.nextInt();
                System.out.print("Enter name : ");
                String name = sc.next();

                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.executeUpdate();

                System.out.println("Data Inserted");

                // Ask user if they want to add another student
                System.out.print("Do you want to add another student? (Y/N): ");
                System.out.println("----------------------------------------------");
                choice = sc.next();

            } while (choice.equalsIgnoreCase("Y"));

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