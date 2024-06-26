package de.nielshoppe.maumau;

import de.nielshoppe.maumau.exceptions.IllegalMoveException;
import de.nielshoppe.maumau.moves.Move;
import de.nielshoppe.maumau.moves.PlayMove;
import de.nielshoppe.maumau.moves.SkipMove;
import de.nielshoppe.maumau.moves.TakeMove;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Let's play Mau-Mau!");
        if(args.length>0) {
            Character c = args[0].charAt(0);
            int max = args[0].length();
            if( max > 10 )
                max = 9;
            String spieler1 = c.toString().toUpperCase() + args[0].substring(1, max);
            args = new String[]{ spieler1,"NPC1", "npc2" };
        }else{
            args = new String[]{"Player A", "Player B"};
        }
        run_a_game(args);
    }

    static void run_a_game(String[] names) {
        Game game = new Game(names);
        GameController gameCtrl = new GameController(game);
        gameCtrl.setup();

        do {
            System.out.println();
            System.out.println(game.getState().toString());
            try {
                int playerNo = gameCtrl.getPlayerOnTurn();
                Player player = game.getPlayer(playerNo);
                Move move = promptMove(
                        player, playerNo,
                        gameCtrl.getHand(playerNo),
                        Math.max(1, gameCtrl.getNextMustTake())
                );
                System.out.println(move);
                gameCtrl.handleMove(move);
            } catch (IllegalMoveException e) {
                System.err.println(e.getMessage());
            }
        } while (!gameCtrl.isGameOver());

        System.out.println("Game over!");

    }

    static Move promptMove (Player player, int playerNo, List<Card> hand, int maxTake) {

        System.out.println("Player " + player.getName() + ": " + hand.toString());

        int n = promptInt("Make your move", -hand.size(), maxTake);

        if (n < 0) {
            final Card card = hand.get(-1-n);
            if (card.getRank() == Card.Rank.JAKE) {
                Card.Suit nextSuit = promptSuit();
                return new PlayMove(playerNo, card, nextSuit);
            } else {
                return new PlayMove(playerNo, card);
            }
        } else if (n > 0) {
            return new TakeMove(playerNo, n);
        } else {
            return new SkipMove(playerNo);
        }
    }

    static Card.Suit promptSuit() {
        int n = promptInt("Choose next suit (CLUBS, HEARTS, SPADES, DIAMONDS):", 1, 4);
        return Card.Suit.values()[n-1];
    }

    static int promptInt(String prompt, int min, int max) {
        System.out.print(prompt + " [" + String.valueOf(min) + ", " + String.valueOf(max) + "]: ");
        int n = 0;
        do {
            n = in.nextInt();
        } while (n < min || n > max);
        return n;
    }
}