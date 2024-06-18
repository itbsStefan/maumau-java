package de.nielshoppe.maumau.moves;

import de.nielshoppe.maumau.Player;

public class TakeMove extends BaseMove {

    private int count;

    public TakeMove(int player, int count) {
        this.player = player;
        this.count = Math.max(1, count); // Positive numbers only
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return "TakeMove{" +
                "count=" + count +
                '}';
    }
}
