package chapter4.item23;

/**
 * packageName : chapter4.item23
 * fileName : Square
 * author : haedoang
 * date : 2022-03-14
 * description :
 */
public class Square extends Rectangle {

    Square(double side) {
        super(side, side);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        super.setLength(width);
    }

    @Override
    public void setLength(double length) {
        super.setLength(length);
        super.setWidth(length);
    }
}
