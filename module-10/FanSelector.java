/* Kypton Lantz
 * 07/08/2025
 * Advanced Java Programming – Module 10 Assignment
 * This program connects to databasedb using user student1 and password pass. Selects data from the table fans to display in the GUI.
 */
import java.sql.*;

public class FanSelector {
    private static final String URL = "jdbc:mysql://localhost:3306/databasedb";
    private static final String USER = "student1";
    private static final String PASS = "pass";

    public static Fan getFanById(int fanID) {
        Fan fan = null;

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = con.prepareStatement(
                     "SELECT firstname, lastname, favoriteteam FROM fans WHERE ID = ?")) {

            stmt.setInt(1, fanID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                fan = new Fan(fanID,
                              rs.getString("firstname"),
                              rs.getString("lastname"),
                              rs.getString("favoriteteam"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fan;
    }

    public static boolean updateFan(Fan fan) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = con.prepareStatement(
                     "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE ID = ?")) {

            stmt.setString(1, fan.getFirstname());
            stmt.setString(2, fan.getLastname());
            stmt.setString(3, fan.getFavoriteteam());
            stmt.setInt(4, fan.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
