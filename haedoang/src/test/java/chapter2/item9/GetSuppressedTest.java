package chapter2.item9;

import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

/**
 * author : haedoang
 * date : 2022/02/19
 * description :
 */
@DisplayName("Throwable.getSuppressed() 예외 스택 추적하기")
public class GetSuppressedTest {


    public static void main(String[] args) throws Exception {
        try {
            testException1();
        } catch(Throwable e) {
            final Throwable[] suppExe = e.getSuppressed();
            System.out.println(e);
            for (int i = 0; i <suppExe.length; i++) {
                System.out.println(String.format("Suppressed Exceptions: %s", suppExe[i]));
            }
        }

    }


    public static void testException1() throws Exception {
        Exception suppressed = new ArrayIndexOutOfBoundsException();

        final IOException ioe = new IOException();

        ioe.addSuppressed(suppressed);

        throw ioe;
    }
}
