package chapter4.item23;

/**
 * packageName : chapter4.item23
 * fileName : Rectangle
 * author : haedoang
 * date : 2022-03-14
 * description :
 */
public class Rectangle extends FigureNew {
    /** 리스코프 치환 법칙 위배를 위해 가변 객체로 변경함 */
    double width;
    double length;

    Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    @Override
    double area() {
        return width * length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }
}
