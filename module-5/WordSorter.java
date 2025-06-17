/* Kypton Lantz
 * 06/17/2025
 * Advanced Java Programming – Module 5 Assignment
 * This program references a file named "collection_of_words.txt" in which words are stored in a TreeSet (handling dupilicates and sorting). A test method verifies functionality and ouputs.
 */
 import java.io.*;
 import java.util.*;

public class WordSorter {
  public static void main(String[] args) {
    String filename = "collection_of_words.txt";

    //Read words from file
    Set<String> uniqueWords = readWordsFromFile(filename);

    //Display in ascending order
    System.out.println("Words in ascending order:");
    for (String word : uniqueWords) {
      System.out.println(word);
    }

    //Display in descending order
    System.out.println("\nWords in descending order:");
    List<String> descendingList = new ArrayList<>(uniqueWords);
    Collections.reverse(descendingList);
    for (String word : descendingList) {
      System.out.println(word);
    }

    //Run test to ensure function works correctly
    testWordSorter();
  }

  //Read all words from file into a sorted set (no duplicates)
  public static Set<String> readWordsFromFile(String filename) {
    Set<String> wordSet = new TreeSet<>();
    try(Scanner scanner = new Scanner(new File(filename))) {
      while (scanner.hasNext()) {
        String word = scanner.next().replaceAll("[^a-zA-Z]", "").toLowerCase(); // Remove non-alphabetic characters and convert to lowercase
        if (!word.isEmpty()) {
          wordSet.add(word);
        }
      }
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + filename);
    }
    return wordSet;
  }

  //Test method to validate functionality
  public static void testWordSorter() {
    String filename = "collection_of_words.txt";
    Set<String> words = readWordsFromFile(filename);

    //Expected words for testing
    Set<String> expectedWords = new HashSet<>(Arrays.asList(
      "aerosmith", "blondie", "cream", "genesis", "journey", "metallica", "muse", "nirvana", "orgy", "queen", "radiohead", "rush", "tool", "weezer"
    ));

    //Unexpected words for testing
    Set<String> unexpectedWords = new HashSet<>(Arrays.asList(
      "beatles", "pink floyd", "rolling stones"
    ));

    boolean allExpectedPresent = true;
    boolean allUnexpectedAbsent = true;

    //Check all expected words are present
    for (String expected : expectedWords) {
      if (!words.contains(expected)) {
        System.out.println("Missing expected word: " + expected);
        allExpectedPresent = false;
      }
    }

    //Check all unexpected words are absent
    for (String unexpected : unexpectedWords) {
      if (words.contains(unexpected)) {
        System.out.println("Found unexpected word: " + unexpected);
        allUnexpectedAbsent = false;
      }
    }

    //If mismatches found, print summary
    if (words.size() !=expectedWords.size()) {
      System.out.println("Mismatch in word count. Expected: " + expectedWords.size() + ", Found: " + words.size());

      //Print differences
      Set<String> missing = new HashSet<>(expectedWords);
      missing.removeAll(words);
      if (!missing.isEmpty()) {
        System.out.println("Missing words: " + missing);
      }

      Set<String> extra = new HashSet<>(words);
      extra.removeAll(expectedWords);
      if (!extra.isEmpty()) {
        System.out.println("Extra words found: " + extra);
      }
    }

    //Print test results
    if (allExpectedPresent 
        && allUnexpectedAbsent 
        && words.size() == expectedWords.size()) {
      System.out.println("Test passed: All checks are successful.");
    } else {
      System.out.println("Test failed: See details above.");
    }
  }
}