package chapter4.item23;

/**
 * packageName : chapter4.item23
 * fileName : Figure
 * author : haedoang
 * date : 2022-03-14
 * description :
 */
public class Figure {
    enum Shape {RECTANGLE, CIRCLE}

    ;

    final Shape shape;

    double length;
    double width;

    double radius;

    Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    Figure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    double area() {
        switch (shape) {
            case RECTANGLE:
                return length * width;
            case CIRCLE:
                return Math.PI * (radius * radius);
            default:
                throw new AssertionError(shape);
        }
    }
}
