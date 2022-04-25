package chapter6.item41;

/**
 * author : haedoang
 * date : 2022/04/22
 * description :
 */
public class User implements Deletable {
    private String name;

    private User(String name) {
        this.name = name;
    }

    public static User valueOf(String name) {
        return new User(name);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
