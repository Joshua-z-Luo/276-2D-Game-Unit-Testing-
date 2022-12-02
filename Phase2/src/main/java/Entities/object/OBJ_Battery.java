package Entities.object;

import Entities.StaticObject;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * The battery class that draws and updates the battery
 * @author Hayato
 */
public class OBJ_Battery extends StaticObject {

    /**
     * Constructor for the battery power up entity
     * Sets up size of the hit box and the sprite it will use
     */
    public OBJ_Battery(){
        name = "Battery";

        //using 48 right now, but should be using gp.tileSize
        solidArea = new Rectangle(0, 48, 48, 48);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        try{
            var pathName ="src/Sprites/battery.png" ;
           image = ImageIO.read(new File(pathName));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Draws the battery object
     * @param g2 Graphics object used to draw
     * @param gp The game panel where the drawing will go
     */
    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, x, y, gp.imageEntityWidth, gp.imageEntityHeight, null);
    }
}
