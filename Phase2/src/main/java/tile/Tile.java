package tile;

import Entities.Entity;

import java.awt.image.BufferedImage;

/**
 * Tile class that will contain the image and if the tile can be walked through
 * @author Connor
 */
public class Tile extends Entity {
    public BufferedImage image;
    public boolean collision = false;

    //we might want to add individual width and height for each tile
}
