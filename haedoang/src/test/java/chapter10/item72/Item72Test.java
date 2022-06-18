package chapter10.item72;

import chapter10.item72.Player;
import chapter10.item72.SoccerTeam;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.List;

import static chapter10.item72.Position.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * author : haedoang
 * date : 2022/06/18
 * description :
 */
public class Item72Test {
    @Test
    @DisplayName("축구선수를 생성한다")
    public void createPlayer() {
        // given
        final Player player = Player.valueOf("손흥민", 7, FW);

        // then
        assertThat(player.getPosition().isFw()).isTrue();
    }

    @Test
    @DisplayName("축구팀을 생성한다")
    public void createSoccerTeam() {
        // given
        final ArrayList<Player> players = Lists.newArrayList(
                Player.valueOf("김승규", 1, GK),
                Player.valueOf("이용", 2, DF),
                Player.valueOf("권경원", 20, DF),
                Player.valueOf("김영권", 19, DF),
                Player.valueOf("홍철", 14, DF),
                Player.valueOf("정우영", 5, MF),
                Player.valueOf("황인범", 6, MF),
                Player.valueOf("백승호", 8, MF),
                Player.valueOf("황희찬", 11, FW),
                Player.valueOf("황의조", 16, FW),
                Player.valueOf("손흥민", 7, FW)
        );

        // when
        final SoccerTeam soccerTeam = SoccerTeam.valueOf("브라질_대항전_한국_라인업", players);

        // then
        assertThat(soccerTeam.getPlayers()).hasSize(11);
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 0})
    @DisplayName("허용하지 않는 인수값은 IllegalArgumentException으로 예외 처리한다")
    public void throwIllegalArgumentException(int candidate) {
        // then
        assertThatThrownBy(() -> Player.valueOf("홍명보", candidate, DF))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("null값은 따로 예외처리 한다")
    public void throwNullPointerException() {
        // then
        assertThatThrownBy(() -> Player.valueOf(null, 20, GK))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("객체가 메서드를 수행하기 적절하지 않은 상태는 IllegalStateException 예외 처리한다")
    public void throwIllegalStateException() {
        // given
        final ArrayList<Player> players = Lists.newArrayList(
                Player.valueOf("김승규", 1, GK),
                Player.valueOf("조현우", 23, GK),
                Player.valueOf("이용", 2, DF),
                Player.valueOf("권경원", 20, DF),
                Player.valueOf("김영권", 19, DF),
                Player.valueOf("홍철", 14, DF),
                Player.valueOf("정우영", 5, MF),
                Player.valueOf("황인범", 6, MF),
                Player.valueOf("백승호", 8, MF),
                Player.valueOf("황희찬", 11, FW),
                Player.valueOf("황의조", 16, FW)
        );

        // when
        assertThatThrownBy(() -> SoccerTeam.valueOf("브라질_대항전_한국_라인업", players))
                .isInstanceOf(IllegalStateException.class)
                .as("객체가 메서드를 수행하기 적절하지 않은 상태인 경우 IllegalStateException을 반환한다");
    }

    @Test
    @DisplayName("인덱스 범위를 넘어가는 경우 IndexOutOfBoundsException 예외를 던진다")
    public void throwIndexOutOfBoundsException() {
        // given
        final ArrayList<Player> players = Lists.newArrayList(
                Player.valueOf("김승규", 1, GK),
                Player.valueOf("조현우", 23, GK),
                Player.valueOf("이용", 2, DF),
                Player.valueOf("권경원", 20, DF),
                Player.valueOf("김영권", 19, DF),
                Player.valueOf("홍철", 14, DF),
                Player.valueOf("정우영", 5, MF),
                Player.valueOf("황인범", 6, MF),
                Player.valueOf("백승호", 8, MF),
                Player.valueOf("황희찬", 11, FW),
                Player.valueOf("황의조", 16, FW)
        );

        // then
        assertThatThrownBy(() -> players.get(players.size() + 1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("허용되지 않은 동시 수정 시 ConcurrentModificationException 예외를 발생한다")
    public void throwConcurrentModificationException() {
        // given
        final ArrayList<Player> players = Lists.newArrayList(
                Player.valueOf("김승규", 1, GK),
                Player.valueOf("이용", 2, DF),
                Player.valueOf("권경원", 20, DF),
                Player.valueOf("김영권", 19, DF),
                Player.valueOf("홍철", 14, DF),
                Player.valueOf("정우영", 5, MF),
                Player.valueOf("황인범", 6, MF),
                Player.valueOf("백승호", 8, MF),
                Player.valueOf("황희찬", 11, FW),
                Player.valueOf("황의조", 16, FW),
                Player.valueOf("손흥민", 7, FW)
        );

        // then
        assertThatThrownBy(() -> {
            for (Player player : players) {
                players.remove(1);
            }
        }).isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    @DisplayName("호출한 메서드를 지원하지 않을 경우 unsupportedOperationException 예외가 발생한다")
    public void throwUnsupportedOperationException() {
        List<String> emptyStrings = Collections.emptyList();

        assertThatThrownBy(() -> emptyStrings.remove(0))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
