package chapter4.item20;

/**
 * author : haedoang
 * date : 2022/03/07
 * description :
 */
public class MyCircle implements Circle {
    private final String color;

    public MyCircle(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return color;
    }
}
