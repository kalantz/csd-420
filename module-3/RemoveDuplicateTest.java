/* Kypton Lantz
 * 06/09/2025
 * Advanced Java Programming - Module 3 Assignment
 * This program uses a generic static method to remove duplicates from an array of 50 random values(range 1-20) and returns a new list that preserves the order of the first occurrences.
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class RemoveDuplicateTest {

  public static void main(String[] args) {
    //Build an ArrayList of 50 random ints (1-20)
    ArrayList<Integer> original = new ArrayList<>();
    Random rng = new Random();
    for (int i = 0; i < 50; i++) {
      original.add(rng.nextInt(20) + 1); // Generates numbers from 1 to 20
    }

    System.out.println("Original List: " + original);

    //Use generic static method to remove duplicates
    ArrayList<Integer> unique = removeDuplicates(original);

    System.out.println("List after removing duplicates: " + unique);
  }

  // Generic static method to remove duplicates from an ArrayList
  public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
    ArrayList<E> result = new ArrayList<>();
    HashSet<E> seen = new HashSet<>(); //tracks what has already been added

    for (E element : list) {
      if (seen.add(element)) { // add returns false if the element was already in the set
        result.add(element); 
      }
    }
    return result;
  }
}