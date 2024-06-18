package de.nielshoppe.maumau.moves;

import de.nielshoppe.maumau.Player;

public abstract class BaseMove implements Move {
    
    protected int player;
    
    public int getPlayer() {
        return player;
    }
}
