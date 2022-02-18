package study.heejin.chapter2.item9;

import java.io.*;

public class CloseResource {
    private static final int BUFFER_SIZE = 500;

    // try-finally 자원을 회수하는 최선의 방책이 아님
    public static String firstLineOfFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    // try-with-resource 자원을 회수하는 최선책
    public static String firstLineOfFile2(String path) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    // try-with-resource를 catch 절과 함께 사용
    public static String firstLineOfFile2(String path, String defaultValue) {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();

        } catch (IOException e) {
            return defaultValue;
        }
    }

    // 자원이 둘 이상일 때 try-finally 사용
    public static void copy(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);

            try {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                while ((n = in.read(buf)) >= 0) {
                    out.write(buf, 0, n);
                }

            } finally {
                out.close();
            }

        } finally {
            in.close();
        }
    }

    // 자원이 둘 이상일 때 try-with-resource 사용
    public static void copy2(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        }
    }
}
