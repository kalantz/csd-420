/* Kypton Lantz
 * 07/08/2025
 * Advanced Java Programming – Module 10 Assignment
 * This program connects to databasedb using user student1 and password pass. It creates and inserts sample fan data to a table for testing.
 */
import java.sql.*;

public class CreateAndInsertFans {
  public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/databasedb";
    String user = "student1";
    String password = "pass";

    try {
      Class.forName("com.mysql.cj.jdbc.Driver"); // <-- Move this here

      try (Connection con = DriverManager.getConnection(url, user, password);
           Statement stmt = con.createStatement()) {

        stmt.executeUpdate("DROP TABLE IF EXISTS fans");
        stmt.executeUpdate("CREATE TABLE fans (" +
                            "id INT PRIMARY KEY AUTO_INCREMENT," +
                            "firstname VARCHAR(25)," +
                            "lastname VARCHAR(25)," +
                            "favoriteteam VARCHAR(25))");

        stmt.executeUpdate("INSERT INTO fans (firstname, lastname, favoriteteam) VALUES " +
                            "('John', 'Doe', 'Team A')," +
                            "('Jane', 'Smith', 'Team B')," +
                            "('Mike', 'Johnson', 'Team C')");

        System.out.println("Table 'fans' created and data inserted successfully.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}