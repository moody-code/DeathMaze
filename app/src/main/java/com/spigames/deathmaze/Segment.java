package com.spigames.deathmaze;

public class Segment {

    private Chit chit;
    private int xPosition;
    private int yPosition;
    boolean isExplored;

    public Segment(Chit chit, int x, int y) {
        this.chit = chit;
        this.xPosition = x;
        this.yPosition = y;
        isExplored = false;
    }

    public Chit getChit() {
        return chit;
    }

    public int getX() {
        return xPosition;
    }

    public int getY() {
        return yPosition;
    }

    public boolean isExplored() {
        return isExplored;
    }
}
