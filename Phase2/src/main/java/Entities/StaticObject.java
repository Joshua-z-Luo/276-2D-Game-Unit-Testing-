package Entities;

import main.GamePanel;

import java.awt.*;

public class StaticObject extends Entity{
    public String name;
    /**
     * Hayato add stuff here
     * @param g2
     * @param gp
     */
    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(walk, x, y, gp.tileSize, gp.tileSize, null);
    }
}

