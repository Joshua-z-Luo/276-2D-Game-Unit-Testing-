package MainCharacter;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainCharacterTV extends Mobile {
    GamePanel gp;
    KeyHandler keyH;

    public MainCharacterTV(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{

            walk = ImageIO.read(new File("src/Sprites/tvGuy.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed){
            direction = "down";
            y -= speed;
        }
        else if(keyH.leftPressed){
            direction = "down";
            x -= speed;
        }
        else if(keyH.rightPressed){
            direction = "down";
            x += speed;
        }
        else if(keyH.downPressed){
            direction = "down";
            y += speed;
        }
    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        if(direction.equals("down")){
            image = walk;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
