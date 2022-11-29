package Entities;

import java.awt.image.BufferedImage;

/**
 * Extends the basic Entity class and adds movement speed and walking image
 * @author Connor
 */
public class MovingObject extends Entity {
    public int speed;
    public BufferedImage walk;
    public String direction;
}
