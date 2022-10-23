package main;

import MainCharacter.MainCharacterTV;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768 pixels
    final int screenHeight = tileSize * maxScreenRow; //576 pixels

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    MainCharacterTV tvGuy = new MainCharacterTV(this, keyH);

    //set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //setting the panel size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this); // starts the thread for the game
        gameThread.start();
    }

    @Override
    public void run(){
        //where the core of the game will go
        double drawInterval = 1000000000/60;
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

    public void update(){
        tvGuy.update();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tvGuy.draw(g2);

        g2.dispose();
    }


}
