package Entities.object;

import Entities.StaticObject;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Puddle object that acts as a trap for the main character
 * If main character touches it, the game will end
 * @author Connor
 */
public class OBJ_Puddle extends StaticObject {

    /**
     * Constructor for the Puddle trap
     * Sets up size of the hit box and the sprite it will use
     */
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
