package object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Hayato add stuff here
 * @author Hayato
 */
public class OBJ_Battery extends SuperObject{

    /**
     * Hayato add stuff here
     */
    public OBJ_Battery(){
        name = "Battery";
        solidArea = new Rectangle(8,16, 32, 32);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        try{
           walk = ImageIO.read(new File("src/Sprites/battery.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
