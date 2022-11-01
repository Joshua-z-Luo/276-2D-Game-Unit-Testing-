package main;

import Entities.MainCharacterTV;
import Entities.StaticObject;
import Entities.monster.monsterEntity;
import tile.TileManager;
import javax.swing.*;
import java.awt.*;

/**
 * This class is where the main logic will go such as how the game will continually run and will update what is currently on screen.
 * @author Connor, Hayato
 */
public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16; //16x16 tile

    final int originalEntityWidth = 16; //16x32 entity

    final int originalEntityHeight = 32; //16x32 entity
    public final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48 tile

    public final int entityWidth = originalEntityWidth * scale;

    public final int entityHeight = originalEntityHeight * scale;
    public final int maxScreenCol = 24; // 16
    public final int maxScreenRow = 18; // 12
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels make it 1920 if fullscreen
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels make it 1080 if fullscreen

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    public UI ui = new UI(this);
    public MainCharacterTV tvGuy = new MainCharacterTV(this, keyH);
    public StaticObject obj[] = new StaticObject[10];

    public  monsterEntity monster[] = new monsterEntity[10];

    //Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int loseState = 3;
    public final int winState = 4;


    /**
     * The constructor that will set the height and width of the window when it pops up as well as the background colour and also will add a key listener
     */
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //setting the panel size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    /**
     * First sets the game to the title screen and then loads in the enemies and power-ups
     */
    public void setUpGame(){
        gameState = titleState;
        aSetter.setObject();
        aSetter.setMonster();
    }

    public void retry() {
        tvGuy.setDefaultPosition();
        tvGuy.restoreLife();
        aSetter.setObject();
        aSetter.setMonster();
    }

    public void restart() {
        tvGuy.setDefaultPosition();
        tvGuy.setDefaultValues();
        tvGuy.restoreLife();
        aSetter.setObject();
        aSetter.setMonster();
    }

    /**
     * Method will start the game thread so that the game will run continuously
     */
    public void startGameThread(){
        gameThread = new Thread(this); // starts the thread for the game
        gameThread.start();
    }

    /**
     * Overriding the run method to update and re-tile the map if needed as well as make the game run in 60FPS
     */
    @Override
    public void run(){
        //where the core of the game will go
        double drawInterval = 1000000000/60; //60 fps
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            //updates character position
            update();
            //draws the screen with updated info
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Will be called by the run method to update the position of the character and enemies
     */
    public void update(){
        if(gameState == playState){
            tvGuy.update();
            tvGuy.life -= 0.05;

            for(int i = 0;i<monster.length;i++){
                if(monster[i]!=null){
                    monster[i].update();
                }
            }
        }
        else if(gameState==pauseState){
            //pause - don't update player information
        }
        else if(gameState==loseState){
            //lose screen is displayed - return to main screen afterwards
        }
    }

    /**
     * Used to draw the entities such as the main character, enemies, map, and rewards
     * @param g Graphics Entities.object that will be turned into a Graphics2D Entities.object
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //Title screen
        if (gameState == titleState) {
            ui.draw(g2);
        }
        else {

            tileM.draw(g2);

            //Object (temporary). will delete later
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);

                }
            }
            //Monster
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    monster[i].draw(g2, this);
                }
            }
            tvGuy.draw(g2);

            //UI
            ui.draw(g2);


            g2.dispose();
        }
    }


}
