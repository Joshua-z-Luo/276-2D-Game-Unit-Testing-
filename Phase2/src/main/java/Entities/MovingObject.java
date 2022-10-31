package Entities;

import java.awt.image.BufferedImage;

public class MovingObject extends Entity {

    public int speed;
    public BufferedImage walk;
    public String direction; // we should phase this out

    public int directionX;
    public int directionY;
}
