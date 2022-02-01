package item1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

/**
 * packageName : item1
 * fileName : DriverManager
 * author : haedoang
 * date : 2022/02/01
 * description :
 */
public class DBUtil {
    private static DBUtil instance;

    private DBUtil() {
    }

    public static DBUtil getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DBUtil();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            //Class.forName("com.mysql.jdbcdriver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db", "root", "1234");
        }
//        catch (ClassNotFoundException e) {
//            System.out.println("해당 클래스가 존재하지 않습니다.");
//        }
        catch (SQLException e) {
            System.out.println("SQL 예외가 발생했습니다.");
        }
        return conn;
    }

    public static void main(String[] args) throws Exception {
        final DBUtil instance =
                DBUtil.getInstance();

        instance.getConnection();
    }

}
