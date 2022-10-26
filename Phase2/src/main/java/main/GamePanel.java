package main;

import MainCharacter.MainCharacterTV;
import MainCharacter.Mobile;
import monster.monsterEntity;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels make it 1920 if fullscreen
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels make it 1080 if fullscreen

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    MainCharacterTV tvGuy = new MainCharacterTV(this, keyH);
    public SuperObject obj[] = new SuperObject[10];

    public  monsterEntity monster[] = new monsterEntity[10];


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //setting the panel size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame(){
        aSetter.setObject();
        aSetter.setMonster();
    }

    public void startGameThread(){
        gameThread = new Thread(this); // starts the thread for the game
        gameThread.start();
    }

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

    public void update(){
        tvGuy.update();

        for(int i = 0;i<monster.length;i++){
           if(monster[i]!=null){
               monster[i].update();
           }
        }
//        for(int i = 0;i<monster.length;i++){
//            if(monster[i]!=null){
//                monster[i].update();
//            }
//
//        }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        //Object (temporary). will delete later
//       for(int i =0;i<obj.length;i++){
//           if(obj[i] != null){
//               obj[i].draw(g2, this);
//
//           }
//       }
       //Monster
        for(int i = 0;i<monster.length;i++){
            if(monster[i]!=null){
                monster[i].draw(g2,this);
            }
        }
        tvGuy.draw(g2);


        g2.dispose();
    }


}
