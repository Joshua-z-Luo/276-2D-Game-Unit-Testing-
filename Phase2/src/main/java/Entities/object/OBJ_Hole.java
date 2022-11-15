package Entities.object;

import Entities.StaticObject;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Hole object that acts as a trap for the main character
 * If main character touches it, the game will end
 * @author Connor
 */
public class OBJ_Hole extends StaticObject {

    /**
     * Constructor for the Hole trap
     * Sets up size of the hit box and the sprite it will use
     */
    public OBJ_Hole() {
        name = "Hole";
        solidArea = new Rectangle(8, 16+48, 32, 32);
        collisionOn = true;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        try {
            image = ImageIO.read(new File("src/Sprites/hole.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws the Hole trap on screen
     * @param g2 Graphics object used to draw
     * @param gp The game panel where the drawing will go
     */
    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, x, y, gp.imageEntityWidth, gp.imageEntityHeight, null);
    }
}
