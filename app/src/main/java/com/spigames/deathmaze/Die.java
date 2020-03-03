package com.spigames.deathmaze;

import java.util.Random;

public final class Die {

    private static Random die = new Random();

    public static int rollOnce(int numSides) {
        return die.nextInt(numSides) + 1;
    }

    public static int rollMultiple(int numTimes, int numSides) {
        int total = 0;
        for (int i = 0; i < numTimes; i++) {
            total += rollOnce(numSides);
        }
        return total;
    }
}
