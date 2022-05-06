package study.heejin.chapter7.item45;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Card {
    public enum Suit { SPADE, HEART, DIAMOND, CLUB }
    public enum Rank { ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING }

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    private static final List<Card> NEW_DECK = newDeck();

    // 데카르트 곱 - 반복문 사용
//    private static List<Card> newDeck() {
//        List<Card> result = new ArrayList<>();
//
//        for (Suit suit : Suit.values()) {
//            for (Rank rank : Rank.values()) {
//                result.add(new Card(suit, rank));
//            }
//        }
//        return result;
//    }

    // 데카르트 곱 - 스트림 사용
    private static List<Card> newDeck() {
        return Stream.of(Suit.values())
                .flatMap(suit -> Stream.of(Rank.values())
                                    .map(rank -> new Card(suit, rank)))
                .collect(toList());
    }

    @Override
    public String toString() {
        return rank + " of " + suit + "S";
    }

    public static void main(String[] args) {
        System.out.println(NEW_DECK);
    }
}
