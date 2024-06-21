package de.nielshoppe.maumau;

public class Game {

    private Player[] players;
    private GameState state;

    public Game(String[] players) {

        this.players = new Player[players.length];

        for (int i = 0; i < players.length; i++) {
            this.players[i] = new Player(players[i]);
        }

        this.state = new GameState(players.length);
    }

    public Player getPlayer(int player) {
        return players[player];
    }

    public Player[] getPlayers() {
        return players;
    }

    public GameState getState() {
        return state;
    }
}