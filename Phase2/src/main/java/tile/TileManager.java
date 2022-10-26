package tile;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

/**
 * Will manage the map and all the tiles it will contain.
 * Can load a map through a .txt file
 * @author Connor
 */
public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    /**
     * Constructor that will initialize the necessary variables such as an array containing the tiles
     * @param gp The main game panel containing the game
     */
    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();
    }

    /**
     * Initializes the used tiles from the images and sets if the tile can be collided with
     */
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("src/Sprites/grassTile.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("src/Sprites/Wall.png"));
            tile[1].collision = true;

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
            File is = new File("src/Maps/map1.txt");
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
