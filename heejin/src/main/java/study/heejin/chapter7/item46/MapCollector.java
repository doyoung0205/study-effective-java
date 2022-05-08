package study.heejin.chapter7.item46;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Comparator.comparing;
import static java.util.function.BinaryOperator.maxBy;
import static java.util.stream.Collectors.toMap;

public class MapCollector {

    public static void main(String[] args) {
        List<Album> albums = Arrays.asList(
                Album.of("Butter", 500, "BTS"),
                Album.of("BE", 200, "BTS"),
                Album.of("how i'm feeling", 50, "Lauve"),
                Album.of("LILAC", 80, "IU"),
                Album.of("PLAY", 120, "AKMU"),
                Album.of("Sailing", 70, "AKMU"),
                Album.of("NEXT EPISODE", 100, "AKMU")
        );

        // 판매량이 가장 높은 앨범 수집기
        Map<Artist, Album> topHit = albums.stream()
                .collect(
                        toMap(Album::getArtist, a -> a, maxBy(comparing(Album::getSales)))
                );
        topHit.values().stream().map(Album::getName).forEach(System.out::println);

        System.out.println("-------------");

        // 마지막 값을 취하는 수집기 - last write wins
        Map<Artist, Album> latest = albums.stream()
                .collect(
                        toMap(Album::getArtist, a -> a, (oldVal, newVal) -> newVal)
                );
        latest.values().stream().map(Album::getName).forEach(System.out::println);
    }
}

class Artist {
    private final String name;

    private Artist(String name) {
        this.name = name;
    }

    public static Artist of(String name) {
        return new Artist(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return Objects.equals(name, artist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

class Album {
    private final String name;
    private final int sales;
    private final Artist artist;

    private Album(String name, int sales, Artist artist) {
        this.name = name;
        this.sales = sales;
        this.artist = artist;
    }

    public static Album of(String name, int sales, String artist) {
        return new Album(name, sales, Artist.of(artist));
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
        if (!(o instanceof Album)) return false;
        Album album = (Album) o;
        return sales == album.sales && Objects.equals(name, album.name) && Objects.equals(artist, album.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sales, artist);
    }
}