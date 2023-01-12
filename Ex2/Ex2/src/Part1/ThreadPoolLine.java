package Part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class ThreadPoolLine implements Callable {
    private String fileName;

    public ThreadPoolLine( String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Object call() throws Exception {
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
            while (reader.readLine() != null) counter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }
}
