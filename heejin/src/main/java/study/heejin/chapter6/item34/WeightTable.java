package study.heejin.chapter6.item34;

public class WeightTable {
    /**
     * 명령 줄 인수 = 185
     *
     * MERCURY에서의 무게는 69.912739이다.
     * VENUS에서의 무게는 167.434436이다.
     * EARTH에서의 무게는 185.000000이다.
     * MARS에서의 무게는 70.226739이다.
     * JUPITER에서의 무게는 467.990696이다.
     * SATURN에서의 무게는 197.120111이다.
     * URANUS에서의 무게는 167.398264이다.
     * NEPTUNE에서의 무게는 210.208751이다.
     */
    public static void main(String[] args) {
        double erarthWeight = Double.parseDouble(args[0]);
        double mass = erarthWeight / Planet.EARTH.getSurfaceGravity();
        for (Planet p : Planet.values()) {
            System.out.printf("%s에서의 무게는 %f이다.%n", p, p.surfaceWeight(mass));
        }
    }
}
