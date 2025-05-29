/* Kypton Lantz
 * 05/29/2025
 * Advanced Java Programming - Module 1 Assignment
 * This JavaFX program will display four images randomly selected from a deck of 52 cards.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class CardDisplayApp extends Application {
  private static final int TOTAL_CARDS = 52;
  private static final String CARD_DIR = "AssignmentCards/";
  private static final int CARD_HEIGHT = 150;

  private HBox cardBox;

  @Override
  public void start(Stage primaryStage) {
    BorderPane root = new BorderPane();

    cardBox = new HBox(10);
    updateCards(); //Show initial set of cards
    root.setCenter(cardBox);

    Button refreshButton = new Button("Refresh");
    refreshButton.setOnAction(e -> updateCards());
    BorderPane.setAlignment(refreshButton, javafx.geometry.Pos.CENTER);
    root.setBottom(refreshButton);

    Scene scene = new Scene(root);
    primaryStage.setTitle("Random Card Display");
    primaryStage.setScene(scene);
    primaryStage.sizeToScene(); // Adjust the window size to fit the content
    primaryStage.show();
  }

  private void updateCards() {
    ArrayList<Integer> deck = new ArrayList<>();
    for (int i = 1; i <= TOTAL_CARDS; i++) {
      deck.add(i);
    }
    Collections.shuffle(deck); // Shuffle the deck

    cardBox.getChildren().clear(); // Clear previous cards
    for (int i = 0; i < 4; i++) {
      String filename = CARD_DIR + deck.get(i) + ".png";
      Image cardImage = new Image(new File(filename).toURI().toString());
      ImageView cardView = new ImageView(cardImage);
      cardView.setFitHeight(CARD_HEIGHT);
      cardView.setPreserveRatio(true);
      cardBox.getChildren().add(cardView); // Add the card image to the HBox
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}