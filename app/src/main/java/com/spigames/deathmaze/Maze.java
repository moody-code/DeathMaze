package com.spigames.deathmaze;

import java.util.ArrayList;

public class Maze {

    // need this for creating match chit AND to track movement
    public enum Direction { NORTH, EAST, SOUTH, WEST }

    private ArrayList<Segment> maze;
    private ChitCup roomChits;
    private ChitCup corridorChits;

    public Maze() {
        //normal
        //maze = null;
        //for testing:
        // ArrayList<Chit> chits = Chit.getRoomChits();
        ArrayList<Chit> chits = Chit.getCorridorChits();
        maze = new ArrayList<Segment>();
        maze.add(new Segment(chits.get(0), -1, -1));
        maze.add(new Segment(chits.get(1), 1, -1));
        maze.add(new Segment(chits.get(2), -1, 1));
        maze.add(new Segment(chits.get(3), 1, 1));
        maze.add(new Segment(chits.get(4), 0, 0));
        maze.add(new Segment(chits.get(5), -4, 1));
        maze.add(new Segment(chits.get(6), -4, 2));
        maze.add(new Segment(chits.get(7), -4, 3));
        maze.add(new Segment(chits.get(8), -4, 4));
        //end test maze
        roomChits = new ChitCup("room");
        corridorChits = new ChitCup("corridor");
    }

    public ArrayList<Segment> getMaze() {
        return maze;
    }

    public boolean addToMaze(Chit chit, int x, int y) {
        if (isSegmentOccupied(x, y)) {
            return false;
        }
        Segment segment = new Segment (chit, x, y);
        maze.add(segment);
        return true;
    }

    private boolean isSegmentOccupied(int x, int y) {
        for (Segment s : maze) {
            if ((s.getX() == x) & (s.getY() == y)) {
                return true;
            }
        }
        return false;
    }

    public Segment getSegmentAtPosition(int x, int y) {
        for (Segment s : maze) {
            if ((s.getX() == x) & (s.getY() == y)) {
                return s;
            }
        }
        return null;
    }
}
