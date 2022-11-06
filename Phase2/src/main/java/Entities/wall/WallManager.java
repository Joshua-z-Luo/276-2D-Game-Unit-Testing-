package Entities.wall;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class that will handle the wall and their hitboxes
 * @author Joshua
 */
public class WallManager{
    private static WallManager wallManager = null;
    GamePanel gp;
    public Wall[] wall;
    public int mapWallNum[][];

    /**
     * Constructor for the WallManager that takes in the Game Panel
     * @param gp Game Panel the holds the game
     */
    protected WallManager(GamePanel gp){
        this.gp = gp;

        wall = new Wall[10];
        mapWallNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getWallImage();
        loadMap();
    }

    public static WallManager instance(GamePanel gp){
        if (wallManager == null){
            wallManager = new WallManager(gp);
        }
        return wallManager;
    }

    /**
     * Initializes the used tiles from the images and sets if the Entities.tile can be collided with
     */
    public void getWallImage(){
        try{
            //wall[0] is redundant we can probably get rid of it just check for related dependencies
            wall[0] = new Wall();
            wall[0].image = ImageIO.read(new File("src/Sprites/wall1.png"));

            wall[1] = new Wall();
            wall[1].image = ImageIO.read(new File("src/Sprites/wall1.png"));
            wall[1].collision = true;

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Loads the walls from a text file and draws it using the walls it has stored.
     */
    public void loadMap(){
        try{
            File is;
            if(gp.level == 0) {
                is = new File("src/Maps/map1.txt");
            }
            else{
                is = new File("src/Maps/map2.txt");
            }
            BufferedReader br = new BufferedReader(new FileReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();

                while(col < gp.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapWallNum[col][row] = num;
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
     * Draws the walls onto the game panel
     * @param g2 Graphics2D object from the game panel
     */
    public void draw(Graphics2D g2){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){

            int wallNum = mapWallNum[col][row];

            // it looks like the hitbox for the y at least is just going to place itself in the middle
            // so for example if we have a hitbox of one pixel in the y axis, and our picture is 3 pixel in the y axis
            // on default we just draw the hitbox in the middle, so at the second pixel

            switch(wallNum){
                case 1:
                    g2.drawImage(wall[wallNum].image, x, y-(gp.imageEntityHeight /2), gp.imageEntityWidth, gp.imageEntityHeight, null);
                default:
                    //dont do anything,
            }


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
