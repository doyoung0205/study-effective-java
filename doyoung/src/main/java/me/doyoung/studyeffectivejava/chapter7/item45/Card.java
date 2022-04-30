package me.doyoung.studyeffectivejava.chapter7.item45;

import java.util.ArrayList;
import java.util.List;

// 반복 방식과 스트림 방식으로 두 리스트의 데카르트 곱을 생성한다. (275-276쪽)
public class Card {
    public enum Suit {SPADE, HEART, DIAMOND, CLUB}

    public enum Rank {
        ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN,
        EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    private final Suit suit;
    private final Rank rank;

    @Override
    public String toString() {
        return rank + " of " + suit + "S";
    }

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;

    }

    private static final List<Card> NEW_DECK = newDeck();

    // 코드 45-4 데카르트 곱 계산을 반복 방식으로 구현 (275쪽)
    private static List<Card> newDeck() {
        List<Card> result = new ArrayList<>();
        for (Suit suit : Suit.values())
            for (Rank rank : Rank.values())
                result.add(new Card(suit, rank));

        return result;
    }

//    // 코드 45-5 데카르트 곱 계산을 스트림 방식으로 구현 (276쪽)
//    private static List<Card> newDeck2() {
//        return Stream.of(Suit.values())
//                .flatMap(suit -> Stream.of(Rank.values())
//                        .map(rank -> new Card(suit, rank)))
//                .collect(toList());
//    }

    public static void main(String[] args) {
        System.out.println(NEW_DECK);
    }
}
