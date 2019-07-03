package de.wgkassel.curstle;

import greenfoot.Actor;

public class Story extends Actor {
int removeAt;
public static int removeMe;
    public Story(int x) {
        switch (x) {
            case 1:
                setImage("Story_1.png");
                removeAt = 1;
                break;
            case 2:
                setImage("Story_2.png");
                removeAt = 2;
                break;
            case 3:
                setImage("Story_3.png");
                removeAt = 3;
                break;
            case 4:
                setImage("Story_4.png");
                removeAt = 4;
                break;
            case 5:
                setImage("Story_5.png");
                removeAt = 5;
                break;
            case 6:
                setImage("Story_6.png");
                removeAt = 6;
                break;
            case 7:
                setImage("Story_7.png");
                removeAt = 7;
                break;
            case 8:
                setImage("Story_8.png");
                removeAt = 8;
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
