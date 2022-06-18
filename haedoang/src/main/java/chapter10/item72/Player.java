package chapter10.item72;

import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/06/18
 * description :
 * FW(공격수)
 * MF (미드필더)
 * DF(수비수)
 * GK(골키퍼)
 */
public class Player {
    public static final int MIN_BACK_NUMBER = 1;


    private final String name;
    private final int backNumber;
    private final Position position;

    private Player(String name, int age, Position position) {
        validate(name, age);
        this.name = Objects.requireNonNull(name);
        this.backNumber = age;
        this.position = Objects.requireNonNull(position);
    }

    private void validate(String name, int backNumber) {
        if (name.isEmpty() || backNumber < MIN_BACK_NUMBER) {
            throw new IllegalArgumentException();
        }
    }

    public static Player valueOf(String name, int backNumber, Position position) {
        return new Player(name, backNumber, position);
    }

    public String getName() {
        return name;
    }

    public int getBackNumber() {
        return backNumber;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return getBackNumber() == player.getBackNumber() && Objects.equals(getName(), player.getName()) && getPosition() == player.getPosition();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBackNumber(), getPosition());
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", backNumber=" + backNumber +
                ", position=" + position +
                '}';
    }
}
