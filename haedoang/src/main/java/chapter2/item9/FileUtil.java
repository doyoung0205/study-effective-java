package chapter2.item9;

import java.io.*;
import java.math.BigInteger;
import java.sql.Connection;

/**
 * packageName : chapter2.item9.try_finally
 * fileName : FileUtil
 * author : haedoang
 * date : 2022-02-18
 * description :
 */
public class FileUtil {
    private static final int BUFFER_SIZE = 1024;

    private FileUtil() {
    }

    static String firstLineOfFile(String path) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            br.close();
            System.out.println("br.close() completed");
        }
    }

    static void copy(String src, String dest, boolean useThrow) throws Exception {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dest);
            if (useThrow) throw new Exception("강제로 예외 발생");
            try {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                while ((n = in.read(buf)) >= 0) {
                    out.write(buf, 0, n);
                }
            } finally {
                out.close();
                System.out.println("out.close() completed");
            }
        } finally {
            in.close();
            System.out.println("in.close() completed");
        }
    }

    static void copyRefactor(String src, String dest, boolean useThrow) throws Exception {
        try (
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);
        ) {
            if (useThrow) throw new Exception("강제로 예외 발생");

            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        }
    }

}
