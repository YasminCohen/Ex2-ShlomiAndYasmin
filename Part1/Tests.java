package Part1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Tests {

    String fileNames[];
    long currtime = System.currentTimeMillis();
    final int expectedLines = 49657755, ecpectedLength= 1000;

    @Before
    public void runCreateTextFiles() {
        fileNames = Ex2_1.createTextFiles(ecpectedLength, 1, 99999);

    }
    @Test
    public void testCreateTextFiles() {
        Assert.assertEquals(ecpectedLength, fileNames.length);
        for (int i = 1; i < fileNames.length; i++) {
            Assert.assertEquals("file_"+(i+1)+".txt", fileNames[i]);
        }
    }

    long a = System.currentTimeMillis();
    @Test
    public void testGetNumOfLines() {
       int numOfLines=Ex2_1.getNumOfLines(fileNames);
       Assert.assertEquals(expectedLines, numOfLines);
        System.out.println(" testGetNumOfLines total number of rows: " + numOfLines+ " Time: " + (System.currentTimeMillis() - a) + "ms");
        a = System.currentTimeMillis();

    }

    @Test
    public void testGetNumOfLinesThreads() throws InterruptedException {
        int numOfLines = Ex2_1.getNumOfLinesThreads(fileNames);
        Assert.assertEquals(expectedLines, numOfLines);
        System.out.println(" testGetNumOfLinesThreads total number of rows: " + numOfLines + " Time: " + (System.currentTimeMillis() - a) + "ms");
        a = System.currentTimeMillis();
    }

    @Test
    public void testGetNumOfLinesThreadPool() {
        int numOfLines = Ex2_1.getNumOfLinesThreadPool(fileNames);
        Assert.assertEquals(expectedLines, numOfLines);
        System.out.println(" testGetNumOfLinesThreadPool total number of rows: " + numOfLines+ " Time: " + (System.currentTimeMillis() - a) + "ms");
    }
    @After
    public void testDelete() {
        Assert.assertEquals(ecpectedLength, Ex2_1.deleteTextFiles(fileNames));
    }

}