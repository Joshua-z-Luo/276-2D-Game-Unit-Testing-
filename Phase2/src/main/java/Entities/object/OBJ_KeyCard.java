package Entities.object;

import Entities.StaticObject;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Keycard object that is required to leave the level
 * @author Hayato
 */
public class OBJ_KeyCard extends StaticObject {

    /**
     * Constructor for the Key card object
     * Sets up size of the hit box and the sprite it will use
     */
    public OBJ_KeyCard(){
        name = "KeyCard";
        solidArea = new Rectangle(0,48, 48, 48);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        try{
            image = ImageIO.read(new File("src/Sprites/keycard.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Draws the key card
     * @param g2 Graphics object used to draw
     * @param gp The game panel where the drawing will go
     */
    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, x, y, gp.imageEntityWidth, gp.imageEntityHeight, null);
    }

}
