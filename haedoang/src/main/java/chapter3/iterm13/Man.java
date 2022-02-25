package chapter3.iterm13;

/**
 * packageName : chapter3.iterm13
 * fileName : Person
 * author : haedoang
 * date : 2022-02-25
 * description :
 */
public class Man implements Cloneable {
    private String name;
    private int age;

    public Man(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void speak() {
        System.out.println("HELLO");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
