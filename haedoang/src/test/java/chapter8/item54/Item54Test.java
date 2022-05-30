package chapter8.item54;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter8.item54
 * fileName : Item54Test
 * author : haedoang
 * date : 2022-05-24
 * description :
 */
public class Item54Test {

    @Test
    @DisplayName("collection에서 null을 반환하는 경우 클라이언트에서 처리를 해주어야 한다")
    public void getEmptyMembers() {
        // given
        Team teamA = Team.valueOf("teamA");

        // when
        List<Member> nullValue = teamA.getMembers();

        // then
        assertThat(nullValue).isNull();

        // when
        teamA.addMembers(Arrays.asList(Member.valueOf("member1", 34), Member.valueOf("member2", 24)));

        // then
        assertThat(teamA.getMembers()).hasSize(2).as("클라이언트에서 member 유무에 따라 방어코드를 넣어 처리해야 한다");
    }

    @Test
    @DisplayName("컬렉션이 비어있는 경우 빈 컬렉션을 반환한다")
    public void getMemberIfEmptyMemberThenEmptyCollection() {
        // given
        Team teamA = Team.valueOf("teamA");

        // when
        List<Member> emptyCollection = teamA.getMembersIfEmptyThenReturnEmptyCollection();

        // then
        assertThat(emptyCollection).isEmpty();

        // when
        List<Member> emptyCollection2 = teamA.getMembersIfEmptyThenReturnEmptyCollection();

        // then
        assertThat(emptyCollection).isNotSameAs(emptyCollection2)
                .as("빈 컬렉션이 여러개 생성될 수 있음");
    }

    @Test
    @DisplayName("컬렉션이 비어있는 경우 빈 컬렉션을 반환을 최적화한다")
    public void getMembersRefactoring() {
        // given
        Team teamA = Team.valueOf("teamA");

        // when
        List<Member> emptyCollection = teamA.getMembersRefactoring();

        // then
        assertThat(emptyCollection).isEmpty();

        // when
        List<Member> emptyCollection2 = teamA.getMembersRefactoring();

        // then
        assertThat(emptyCollection).isSameAs(emptyCollection2);
    }


    @Test
    @DisplayName("빈 배열을 반환하는 테스트")
    public void iceCreamTest() {
        // given
        Shop shop = Shop.valueOf("베스킨라빈스");

        // when
        String[] emptyArray = shop.getIceCreamNames();

        // then
        assertThat(emptyArray).hasSize(0);

        // when
        shop.addIceCreams(Arrays.asList(IceCream.valueOf("엄마는 외계인"), IceCream.valueOf("사랑에 빠진 외계인")));

        // then
        assertThat(shop.getIceCreamNames()).hasSize(2);
    }


    @Test
    @DisplayName("빈 배열 최적화")
    public void emptyArray() {
        // given
        Shop shop = Shop.valueOf("베스킨라빈스");

        // when
        String[] emptyArray = shop.getIceCreamNames();

        // then
        assertThat(emptyArray).hasSize(0);
        assertThat(emptyArray).isNotSameAs(shop.getIceCreamNames());

        //when
        String[] newEmptyArray = shop.getIceCreamNamesRefactoring();

        // then
        assertThat(newEmptyArray).isSameAs(shop.getIceCreamNamesRefactoring());
    }
}
