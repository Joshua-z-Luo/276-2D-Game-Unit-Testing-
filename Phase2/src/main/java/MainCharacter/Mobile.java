package MainCharacter;

import main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Mobile {
    public int x, y;
    public int speed;

    public BufferedImage walk;
    public String direction;
    public Rectangle solidArea;
    public boolean collisionOn = false;

}
