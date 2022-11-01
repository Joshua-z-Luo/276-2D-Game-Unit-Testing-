package Entities.object;

import Entities.StaticObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OBJ_Puddle extends StaticObject {

    public OBJ_Puddle() {
        name = "Puddle";
        solidArea = new Rectangle(8, 16, 32, 32);
        collisionOn = true;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        try {
            image = ImageIO.read(new File("src/Sprites/puddle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
