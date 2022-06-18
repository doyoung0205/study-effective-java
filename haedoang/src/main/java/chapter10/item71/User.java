package chapter10.item71;

import org.apache.commons.lang3.StringUtils;

/**
 * author : haedoang
 * date : 2022/06/18
 * description :
 */
public class User {
    private String name;
    private int age;

    private User(String name, int age) {
        try {
            validate(name, age);
            this.name = name;
            this.age = age;
        } catch (MyCheckedException e) {
            throw new AssertionError();
        }
    }

    public static User valueOf(String name, int age) {
        return new User(name, age);
    }

    private void validate(String name, int age) throws MyCheckedException {
        if (StringUtils.isEmpty(name)) {
            throw new MyCheckedException("이름값은 필수 입니다");
        }
    }

    public String getName() {
        return name;
    }
}
