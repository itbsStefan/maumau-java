package de.nielshoppe.maumau;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameState {
    public Stack<Card> stock;
    public Stack<Card> dump;
    public List<Card>[] hands;
    public int playerOnTurn;
    public int nextMustTake;
    public boolean nextMayTake;
    public boolean nextMustSkip;
    public boolean nextMaySkip;
    public Card.Suit nextSuit;
    public Card.Rank nextRank;

    public GameState(int players) {
        this.stock = Card.getDeck();
        this.dump = new Stack<Card>();

        this.hands = new List[players];
        for (int i = 0; i < players; i++) {
            this.hands[i] = new ArrayList<Card>();
        }

        this.playerOnTurn = 0;
        this.nextMustTake = 0;
        this.nextMustSkip = false;
        this.nextMayTake = true;
        this.nextMaySkip = false;
    }

    @Override
    public String toString() {
        return "Game{" +
                "faceup=" + dump.peek().toString() +
                ", stock=" + stock.size() +
                ", dump=" + dump.size() +
                ", nextSuit=" + nextSuit +
                ", nextRank=" + nextRank +
                ", nextMustTake=" + String.valueOf(nextMustTake) +
                ", nextMustSkip=" + String.valueOf(nextMustSkip) +
                ", nextMayTake=" + String.valueOf(nextMayTake) +
                ", nextMaySkip=" + String.valueOf(nextMaySkip) +
                '}';
    }
}