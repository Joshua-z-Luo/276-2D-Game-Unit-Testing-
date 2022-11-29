package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The base class for any entities on the map
 * Includes their sprites, location, and if they are able to collide with other entities
 * @author Connor
 */
public class Entity {
    public int x, y;
    public Rectangle solidArea;
    public BufferedImage image, image2, image3, image4, image5;
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn = false;

}
