package chapter2.item8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * author : haedoang
 * date : 2022/02/14
 * description :
 */
public class Finalizable {
    private BufferedReader reader;

    public Finalizable() {
        InputStream input = this.getClass()
                .getClassLoader()
                .getResourceAsStream("file.txt");
        reader = new BufferedReader(new InputStreamReader(input));
    }

    public String readFirstLine() throws IOException {
        return reader.readLine();
    }

    @Override
    protected void finalize()  {
        try {
            System.out.println("finalize invoke!!");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean isClose() {
        try {
            reader.ready();
        } catch(Exception e) {
            return true;
        }
        return false;
    }
}
