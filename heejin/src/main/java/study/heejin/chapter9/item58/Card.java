package study.heejin.chapter9.item58;

import java.util.Arrays;
import java.util.Collection;

public class Card {
    private final Suit suit;
    private final Rank rank;

    enum Suit {CLUB, DIAMOND, HEART, SPADE}

    enum Rank {ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}

    static Collection<Suit> suits = Arrays.asList(Suit.values());
    static Collection<Rank> ranks = Arrays.asList(Rank.values());

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public static Collection<Suit> getSuits() {
        return suits;
    }

    public static Collection<Rank> getRanks() {
        return ranks;
    }
}
