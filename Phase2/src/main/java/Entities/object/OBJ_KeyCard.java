package Entities.object;

import Entities.StaticObject;

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
            image = ImageIO.read(new File("src/Sprites/temporaryKeyCard.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
