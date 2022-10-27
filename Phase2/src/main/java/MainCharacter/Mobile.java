package MainCharacter;

import main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Base class for any moving entities
 * @author Connor
 */
public class Mobile {
    public int x, y;
    public int speed;

    public BufferedImage walk;
    public String direction;
    public Rectangle solidArea;
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn = false;

}
