package de.nielshoppe.maumau;

import de.nielshoppe.maumau.exceptions.IllegalMoveException;
import de.nielshoppe.maumau.moves.Move;
import de.nielshoppe.maumau.moves.PlayMove;
import de.nielshoppe.maumau.moves.SkipMove;
import de.nielshoppe.maumau.moves.TakeMove;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class GameController {

    private Game game;
    private GameState state;

    public GameController(Game game) {
        this.game = game;
        this.state = game.getState();
    }

    // TODO: Methode `getPlayerOnTurn`

    // TODO: Methode `getNextMustTake`

    // TODO: Methode `getHand`

    // TODO: Methode `isGameOver`

    public void setup() {
        // TODO: Kartenspiel erzeugen und auf den Nachziehstapel legen
        // TODO: fünf Karten an jeden Spieler austeilen
        // TODO: oberte Karte vom Nachziehstapel auf den Ablagestapel legen
    }

    public void handleMove(Move move) throws IllegalMoveException {
        if (move.getPlayer() != this.getPlayerOnTurn()) {
            throw new IllegalMoveException("It is not your turn.");
        }

        // TODO: passende Methode aufrufen
    }

    private void takeCard(int player, Card card) {
        state.hands[player].add(card);
    }

    private Card playCard(int player, Card card) {

        if (!state.hands[player].contains(card)) {
            throw new NoSuchElementException("No such card in hand");
        }

        state.hands[player].remove(card);

        return card;
    }

    private Card playCard(int player, int card) {

        if (card < 0 || card > state.hands[player].size()) {
            throw new IndexOutOfBoundsException("No such card in hand");
        }

        return state.hands[player].remove(card);
    }

    private void dealCards(int count) {
        for (int n = 0; n < count; n++) {
            for (int i = 0; i < state.hands.length; i++) {
                state.hands[i].add(state.stock.pop());
            }
        }
    }

    private void handleSkipMove (SkipMove move) throws IllegalMoveException {
        if (state.nextMustSkip || state.nextMaySkip) {
            state.nextMustSkip = false;
            endTurn();
        }
        else if (state.nextMustTake > 0) {
            throw new IllegalMoveException("You must take cards or play a SEVEN.");
        }
        else {
            throw new IllegalMoveException("You must take a card if you cannot play.");
        }
    }

    private void handleTakeMove (TakeMove move) throws IllegalMoveException {
        if (state.nextMustSkip) {
            throw new IllegalMoveException("You must skip or play an EIGHT.");
        } else if (!state.nextMayTake) {
            throw new IllegalMoveException("You must play a card or skip.");
        } else if (state.nextMustTake > 0) {
            if (move.getCount() == state.nextMustTake) {
                for (int n = 0; n < state.nextMustTake; n++) {
                    takeCard(move.getPlayer(), drawCard());
                }
                state.nextMustTake = 0;
                state.nextMayTake = false;
                state.nextMaySkip = true; // no end of turn
            }
            else throw new IllegalMoveException("You must take " +
                    String.valueOf(state.nextMustTake) + " cards.");
        }
        else {
            if (move.getCount() == 1) {
                takeCard(move.getPlayer(), drawCard());
                state.nextMayTake = false;
                state.nextMaySkip = true; // no end of turn
            }
            else throw new IllegalMoveException("You must only take one card if you cannot play.");
        }
    }

    private void handlePlayMove (PlayMove move) throws IllegalMoveException {

        final int player = move.getPlayer();
        final Card card = move.getCard();

        if (state.nextMustSkip) {
            if (card.getRank() == Card.Rank.EIGHT) {
                playCard(player, card);
                state.dump.push(card);
                endTurn();
            }
            else throw new IllegalMoveException("You must play an EIGHT or skip.");
        }
        else if (state.nextMustTake > 0) {
            if (card.getRank() == Card.Rank.SEVEN) {
                playCard(player, card);
                state.dump.push(card);
                state.nextMustTake = state.nextMustTake + 2;
                endTurn();
            }
            else throw new IllegalMoveException("You must play a SEVEN or take cards.");
        }
        else {
            if (!follows(card)) {
                throw new IllegalMoveException("Card must follow suit or rank.");
            }

            playCard(player, card);
            state.dump.push(card);
            state.nextRank = move.getNextRank();
            state.nextSuit = move.getNextSuit();

            switch (card.getRank()) {
                case SEVEN:
                    state.nextMustTake = 2;
                    endTurn();
                    break;
                case EIGHT:
                    state.nextMustSkip = true;
                    endTurn();
                    break;
                case ACE:
                    break;
                default:
                    endTurn();
            }
        }
    }

    private Card drawCard() {
        if (state.stock.size() == 0) {
            Card top = state.dump.pop();
            while (!state.dump.empty()) {
                state.stock.push(state.dump.pop());
            }
            state.dump.push(top);
            Collections.shuffle(state.stock);
        }
        return state.stock.pop();
    }

    private boolean follows(Card card) {
        final Card.Suit suit = card.getSuit();
        final Card.Rank rank = card.getRank();

        if (rank == Card.Rank.JAKE) return true;
        if (state.nextRank == null) return followsSuit(card);

        return followsSuit(card) || followsRank(card);
    }

    private boolean followsSuit(Card card) {
        return state.nextSuit == null || state.nextSuit == card.getSuit();
    }

    private boolean followsRank(Card card) {
        return state.nextRank == null || state.nextRank == card.getRank();
    }

    private void endTurn() {
        state.nextMaySkip = false;
        state.nextMayTake = true;
        state.playerOnTurn = (state.playerOnTurn + 1) % game.getPlayers().length;
    }
}