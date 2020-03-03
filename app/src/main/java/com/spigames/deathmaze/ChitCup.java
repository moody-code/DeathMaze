package com.spigames.deathmaze;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class ChitCup {

    private ArrayList<Chit> availableChits;
    private ArrayList<Chit> discardCup;

    public ChitCup(String cupType) {
        switch (cupType) {
            case "room":
                availableChits = Chit.getRoomChits();
                break;
            case "corridor":
                availableChits = Chit.getCorridorChits();
                break;
            default:
                throw new InvalidParameterException();
        }
        discardCup = null;
    }

    /*
    public Chit drawAcceptableChit(Chit match) {
        // the match is created from the maze segment into which the chit will go
        // a side of ANY indicates no chit on that side (no need to match)

        boolean isAcceptable = false;

        while (!isAcceptable && availableChits.size() > 0) {
            Chit test = drawChit();
            isAcceptable = testChits(test, match);
        }

        // test if acceptable
        // if not, draw another
        // if so, replace all discards and return acceptable chit
    }

    private Chit drawChit() {
        int indexDrawn = Die.rollOnce(availableChits.size());
        return availableChits.remove(indexDrawn);
    }
    */
}
