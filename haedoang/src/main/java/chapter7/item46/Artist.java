package chapter7.item46;

import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/05/06
 * description :
 */
public class Artist {
    private final String name;

    private Artist(String name) {
        this.name = name;
    }

    public static Artist valueOf(String name) {
        return new Artist(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(getName(), artist.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                '}';
    }
}
