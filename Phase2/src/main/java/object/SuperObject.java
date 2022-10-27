package object;

import MainCharacter.Mobile;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

//parent class of all object class.

/**
 * Hayato add stuff here
 * @author Hayato
 */
public class SuperObject extends Mobile {
//    public BufferedImage image;
    public String name;
//    public boolean collision = false;
//    public int x, y;


    /**
     * Hayato add stuff here
     * @param g2
     * @param gp
     */
    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(walk, x, y, gp.tileSize, gp.tileSize, null);
    }
}
