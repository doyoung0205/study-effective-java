package chapter3.item13;

/**
 * packageName : chapter3.iterm13
 * fileName : Girl
 * author : haedoang
 * date : 2022-02-25
 * description :
 */
public class Women {
    private String name;
    private int age;

    public Women(String name, int age) {
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
        return new Women(name, age); //anti pattern
    }
}
