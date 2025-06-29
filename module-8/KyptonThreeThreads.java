/* Kypton Lantz
 * 06/29/2025
 * Advanced Java Programming – Module 8 Assignment
 * This program displays 3 threads of random letters, numbers, and symbols.
 */
  import javafx.application.Application;
  import javafx.application.Platform;
  import javafx.scene.Scene;
  import javafx.scene.control.TextArea;
  import javafx.scene.layout.BorderPane;
  import javafx.stage.Stage;

  import java.util.Random;
  import java.util.concurrent.atomic.AtomicInteger;

public class KyptonThreeThreads extends Application {

  private static final int CHAR_COUNT = 10000;
  private final Random random = new Random();
  private final String specialChars = "!@#$%^&*()_+[]{}|;:',.<>?";
  private final TextArea textArea = new TextArea();
  
  //Atomic integers to keep track of counts
  private final AtomicInteger lettersCount = new AtomicInteger(0);
  private final AtomicInteger digitsCount = new AtomicInteger(0);
  private final AtomicInteger symbolsCount = new AtomicInteger(0);

  @Override
  public void start(Stage primaryStage) {
    textArea.setWrapText(true);

    BorderPane pane = new BorderPane();
    pane.setCenter(textArea);

    Scene scene = new Scene(pane, 600, 400);
    primaryStage.setScene(scene);
    primaryStage.setTitle("KyptonThreeThreads Output");
    primaryStage.show();

    //Start three threads to generate random characters
    new Thread(() -> generateAndAppend("Letters", 'a', 'z', lettersCount)).start();
    new Thread(() -> generateAndAppend("Digits", '0', '9', digitsCount)).start();
    new Thread(() -> generateAndAppendSymbols()).start();
  }

  private void generateAndAppend(String name, char start, char end, AtomicInteger counter) {
    System.out.println(name + " thread started.");
    for (int i = 0; i < CHAR_COUNT; i++) {
      char ch = (char) (random.nextInt(end - start + 1) + start);
      appendCharToUI(ch);
      int current = counter.incrementAndGet();

      //Log progress
      if (i % (CHAR_COUNT / 4) == 0) {
        System.out.println(name + " progress: " + (i * 100 / CHAR_COUNT) + "%");
      }
    }
    System.out.println(name + " thread completed.");
    runFinalTestIfReady();
  }

  private void generateAndAppendSymbols() {
    System.out.println("Symbols thread started.");
    for (int i = 0; i < CHAR_COUNT; i++) {
      char ch = specialChars.charAt(random.nextInt(specialChars.length()));
      appendCharToUI(ch);
      int current = symbolsCount.incrementAndGet();

      if (i % (CHAR_COUNT / 4) == 0) {
        System.out.println("Symbols progress: " + (i * 100 / CHAR_COUNT) + "%");
      }
    }
    System.out.println("Symbols thread completed.");
    runFinalTestIfReady();
  }

  private void appendCharToUI(char ch) {
    Platform.runLater(() -> textArea.appendText(Character.toString(ch)));
  }

  private void runFinalTestIfReady() {
    if (lettersCount.get() == CHAR_COUNT && 
        digitsCount.get() == CHAR_COUNT && 
        symbolsCount.get() == CHAR_COUNT) {
      Platform.runLater(() -> {
        int total = textArea.getText().length();
      System.out.println("=== All threads completed ===");
      System.out.println("Letters Count: " + lettersCount.get());
      System.out.println("Digits Count: " + digitsCount.get());
      System.out.println("Symbols Count: " + symbolsCount.get());
      System.out.println("TextArea total length: " + total);

      //Basic test assertions
      assert lettersCount.get() == CHAR_COUNT : "Letters count mismatch!";
      assert digitsCount.get() == CHAR_COUNT : "Digits count mismatch!";
      assert symbolsCount.get() == CHAR_COUNT : "Symbols count mismatch!";
      assert total == (CHAR_COUNT * 3) : "Total length mismatch!";
      System.out.println("All tests passed successfully!");
      });
    }
  }

  public static void main(String[] args) {
      launch(args);
  }
}