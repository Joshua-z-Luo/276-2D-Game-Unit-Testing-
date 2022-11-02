package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The base class for any entities on the map
 * @author Connor
 */
public class Entity {
    public int x, y;
    public Rectangle solidArea;
    public BufferedImage image, image2, image3, image4, image5, image6;
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn = false;


    //some things like maxlife and life should not be in entity class
    public double maxLife;
    public double life; //currently player has about 33 seconds of life
}
