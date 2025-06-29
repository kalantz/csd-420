/* Kypton Lantz
 * 06/29/2025
 * Advanced Java Programming – Module 7 Assignment
 * This JavaFX program displays four circles and uses the style class and ID.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleStyleDemo extends Application {
  @Override
  public void start(Stage primaryStage) {
    //Create four circles
    Circle circle1 = new Circle(50);
    circle1.getStyleClass().addAll("plaincircle", "circleborder");
    StackPane circle1Container = new StackPane(circle1);
    circle1Container.getStyleClass().add("border");

    Circle circle2 = new Circle(50);
    circle2.setId("redcircle");

    Circle circle3 = new Circle(50);
    circle3.setId("greencircle");

    Circle circle4 = new Circle(50);
    circle4.getStyleClass().add("plaincircle");

    //Layout
    HBox pane = new HBox(20);
    pane.getChildren().addAll(circle1, circle2, circle3, circle4);
    pane.setStyle("-fx-padding: 20; -fx-alignment: center;");

    Scene scene = new Scene(pane);
    scene.getStylesheets().add("circlestyle.css");

    primaryStage.setTitle("Circle Style Demo");
    primaryStage.setScene(scene);
    primaryStage.show();

    //Test to check if styles are applied
    testStyles(circle1, circle2, circle3, circle4);
  }

  private void testStyles(Circle c1, Circle c2, Circle c3, Circle c4) {
    System.out.println("Testing circle1 (plaincircle + border)...");
    assert c1.getStyleClass().contains("plaincircle") : "circle1 should have 'plaincircle' class";

    System.out.println("Testing circle2 (redcircle ID)...");
    assert "redcircle".equals(c2.getId()) : "circle2 should have 'redcircle' ID";

    System.out.println("Testing circle3 (greencircle ID)...");
    assert "greencircle".equals(c3.getId()) : "circle3 should have 'greencircle' ID";

    System.out.println("Testing circle4 (plaincircle, no border)...");
    assert c4.getStyleClass().contains("plaincircle") : "circle4 should have 'plaincircle' class";
  }

  public static void main(String[] args) {
    launch(args);
  }
}