package chapter6.item41;

import java.util.ArrayList;
import java.util.List;

/**
 * author : haedoang
 * date : 2022/04/22
 * description :
 */
public class Group {
    private final String name;
    private final List<User> users;

    private Group(String name) {
        this.name = name;
        this.users = new ArrayList<>();
    }

    public static Group valueOf(String name) {
        return new Group(name);
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
