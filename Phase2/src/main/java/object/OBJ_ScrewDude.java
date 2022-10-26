package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OBJ_ScrewDude extends SuperObject {

    //ctor
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
