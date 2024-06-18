package de.nielshoppe.maumau.moves;

import de.nielshoppe.maumau.Card;
import de.nielshoppe.maumau.Player;

public class PlayMove extends BaseMove {

    private Card card;
    private Card.Suit nextSuit;

    public PlayMove(int player, Card card, Card.Suit nextSuit) {

        if (card == null) {
            throw new IllegalArgumentException("Card must not be null.");
        }

        this.player = player;
        this.card = card;
        this.nextSuit = card.getSuit();

        if (card.getRank() == Card.Rank.JAKE) {
            if (nextSuit == null) {
                throw new IllegalArgumentException("Next suit must be specified with a JAKE.");
            }
            this.nextSuit = nextSuit;
        }
    }

    public PlayMove(int player, Card card) {

        this(player, card, null);
    }

    public Card getCard() {
        return card;
    }

    public Card.Rank getNextRank() {
        switch (card.getRank()) {
            case JAKE:
            case ACE:
                return null;
            default:
                return card.getRank();
        }
    }

    public Card.Suit getNextSuit() {
        return nextSuit;
    }

    @Override
    public String toString() {
        return "PlayMove{" +
                "card=" + card +
                ", nextSuit=" + nextSuit +
                '}';
    }
}
