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

        solidArea = new Rectangle(8,16, 32, 32);

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

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            } else if (keyH.downPressed) {
                direction = "down";
            }
            collisionOn = false;
            gp.cChecker.checkTile(this);

            if (!collisionOn) {
                switch (direction) {
                    case "up" -> y -= speed;
                    case "left" -> x -= speed;
                    case "right" -> x += speed;
                    case "down" -> y += speed;
                }
            }
        }
    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        switch(direction){
            case "up":
                image = walk;
                break;
            case "down":
                image = walk;
                break;
            case "right":
                image = walk;
                break;
            case "left":
                image = walk;
                break;

        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
