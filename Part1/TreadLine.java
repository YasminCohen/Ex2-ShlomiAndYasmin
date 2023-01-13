package Part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TreadLine extends Thread {
    private int counter =0;
    private String nameOfFile;

    public TreadLine(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }
    public int getCounter() {
        return counter;
    }

    @Override
    public void run() {
            try (BufferedReader reader = new BufferedReader(new FileReader(this.nameOfFile))) {
                while (reader.readLine() != null) counter++;
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
