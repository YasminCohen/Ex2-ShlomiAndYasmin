package Part1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Tests {
    String fileNames[];
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


    @Test
    public void testGetNumOfLines() {
       int numOfLines=Ex2_1.getNumOfLines(fileNames);
       Assert.assertEquals(expectedLines, numOfLines);
    }

    @Test
    public void testGetNumOfLinesThreads() throws InterruptedException {
        int numOfLines = Ex2_1.getNumOfLinesThreads(fileNames);
        Assert.assertEquals(expectedLines, numOfLines);
    }

    @Test
    public void testGetNumOfLinesThreadPool() {
        int numOfLines = Ex2_1.getNumOfLinesThreadPool(fileNames);
        Assert.assertEquals(expectedLines, numOfLines);
    }
    @After
    public void testDelete() {
        Assert.assertEquals(ecpectedLength, Ex2_1.deleteTextFiles(fileNames));
    }

}