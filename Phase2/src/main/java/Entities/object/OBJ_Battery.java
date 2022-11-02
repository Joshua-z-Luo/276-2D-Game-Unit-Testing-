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
     *
     */
    public OBJ_Battery(){
        name = "Battery";

        //using 48 right now, but should be using gp.tileSize
        solidArea = new Rectangle(0, 48, 48, 48);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        try{
           image = ImageIO.read(new File("src/Sprites/battery.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, x, y, gp.imageEntityWidth, gp.imageEntityHeight, null);
    }
}
