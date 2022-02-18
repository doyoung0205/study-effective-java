package chapter2.item9;

import chapter2.item9.FileUtil;
import jdk.swing.interop.SwingInterOpUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * packageName : chapter2.item9.try_finally
 * fileName : FileUtilTest
 * author : haedoang
 * date : 2022-02-18
 * description :
 */
class FileUtilTest {

    @Test
    @DisplayName("파일의 첫줄을 읽는다.")
    public void readFileFirstLine() throws IOException  {
        //given
        String path = "src/main/resources/file.txt";

        // when
        String actual = FileUtil.firstLineOfFile(path);

        // then
        assertThat(actual).isEqualTo("두아리파");
    }


    @Test
    @DisplayName("파일을 복사한다")
    public void copyFile() throws Exception {
        // given
        String src = "src/main/resources/file.txt";
        String dest = "src/main/resources/file_copy.txt";
        File file = new File(dest);
        if (file.exists()) {
            file.delete();
        }

        // when
        FileUtil.copy(src, dest, false);

        // then
        assertThat(new File(dest).isFile()).isTrue();
    }

    @Test
    @DisplayName("try-finally 예외처리가 잘못된 경우 자원회수를 제대로 하지 못한다")
    public void tryFinallyCaseNG() throws Exception {
        // given
        String src = "src/main/resources/file.txt";
        String dest = "src/main/resources/file_copy.txt";
        File file = new File(dest);
        if (file.exists()) {
            file.deleteOnExit();
        }

        // when & then
        assertThatThrownBy(() -> FileUtil.copy(src, dest, true))
                .isInstanceOf(Exception.class)
                .as("out.close()를 실행하지 못함");
    }

    @Test
    @DisplayName("tryWithResource는 자원을 자동으로 회수한다")
    public void tryWithResourceTest() throws Exception {
        // given
        String src = "src/main/resources/file.txt";
        String dest = "src/main/resources/file_copy.txt";
        File file = new File(dest);
        if (file.exists()) {
            file.deleteOnExit();
        }

        // when
        FileUtil.copyRefactor(src, dest, false);

        // then
        assertThat(new File(dest).isFile()).isTrue();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("try-with-resource 자원을 자동으로 회수한다");
        try (
            TestResource resource = new TestResource();
        ) {
            resource.run();
        }

        System.out.println("-----------------------------------------------------------");
        System.out.println("JDK9 부터 try-with-resource try 블록 전에 리소스를 생성할수 있다.");

        TestResource resource1 = new TestResource();
        TestResource resource2 = new TestResource();
        TestResource resource3 = new TestResource();
        TestResource resource4 = new TestResource();

        try (resource1; resource2; resource3; resource4 )  {
            resource1.run();
            resource2.run();
            resource3.run();
            resource4.run();
        }

    }
}
