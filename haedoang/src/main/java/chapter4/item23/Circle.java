package chapter4.item23;

/**
 * packageName : chapter4.item23
 * fileName : Cirlcle
 * author : haedoang
 * date : 2022-03-14
 * description :
 */
public class Circle extends FigureNew{
    final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * (radius * radius);
    }
}
