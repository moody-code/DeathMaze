package com.spigames.deathmaze;

import java.util.ArrayList;

public class Chit {

    public enum Side { ANY, WALL, CORRIDOR, DOOR, PEGGED_DOOR }
    public enum Feature { NONE, TRAP_DOOR, FOUNTAIN, STATUE }

    private Side north;
    private Side east;
    private Side south;
    private Side west;
    private Feature feature;

    private Chit(Chit c) {
        this.north = c.north;
        this.east = c.east;
        this.south = c.south;
        this.west = c.west;
        this.feature = c.feature;
    }

    private Chit(Side n, Side e, Side s, Side w, Feature f) {
        north = n;
        east = e;
        south = s;
        west = w;
        feature = f;
    }

    // Chit data currently hard-coded: maybe transfer to a file?
    public static ArrayList<Chit> getRoomChits() {
        ArrayList<Chit> chits = new ArrayList<Chit>();
        addChits(2, chits, Side.WALL, Side.WALL, Side.DOOR, Side.WALL, Feature.FOUNTAIN);
        addChits(2, chits, Side.DOOR, Side.WALL, Side.DOOR, Side.WALL, Feature.FOUNTAIN);
        addChits(2, chits, Side.DOOR, Side.DOOR, Side.DOOR, Side.DOOR, Feature.FOUNTAIN);
        addChits(2, chits, Side.WALL, Side.WALL, Side.DOOR, Side.WALL, Feature.STATUE);
        addChits(2, chits, Side.DOOR, Side.WALL, Side.WALL, Side.DOOR, Feature.STATUE);
        addChits(2, chits, Side.WALL, Side.DOOR, Side.WALL, Side.WALL, Feature.TRAP_DOOR);
        addChits(2, chits, Side.WALL, Side.WALL, Side.DOOR, Side.DOOR, Feature.TRAP_DOOR);
        addChits(24, chits, Side.WALL, Side.WALL, Side.DOOR, Side.WALL, Feature.NONE);
        addChits(14, chits, Side.DOOR, Side.DOOR, Side.DOOR, Side.WALL, Feature.NONE);
        addChits(12, chits, Side.WALL, Side.DOOR, Side.WALL, Side.DOOR, Feature.NONE);
        addChits(4, chits, Side.DOOR, Side.WALL, Side.WALL, Side.DOOR, Feature.NONE);
        addChits(4, chits, Side.DOOR, Side.DOOR, Side.DOOR, Side.DOOR, Feature.NONE);
        addChits(2, chits, Side.DOOR, Side.CORRIDOR, Side.DOOR, Side.CORRIDOR, Feature.NONE);
        addChits(6, chits, Side.DOOR, Side.CORRIDOR, Side.WALL, Side.CORRIDOR, Feature.NONE);
        addChits(2, chits, Side.CORRIDOR, Side.DOOR, Side.WALL, Side.CORRIDOR, Feature.NONE);
        addChits(2, chits, Side.CORRIDOR, Side.CORRIDOR, Side.WALL, Side.DOOR, Feature.NONE);
        addChits(4, chits, Side.WALL, Side.CORRIDOR, Side.WALL, Side.CORRIDOR, Feature.NONE);
        return chits;
    }

    // Chit data currently hard-coded: maybe transfer to a file?
    public static ArrayList<Chit> getCorridorChits() {
        ArrayList<Chit> chits = new ArrayList<Chit>();
        addChits(2, chits, Side.CORRIDOR, Side.CORRIDOR, Side.DOOR, Side.DOOR, Feature.NONE);
        addChits(2, chits, Side.WALL, Side.WALL, Side.CORRIDOR, Side.CORRIDOR, Feature.NONE);
        addChits(2, chits, Side.DOOR, Side.CORRIDOR, Side.CORRIDOR, Side.WALL, Feature.NONE);
        addChits(2, chits, Side.DOOR, Side.WALL, Side.CORRIDOR, Side.CORRIDOR, Feature.NONE);
        addChits(10, chits, Side.WALL, Side.CORRIDOR, Side.DOOR, Side.CORRIDOR, Feature.NONE);
        addChits(6, chits, Side.DOOR, Side.CORRIDOR, Side.DOOR, Side.CORRIDOR, Feature.NONE);
        addChits(4, chits, Side.WALL, Side.CORRIDOR, Side.WALL, Side.CORRIDOR, Feature.NONE);
        return chits;
    }

    // Helper method to create chits
    private static void addChits(int numChits, ArrayList<Chit> chits,
                          Side n, Side e, Side s, Side w, Feature f) {
        for(int i = 0; i < numChits; i++) {
            chits.add(new Chit(n, e, s, w, f));
        }
    }

    // Rotate clockwise
    public void rotate() {
        Side temp = north;
        north = west;
        west = south;
        south = east;
        east = temp;
    }

    // todo: maybe not need this - could just pass parameters directly to doesMatch()
    // only keep if useful on user end
    public Chit getMatchChit(Side n, Side e, Side s, Side w) {
        return new Chit(n, e, s, w, Feature.NONE);
    }

    public boolean doesMatch(Chit match) {
        return (match.north == Side.ANY | match.north == this.north) &
                (match.east == Side.ANY | match.east == this.east) &
                (match.south == Side.ANY | match.south == this.south) &
                (match.west == Side.ANY | match.west == this.west);
    }

    public void setChitSideToWall(Maze.Direction dir) {
        switch (dir) {
            case NORTH:
                this.north = Side.WALL;
                break;
            case EAST:
                this.east = Side.WALL;
                break;
            case SOUTH:
                this.south = Side.WALL;
                break;
            case WEST:
                this.west = Side.WALL;
                break;
        }
    }

    public Side getNorth() {
        return north;
    }

    public Side getEast() {
        return east;
    }

    public Side getSouth() {
        return south;
    }

    public Side getWest() {
        return west;
    }

    public Feature getFeature() {
        return feature;
    }

    public boolean isCorridor() {
        return north == Side.CORRIDOR | east == Side.CORRIDOR | south == Side.CORRIDOR
                | west == Side.CORRIDOR;
    }

    public boolean hasFeature() {
        return feature != Feature.NONE;
    }
}
