/* Kypton Lantz
 * 06/10/2025
 * Advanced Java Programming – Module 4 Assignment
 * This program benchmarks two approaches to traversing a LinkedList:
 * 1. Using an Iterator (enhanced for loop)
 * 2. Using the get(index) method
 * It tests the list at 50,000 and then 500,000 elements, printing elapsed milliseconds for each approach.
 */
import java.util.LinkedList;

public class LinkedListTraversal {

    //Build a LinkedList<Integer>
    private static LinkedList<Integer> buildList(int size) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    //Traverse list with Iterator
    private static long traverseWithIterator(LinkedList<Integer> list) {
        long start = System.nanoTime();
        long checksum = 0;
        for (int value : list) {
            checksum += value;
        }
        long end = System.nanoTime();
        verifySum(checksum, list.size());
        return end - start;
    }

    //Traverse using get(index)
    private static long traverseWithGet(LinkedList<Integer> list) {
        long start = System.nanoTime();
        long checksum = 0;
        for (int i = 0; i < list.size(); i++) {
            checksum += list.get(i);
        }
        long end = System.nanoTime();
        verifySum(checksum, list.size());
        return end - start;
    }

    //Ensure every element was visited and summed correctly
    private static void verifySum(long actual, int size) {
        long expected = (long) size * (size - 1) / 2;  // arithmetic-series sum
        if (actual != expected) {
            throw new IllegalStateException(
                "Checksum failed: expected " + expected + ", but got " + actual);
        }
    }

    public static void main(String[] args) {
        int[] sizes = {50_000, 500_000};

        for (int size : sizes) {
            LinkedList<Integer> list = buildList(size);

            long itrTime = traverseWithIterator(list);
            long getTime = traverseWithGet(list);

            System.out.printf("Size: %,d%n", size);
            System.out.printf("\tIterator   : %.3f ms%n", itrTime / 1_000_000.0); //convert to milliseconds
            System.out.printf("\tget(index) : %.3f ms%n%n", getTime / 1_000_000.0); //convert to milliseconds
        }
    }
}

/* -----------------------------------------------------------
   RESULTS & DISCUSSION (Ran with Java 24, HotSpot 64-bit Server VM on a laptop)

   Runtime Environment:
   - Java Version: 24 (build 24+36-3646)
   - JVM: Java HotSpot(TM) 64-Bit Server VM
   - OS: Microsoft Windows 11 Home
   - CPU: Intel(R) Core(TM) i5-8250U CPU @ 1.60GHz (4 cores, 8 threads)

    Execution Times (approximate, measured in milliseconds):
    Size: 50,000
            Iterator   : 6.581 ms
            get(index) : 1088.805 ms (~1.1 seconds)

    Size: 500,000
            Iterator   : 8.062 ms
            get(index) : 202937.756 ms (~203 seconds or 3.4 minutes)

   Note on hardware:
   The relatively modest CPU impacts absolute runtimes. Faster CPUs could reduce these times, but the dramatic performance difference between the two traversal methods would remain as it is driven by algorithmic complexity, not just hardware speed.

   Why the huge gap?
   - Iterator traversal: Each node’s “next” pointer is followed once.
   - get(i) traversal: Inefficient. It walks from the start/tail to index i each time. Repeating that for i=0..n-1 leads to n(n-1)/2 steps.

   Conclusion: 
   Always use an Iterator (or a foreach loop) with LinkedList.
   If random index-based access is required, use ArrayList instead.
----------------------------------------------------------- */
