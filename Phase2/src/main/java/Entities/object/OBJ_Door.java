package Entities.object;

import Entities.StaticObject;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Door object that will be used to leave the level
 * @author Hayato
 */
public class OBJ_Door extends StaticObject {

    /**
     * Constructor for the Door object
     * Sets up size of the hit box and the sprite it will use
     */
    public OBJ_Door(){
        name = "Door";
        solidArea = new Rectangle(0, 48, 48, 48);
        collisionOn = true;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        try {
            image = ImageIO.read(new File("src/Sprites/exit.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws the Exit door entity
     * @param g2 Graphics object used to draw
     * @param gp The game panel where the drawing will go
     */
    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, x, y, gp.imageEntityWidth, gp.imageEntityHeight, null);
    }
}
