package Part1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.*;
/**

 Ex2_1 class is used for creating, deleting and counting the lines of text files.
 It has three main methods:
 createTextFiles(int n, int seed, int bound) which creates n text files with random number of rows between 0-bound,
 deleteTextFiles(String[] arrayOfFileName) which deletes all the files in the array
 getNumOfLines(String[] fileNames), getNumOfLinesThreads(String[] fileNames) and getNumOfLinesThreadPool(String[] fileNames)
 which counts the number of lines in all the files in the array
 */
 public class Ex2_1 {
 /**
 Creates n text files with random number of rows between 0-bound.
 @param n number of text files to be created
 @param seed seed for random number generator
 @param bound upper bound for random number of rows
 @return an array of the file names
 */
    public static String[] createTextFiles(int n, int seed, int bound) {
        // array of the file name
        String arrayOfFileName[] = new String[n];
        int rowArray[] = random(seed, bound, n);
        //creat random number of rows
        for (int i = 1; i <= n; i++) {
            int rows = rowArray[i - 1];
            try {
                File myObj = new File("file_" + i + ".txt");
                if (myObj.createNewFile()) {
                    FileOutputStream fos = new FileOutputStream(myObj);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                    for (int j = 0; j < rows; j++) {
                        bw.write("hello world");
                        bw.newLine();
                    }
                    //System.out.println("File created: " + myObj.getName());
                    arrayOfFileName[i - 1] = myObj.getName();
                    bw.close();
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return arrayOfFileName;
    }
    /**
     * Deletes all the files in the array
     *
     * @param arrayOfFileName array of file names to be deleted
     * @return the number of deleted files
     */
    public static int deleteTextFiles(String[] arrayOfFileName) {
        int counter= 0;
        for (String fileName: arrayOfFileName) {
            Path filePath = Paths.get(fileName);
            try {
                if (Files.exists(filePath)) {
                    Files.delete(filePath);
                    counter++;
                }
            } catch (IOException e) {
                System.out.println("An error occurred while trying to delete the file: " + e.getMessage());
            }
        }
        return counter;
    }
    /**
     * Counts the number of lines in all the files in the array
     *
     * @param fileNames array of file names to be counted
     * @return the number of lines in all the files
     */
    public static int getNumOfLines(String[] fileNames) {
        int lines = 0;
        for (String fileName : fileNames) {
            try {
                Path path = Paths.get(fileName);
                if (!Files.exists(path)) {
                    System.out.println("File " + fileName + " does not exist");
                    continue;
                }
                if (!Files.isReadable(path)) {
                    System.out.println("File " + fileName + " is not readable");
                    continue;
                }
                try (BufferedReader reader = Files.newBufferedReader(path)) {
                    while (reader.readLine() != null) {
                        lines++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }
    /**

     Generates an array of random integers between 0 and bound

     @param seed seed for the random number generator

     @param bound upper bound for the random integers

     @param n number of integers in the array

     @return an array of random integers
     */
    public static int[] random(int seed, int bound, int n) {
        int arr[] = new int[n];
        int k = 0;
        Random rand = new Random(seed);
        for (int i = 1; i <= n; i++) {
            int x = rand.nextInt(bound);
            arr[k] = x;
            k++;
        }
        return arr;
    }
    /**

     Counts the number of lines in all the files in the array using multiple threads
     @param fileNames array of file names to be counted
     @return the number of lines in all the files
     @throws InterruptedException if any thread is interrupted
     */
    public static int getNumOfLinesThreads(String[] fileNames) throws InterruptedException { ///Static?
        int lines = 0;
        int k=0;
        TreadLine threads[] = new TreadLine[fileNames.length];

        for (String i : fileNames) {
            threads[k] = new TreadLine(i);
            threads[k].start();
            k++;
        }

        for(TreadLine a : threads) {
               a.join();
               lines += a.getCounter();
        }

        return lines;
    }
    /**

     Counts the number of lines in all the files in the array using a thread pool
     @param fileNames array of file names to be counted
     @return the number of lines in all the files
     */
    public static int getNumOfLinesThreadPool(String[] fileNames) {
        int lines = 0;
        int k =0;
        ExecutorService threadPool1 = Executors.newFixedThreadPool(fileNames.length);
        Future futures[] = new Future[fileNames.length] ;

        for (String i : fileNames) {
            futures[k] = threadPool1.submit(new ThreadPoolLine(i));
            k++;
        }

        for (Future<Integer> a : futures){
            try {
                lines += a.get();
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Caught an exception: " + e.getCause());
            }
        }

        threadPool1.shutdown();
        return lines;
    }

}
