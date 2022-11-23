package Entities.tile;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

/**
 * Will manage the map and all the tiles it will contain.
 * Can load a map through a .txt file
 * @author Connor, Joshua
 */
public class TileManager {
    private static TileManager tileManager = null;
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    /**
     * Constructor that will initialize the necessary variables such as an array containing the tiles
     * @param gp The main game panel containing the game
     */
    protected TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();
    }

    /**
     * Instance method that implements the singleton creational pattern
     * @param gp GamePanel that will contain the game
     * @return the single instance of TileManager
     */
    public static TileManager instance(GamePanel gp){
        if (tileManager == null){
            tileManager = new TileManager(gp);
        }
        return tileManager;
    }

    /**
     * Initializes the used tiles from the images and sets if the Entities.tile can be collided with
     */
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("src/Sprites/tile1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("src/Sprites/tile1.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("src/Sprites/tile1.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("src/Sprites/tile1.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("src/Sprites/tile1.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("src/Sprites/tile1.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new File("src/Sprites/tile1.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(new File("src/Sprites/darkTile.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Loads the map from a text file and draws it using the tiles it has stored.
     * Will account for different tiles based on the integer given in the text file
     */
    public void loadMap(){
        try{
            File is;
            if(gp.level == 0) {
                is = new File("src/Maps/map1.txt");
            }
            else if (gp.level==1){
                is  = new File("src/Maps/map2.txt");
            }
            else{
                is  = new File("src/Maps/map3.txt");
            }
            BufferedReader br = new BufferedReader(new FileReader(is));


            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();

                while(col < gp.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Draws the map onto the game panel
     * @param g2 Graphics2D object from the game panel
     */
    public void draw(Graphics2D g2){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = mapTileNum[col][row];

            // it looks like the hitbox for the y at least is just going to place itself in the middle
            // so for example if we have a hitbox of one pixel in the y axis, and our picture is 3 pixel in the y axis
            // on default we just draw the hitbox in the middle, so at the second pixel
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);

            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
