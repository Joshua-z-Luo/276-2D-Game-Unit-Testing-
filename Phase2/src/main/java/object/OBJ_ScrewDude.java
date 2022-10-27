package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Hayato add stuff here
 * @author Hayato
 */
public class OBJ_ScrewDude extends SuperObject {

    //ctor

    /**
     * Hayato add stuff here
     */
    public OBJ_ScrewDude() {

        name="ScrewDude";
        //load image
        try{
            image = ImageIO.read(new File("src/Sprites/screwdude.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
