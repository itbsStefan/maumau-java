package de.nielshoppe.maumau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    Card[] cards;

    @BeforeEach
    void prepareTest() {
        cards = new Card[] {
                new Card(Card.Suit.CLUBS, Card.Rank.JAKE),
                new Card(Card.Suit.HEARTS, Card.Rank.QUEEN),
                new Card(Card.Suit.SPADES, Card.Rank.ACE),
                new Card(Card.Suit.DIAMONDS, Card.Rank.TEN)
        };
    }

    @Test
    void getDeck() {
        Stack<Card> deck = Card.getDeck();
        assertEquals(32, deck.size());
    }

    @Test
    void testToString() {
        assertEquals("JAKE/CLUBS", cards[0].toString());
        assertEquals("QUEEN/HEARTS", cards[1].toString());
        assertEquals("ACE/SPADES", cards[2].toString());
        assertEquals("TEN/DIAMONDS", cards[3].toString());
    }

    @Test
    void getSuit() {
        assertEquals(Card.Suit.CLUBS, cards[0].getSuit());
        assertEquals(Card.Suit.HEARTS, cards[1].getSuit());
        assertEquals(Card.Suit.SPADES, cards[2].getSuit());
        assertEquals(Card.Suit.DIAMONDS, cards[3].getSuit());
    }

    @Test
    void getRank() {
        assertEquals(Card.Rank.JAKE, cards[0].getRank());
        assertEquals(Card.Rank.QUEEN, cards[1].getRank());
        assertEquals(Card.Rank.ACE, cards[2].getRank());
        assertEquals(Card.Rank.TEN, cards[3].getRank());
    }

    @Test
    void testEquals() {
        Card[] expected = new Card[] {
                new Card(Card.Suit.CLUBS, Card.Rank.JAKE),
                new Card(Card.Suit.HEARTS, Card.Rank.QUEEN),
                new Card(Card.Suit.SPADES, Card.Rank.ACE),
                new Card(Card.Suit.DIAMONDS, Card.Rank.TEN)
        };
        assertTrue(cards[0].equals(expected[0]));
        assertTrue(cards[1].equals(expected[1]));
        assertTrue(cards[2].equals(expected[2]));
        assertTrue(cards[3].equals(expected[3]));

        assertFalse(cards[0].equals(expected[1]));
        assertFalse(cards[1].equals(expected[3]));
        assertFalse(cards[2].equals(expected[0]));
        assertFalse(cards[3].equals(expected[2]));
    }
}