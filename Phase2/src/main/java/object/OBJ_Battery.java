package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OBJ_Battery extends SuperObject{

    public OBJ_Battery(){
        name = "Battery";
        try{
           image = ImageIO.read(new File("src/Sprites/battery.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
