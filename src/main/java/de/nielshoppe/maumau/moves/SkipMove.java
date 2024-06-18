package de.nielshoppe.maumau.moves;

import de.nielshoppe.maumau.Player;

public class SkipMove extends BaseMove {

    public SkipMove(int player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "SkipMove{}";
    }
}
