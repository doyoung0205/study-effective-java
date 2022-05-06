package chapter7.item46;

import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/05/06
 * description :
 */
public class Album {
    private final String name;
    private final int sales;
    private final Artist artist;

    private Album(String name, Artist artist, int sales) {
        this.name = name;
        this.artist = artist;
        this.sales = sales;
    }

    public static Album valueOf(String name, Artist artist, int sales) {
        return new Album(name, artist, sales);
    }

    public String getName() {
        return name;
    }

    public int getSales() {
        return sales;
    }

    public Artist getArtist() {
        return artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return getSales() == album.getSales() && Objects.equals(getName(), album.getName()) && Objects.equals(getArtist(), album.getArtist());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSales(), getArtist());
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", sales=" + sales +
                ", artist=" + artist.getName() +
                '}';
    }
}
