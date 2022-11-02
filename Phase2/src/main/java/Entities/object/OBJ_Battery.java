package Entities.object;

import Entities.StaticObject;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
        solidArea = new Rectangle(8,16, 32, 32);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        try{
           image = ImageIO.read(new File("src/Sprites/battery.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, x, y, gp.entityWidth, gp.entityHeight, null);
    }
}
