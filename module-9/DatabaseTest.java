/* Kypton Lantz
 * 06/29/2025
 * Advanced Java Programming – Module 9 Assignment
 * This program creates creates a database and user/password for the database. It then connects to the database and runs a test to show it was successful.
 */
import java.net.URL;
import java.sql.*;

public class DatabaseTest {
  private Connection con;
  private Statement stmt;

  public DatabaseTest() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      String url = "jdbc:mysql://localhost:3306/databasedb?";
      con = DriverManager.getConnection(url + "user=student1&password=pass");
      stmt = con.createStatement();
      System.out.println("Connection successful!");
    } catch (Exception e) {
      System.out.println("Connection failed: " + e.getMessage());
    }
  }

  public void createTable() {
    if (stmt == null) {
      System.out.println("Statement is not initialized. Cannot create table.");
      return;
    }
    try {
      stmt.executeUpdate("DROP TABLE IF EXISTS address33");
      System.out.println("Table dropped if it existed.");
    } catch (SQLException e) {
      System.out.println("Error dropping table: " + e.getMessage());
    }

    try {
      stmt.executeUpdate("CREATE TABLE address33(ID int PRIMARY KEY,LASTNAME varchar(40)," +
                         "FIRSTNAME varchar(40), STREET varchar(40), CITY varchar(40), STATE varchar(40)," +
                         "ZIP varchar(40))");
      System.out.println("Table address33 created successfully.");
    } catch (SQLException e) {
      System.out.println("Error creating table: " + e.getMessage());
    }
  }

  public void insertData() {
    try {
      System.out.println(
      stmt.executeUpdate("INSERT INTO address33 VALUES(55,'Larry','Rich','1111 Redwing Circle888','Bellevue','NE','68123')") + " row updated");
      System.out.println(
      stmt.executeUpdate("INSERT INTO address33 VALUES(1,'Fine','Ruth','1111 Redwing Circle','Bellevue','NE','68123')") + " row updated");
      System.out.println(
      stmt.executeUpdate("INSERT INTO address33 VALUES(2,'Howard','Curly','1000 Galvin Road South','Bellevue','NE','68005')") + " row updated");
      System.out.println(
      stmt.executeUpdate("INSERT INTO address33 VALUES(3,'Howard','Will','2919 Redwing Circle','Bellevue','NE','68123')") + " row updated");
      System.out.println(
      stmt.executeUpdate("INSERT INTO address33 VALUES(4,'Wilson','Larry','1121 Redwing Circle','Bellevue','NE','68124')") + " row updated");
      System.out.println(
      stmt.executeUpdate("INSERT INTO address33 VALUES(5,'Johnson','George','1300 Galvin Road South','Bellevue','NE','68006')") + " row updated");
      System.out.println(
      stmt.executeUpdate("INSERT INTO address33 VALUES(6,'Long','Matthew','2419 Redwing Circle','Bellevue','NE','68127')") + " row updated");
      System.out.println(
      stmt.executeUpdate("INSERT INTO address33 VALUES(44,'Tom','Matthew','1999 Redwing Circle','Bellevue','NE','68123')") + " row updated");

      stmt.executeUpdate("COMMIT");

      System.out.println("Data Inserted");
    } catch (SQLException e) {
      System.out.println("Error inserting data: " + e.getMessage());
    }
  }

  public void displayData() {
    try {
      ResultSet rs = stmt.executeQuery("SELECT * FROM address33");
      System.out.println("Received Results: ");

      int columns = rs.getMetaData().getColumnCount();

      while (rs.next()) {
        for (int i = 1; i <= columns; i++) {
          System.out.print(rs.getString(i) + "\t ");
        }
        System.out.println();
      }

    } catch (SQLException e) {
      System.out.println("Error displaying data: " + e.getMessage());
    }
  }

  public void closeConnection() {
    try {
      if (stmt != null) {
        stmt.close();
      }
      if (con != null) {
        con.close();
      }
      System.out.println("Connection closed.");
    } catch (SQLException e) {
      System.out.println("Error closing connection: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    DatabaseTest dbTest = new DatabaseTest();
    dbTest.createTable();
    dbTest.insertData();
    dbTest.displayData();
    dbTest.closeConnection();
  }
}