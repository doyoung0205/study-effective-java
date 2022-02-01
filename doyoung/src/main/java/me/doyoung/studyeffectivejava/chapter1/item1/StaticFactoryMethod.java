package me.doyoung.studyeffectivejava.chapter1.item1;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import static me.doyoung.studyeffectivejava.chapter1.item1.Rank.*;

public class StaticFactoryMethod {

    public static void main(String[] args) throws IOException {

        // 1.
        final Date now = Date.from(Instant.now());

        // 2.
        Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);

        // 3.
        final BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);

        // 4. 같은 인스턴스 일 수 도 있다.
        final Lotto lotto = Lotto.getInstance(33);

        // 5.
        int[] intArray = (int[]) Array.newInstance(int.class, 3);

        // 6.
        FileStore fs = Files.getFileStore(Path.of("/files/text.txt"));

        // 7.
        BufferedReader br = Files.newBufferedReader(Path.of("/files/text.txt"));

        // 8.
        final Vector<String> ve = new Vector<>(Arrays.asList("A", "B", "C"));
        final List<String> list = Collections.list(ve.elements());

        // 9.
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "doyoung";

        try {
            //1. Class.forName(String name) 메소드에 의해 문자열로 전달되는 "com.mysql.jdbc.Driver"이라는 클래스가 메모리에 로드 된다.
            //2. 메모리에 로드되면서 "com.mysql.jdbc.Driver" 클래스의 static 절이 실행된다. 아래와 같이 DriverManager.registerDriver() 메소드를 통해 자기 자신을 등록한다. 즉, 이러한 이유로 Class.forName("com.mysql.jdbc.Driver") 실행시 JDBC Driver가 자동 등록된다.
            Class.forName(driverName);

            // 3. 등록한 JDBC Driver는 데이터베이스 Connection을 생성하는 시점에 사용되게 된다.
            // 서비스 접근 API인 DriverManager.getConnection가 서비스 구현체(서비스 인터페이스)인 Connection 반환
            Connection connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
