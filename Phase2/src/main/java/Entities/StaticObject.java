package Entities;

import main.GamePanel;
import java.awt.*;

/**
 * Objects that don't move such as power-ups and traps
 * Extends the Entity class
 * @author Connor, Hayato
 */
public class StaticObject extends Entity{
    public String name;

    /**
     * Draws the image of the static object
     * @param g2 Graphics object used to draw
     * @param gp The game panel where the drawing will go
     */
    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}

