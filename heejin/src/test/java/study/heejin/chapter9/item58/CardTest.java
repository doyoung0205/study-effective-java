package study.heejin.chapter9.item58;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static study.heejin.chapter9.item58.Card.Suit.CLUB;

class CardTest {

    @Test
    @Disabled
    @DisplayName("전통적인 for문에 숨은 버그")
    void traditionalForStatementWithBug() {
        List<Card> deck = new ArrayList<>();
        Collection<Card.Suit> suits = Card.getSuits();
        Collection<Card.Rank> ranks = Card.getRanks();

        // i.next()가 안쪽 반복문에서 호출되어, 숫자당 한번씩이 아닌 카드당 한번씩 호출되고 있음
        for (Iterator<Card.Suit> i = suits.iterator(); i.hasNext();) {
            for (Iterator<Card.Rank> j = ranks.iterator(); j.hasNext();) {
                // NoSuchElementException 발생!
                 deck.add(new Card(i.next(), j.next()));
            }
        }
    }

    @Test
    @DisplayName("전통적인 for문 사용")
    void traditionalForStatement() {
        List<Card> deck = new ArrayList<>();
        Collection<Card.Suit> suits = Card.getSuits();
        Collection<Card.Rank> ranks = Card.getRanks();

        // 위의 문제를 고쳤지만 보기에 좋지 않음
        for (Iterator<Card.Suit> i = suits.iterator(); i.hasNext();) {
            Card.Suit suit = i.next();
            for (Iterator<Card.Rank> j = ranks.iterator(); j.hasNext();) {
                deck.add(new Card(suit, j.next()));
            }
        }

        assertThat(deck).hasSize(52);
    }

    @Test
    @DisplayName("향상된 for문 사용")
    void forEachStatement() {
        List<Card> deck = new ArrayList<>();

        // for-each문을 사용한 권장 관용구
        for (Card.Suit suit : Card.suits) {
            for (Card.Rank rank : Card.ranks) {
                deck.add(new Card(suit, rank));
            }

        }
        assertThat(deck).hasSize(52);

        deck.removeIf(card -> card.getSuit().equals(CLUB));
        assertThat(deck).hasSize(39);
    }

}