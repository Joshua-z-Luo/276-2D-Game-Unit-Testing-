package MainCharacter;

import main.GamePanel;
import main.KeyHandler

import java.awt.*;

public class MainCharacterTV extends Mobile {
    GamePanel gp;
    KeyHandler keyH;

    public MainCharacterTV(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update(){
        if(keyH.upPressed){
            y -= speed;
        }
        else if(keyH.leftPressed){
            x -= speed;
        }
        else if(keyH.rightPressed){
            x += speed;
        }
        else if(keyH.downPressed){
            y += speed;
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.white);

        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
