package chapter10.item72;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/06/18
 * description :
 */
public class SoccerTeam {
    public static final int PLAYERS_COUNT = 11;

    private final String name;
    private List<Player> players;

    private SoccerTeam(String name, List<Player> players) {
        validate(name, players);
        this.name = name;
        this.players = new ArrayList<>(players);
    }

    /**
     *
     * @param name
     * @param players
     * @throws NullPointerException 이름이 null값인 경우
     * @throws IllegalArgumentException 이름 또는 플레이어가 공백인 경우와 플레이어 수가 최소 인원보다 적은 경우
     * @throws IllegalStateException 골키퍼가 1명이 아닌경우
     */
    private void validate(String name, List<Player> players) {
        if (Objects.requireNonNull(name).isEmpty() ||
                Objects.requireNonNull(players).isEmpty() ||
                players.size() < PLAYERS_COUNT
        ) {
            throw new IllegalArgumentException();
        }

        if (players.stream().filter(it -> it.getPosition().equals(Position.GK)).count() != 1) {
            throw new IllegalStateException();
        }
    }

    public static SoccerTeam valueOf(String name, List<Player> players) {
        return new SoccerTeam(name, players);
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
