/* Kypton Lantz
 * 06/01/2025
 * Advanced Java Programming - Module 2 Assignment
 * This program stores two arrays (int and double), checks for a file, and either writes headers
 * (if new) or appends to the last array lines (if the file already exists).
 */
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class DataWriter {
    public static void main(String[] args) {
        Random random = new Random();
        String filename = "klantz_datafile.dat";
        File file = new File(filename);

        // Generate arrays
        int[] intArray = new int[5];
        double[] doubleArray = new double[5];
        for (int i = 0; i < 5; i++) {
            intArray[i] = random.nextInt(100);     // 0–99
            doubleArray[i] = random.nextDouble() * 100;  // 0.0–99.9
        }

        // Display arrays for Debugging
        System.out.print("Random Integers: ");
        for (int num : intArray) System.out.print(num + " ");
        System.out.print("Random Doubles:  ");
        for (double num : doubleArray) System.out.printf("%.2f ", num);
        System.out.println("\n");

        // Handle file writing
        if (!file.exists()) {
            // File doesn't exist – create and write new
            try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
                pw.println("Random Integers:");
                for (int num : intArray) pw.print(num + " ");
                pw.println();

                pw.println("Random Doubles:");
                for (double num : doubleArray) pw.printf("%.2f ", num);
                pw.println();

                System.out.println("File created and data written.");
            } catch (IOException e) {
                System.err.println("Write error: " + e.getMessage());
            }
        } else {
            // File exists – read lines and find correct ones to append
            try {
                List<String> lines = new ArrayList<>(Files.readAllLines(file.toPath()));

                int intLineIndex = -1;
                int doubleLineIndex = -1;

                // Find "Random Integers:" and "Random Doubles:" headers
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).trim().equals("Random Integers:")) {
                        intLineIndex = i + 1;
                    } else if (lines.get(i).trim().equals("Random Doubles:")) {
                        doubleLineIndex = i + 1;
                    }
                }

                if (intLineIndex != -1 && doubleLineIndex != -1 &&
                    intLineIndex < lines.size() && doubleLineIndex < lines.size()) {

                    // Append to integer line
                    StringBuilder intLine = new StringBuilder(lines.get(intLineIndex).trim());
                    for (int num : intArray) {
                        intLine.append(" ").append(num);
                    }
                    lines.set(intLineIndex, intLine.toString());

                    // Append to double line
                    StringBuilder doubleLine = new StringBuilder(lines.get(doubleLineIndex).trim());
                    for (double num : doubleArray) {
                        doubleLine.append(String.format(" %.2f", num));
                    }
                    lines.set(doubleLineIndex, doubleLine.toString());

                    // Write the updated lines back
                    try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
                        for (String line : lines) {
                            pw.println(line);
                        }
                    }

                    System.out.println("Arrays appended to file.");
                } else {
                    System.err.println("Could not find proper headers in the file.");
                }

            } catch (IOException e) {
                System.err.println("Error reading or writing to file: " + e.getMessage());
            }
        }
    }
}
