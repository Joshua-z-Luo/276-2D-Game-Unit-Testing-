package Entities.object;

import Entities.StaticObject;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OBJ_KeyCard extends StaticObject {

    public OBJ_KeyCard(){
        name = "KeyCard";
        solidArea = new Rectangle(8,16, 32, 32);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        try{
            image = ImageIO.read(new File("src/Sprites/keycard.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, x, y, gp.imageEntityWidth, gp.imageEntityHeight, null);
    }

}
