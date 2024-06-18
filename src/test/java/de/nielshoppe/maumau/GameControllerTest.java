package de.nielshoppe.maumau;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    Game game;
    GameController gameCtrl;

    @BeforeEach
    public void prepareTest() {
        game = new Game(new String[]{ "Player A", "Player B" });
        gameCtrl = new GameController(game);
    }

    @Test
    void getPlayerOnTurn() {
        GameState state = game.getState();
        assertEquals(0, gameCtrl.getPlayerOnTurn());
        state.playerOnTurn = 1;
        assertEquals(1, gameCtrl.getPlayerOnTurn());
    }

    @Test
    void setup() {
        gameCtrl.setup();

        GameState state = game.getState();
        assertEquals(21, state.stock.size());
        assertEquals(5, state.hands[0].size());
        assertEquals(5, state.hands[1].size());
        assertEquals(1, state.dump.size());
    }

    @Test
    void getNextMustTake() {
        GameState state = game.getState();
        assertEquals(0, gameCtrl.getNextMustTake());
        state.nextMustTake = 4;
        assertEquals(4, gameCtrl.getNextMustTake());
    }

    @Test
    void getHand() {
        gameCtrl.setup();

        assertEquals(5, gameCtrl.getHand(0).size());
        assertEquals(5, gameCtrl.getHand(1).size());
    }

    @Test
    void isGameOver() {
        game.getState().hands[0] = new ArrayList<Card>();
        assertEquals(true, gameCtrl.isGameOver());
    }

    @Test
    void handleMove() {
    }
}