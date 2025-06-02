/* Kypton Lantz
 * 06/01/2025
 * Advanced Java Programming - Module 2 Assignment
 * This program will read from a file named klantz_datafile.dat and display the data to the user.
 */
 import java.io.BufferedReader;
  import java.io.FileReader;
  import java.io.IOException;

public class DataReader {
  public static void main(String[] args) {
    String fileName = "klantz_datafile.dat";

    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      System.out.println("Contents of " + fileName + ":");
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }
  }
}