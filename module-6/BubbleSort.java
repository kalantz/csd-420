/* Kypton Lantz
 * 06/22/2025
 * Advanced Java Programming – Module 6 Assignment
 * This program uses two generic methods of bubbling sorting, using a Comparable interface and a Comparator interface, to reorder a list of elements. Then, it tests to ensure it functions correctly.
 */
import java.util.Comparator;

public class BubbleSort {
  //Generic method to sort an array using Comparable interface
  public static <E extends Comparable<E>> void bubbleSort(E[] list) {
    for (int i = 0; i < list.length - 1; ++i) {
      for (int j = 0; j < list.length - i - 1; ++j) {
        if (list[j].compareTo(list[j + 1]) > 0) {
          E temp = list[j];
          list[j] = list[j + 1];
          list[j + 1] = temp;
        }
      }
    }
  }

  //Generic method to sort an array using Comparator interface
  public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
    for (int i = 0; i < list.length - 1; ++i) {
      for (int j = 0; j < list.length - i - 1; ++j) {
        if (comparator.compare(list[j], list[j + 1]) > 0) {
          E temp = list[j];
          list[j] = list[j + 1];
          list[j + 1] = temp;
        }
      }
    }
  }

  //Utility method to print the array
  public static <E> void printArray(E[] list) {
    System.out.print("[");
    for (int i = 0; i < list.length; i++) {
      System.out.print(list[i]);
      if (i < list.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  //Main method to test the bubble sort methods
  public static void main(String[] args) {
    //Test with Comparable interface
    String[] movieTitles1 = {
        "The Matrix", "Inception", "Pulp Fiction", 
        "Gladiator", "Interstellar", "The Godfather"
    };

    System.out.println("Before Comparable Sort: ");
    printArray(movieTitles1);

    bubbleSort(movieTitles1);

    System.out.println("After Comparable Sort (alphabetical): ");
    printArray(movieTitles1);

    //Test with Comparator interface
    String[] movieTitles2 = {
        "The Matrix", "Inception", "Pulp Fiction", 
        "Gladiator", "Interstellar", "The Godfather"
    };

    System.out.println("\nBefore Comparator Sort: ");
    printArray(movieTitles2);

    Comparator<String> reverseAlphabetical = (a, b) -> b.compareToIgnoreCase(a);
    bubbleSort(movieTitles2, reverseAlphabetical);

    System.out.println("After Comparator Sort (reverse alphabetical): ");
    printArray(movieTitles2);
  }
}
