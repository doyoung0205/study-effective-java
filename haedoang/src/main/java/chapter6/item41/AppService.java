package chapter6.item41;

/**
 * author : haedoang
 * date : 2022/04/22
 * description :
 */
public class AppService {

    public void delete(Object object) {
        if (!(object instanceof Deletable)) {
            throw new IllegalStateException();
        }
        System.out.println("delete " + object);
    }
}
