package de.wgkassel.curstle.player;

import greenfoot.Greenfoot;

enum Direction {
    UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN;

    public static Direction getCurrent() {

        if (Greenfoot.isKeyDown("a") && Greenfoot.isKeyDown("w")) {
            return LEFT_UP;
        }
        if (Greenfoot.isKeyDown("a") && Greenfoot.isKeyDown("s")) {
            return LEFT_DOWN;
        }
        if (Greenfoot.isKeyDown("d") && Greenfoot.isKeyDown("w")) {
            return RIGHT_UP;
        }
        if (Greenfoot.isKeyDown("d") && Greenfoot.isKeyDown("s")) {
            return RIGHT_DOWN;
        }
        if (Greenfoot.isKeyDown("w")) {
            return UP;
        }
        if (Greenfoot.isKeyDown("s")) {
            return DOWN;
        }
        if (Greenfoot.isKeyDown("a")) {
            return LEFT;
        }
        if (Greenfoot.isKeyDown("d")) {
            return RIGHT;
        }
        return null;
    }
}
