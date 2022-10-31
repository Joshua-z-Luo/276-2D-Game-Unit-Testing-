package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public Rectangle solidArea;
    public BufferedImage walk;
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn = false;
}
