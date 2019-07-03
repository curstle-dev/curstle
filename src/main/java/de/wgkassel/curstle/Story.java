package de.wgkassel.curstle;

import greenfoot.Actor;

public class Story extends Actor {
int removeAt;
public static int removeMe;
    public Story(int x) {
        removeAt = x;
        switch (x) {
            case 1:
                setImage("Story_1.png");
                break;
            case 2:
                setImage("Story_2.png");
                break;
            case 3:
                setImage("Story_3.png");
                break;
            case 4:
                setImage("Story_4.png");
                break;
            case 5:
                setImage("Story_5.png");
                break;
            case 6:
                setImage("Story_6.png");
                break;
            case 7:
                setImage("Story_7.png");
                break;
            case 8:
                setImage("Story_8.png");
                break;
        }
    }

    @Override
    public void act() {
        super.act();
    if (removeAt == removeMe)
        getWorld().removeObject(this);
    }
}
