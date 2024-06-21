package de.nielshoppe.maumau;

import java.util.Collections;
import java.util.Stack;

public class Card {

    public enum Suit { CLUBS, HEARTS, SPADES, DIAMONDS };
    // public enum Rank { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JAKE, QUEEN, KING, ACE };
    public enum Rank { SEVEN, EIGHT, NINE, TEN, JAKE, QUEEN, KING, ACE };

    // TODO: Eigenschaften `suit` und `rank`
    private Suit suit;
    private Rank rank;

    // TODO: Konstruktor
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // TODO: Methode `toString`
    public String toString() {
        return rank + "/" + suit;
    }

    // TODO: Methode `getSuit`
    public Suit getSuit () {
        return suit;
    }

    // TODO: Methode `getRank`
    public Rank getRank() {
        return rank;
    }

    // TODO: Methode `equals`
    @Override
    public boolean equals(Object other) {
        if (other instanceof Card) {
            Card otherCard = (Card) other;
            return rank == otherCard.rank && suit == otherCard.suit;
        }
        return false;
    }

    // TODO: statische Methode `getDeck`
    public static Stack<Card> getDeck() {

        Stack<Card> deck = new Stack<Card>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }

        Collections.shuffle(deck);

        return deck;
    }
}