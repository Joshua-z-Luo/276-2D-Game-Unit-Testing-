package main;

import Entities.StaticObject;
import Entities.object.OBJ_Life;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

/**
 * UI class that takes care of UI part of this game.
 * @author Hayato
 */
public class UI {

    GamePanel gp;

    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage battery_low, battery_quart, battery_half, battery_3quart, battery_full, battery_dead;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public int commandNum = 0;

    /**
     * Constructor that defines the font used in the game as well as showing the player's life with a battery image
     * @param gp
     */
    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN,40 );
        arial_80B = new Font("Arial",Font.BOLD,80 );
        StaticObject battery = new OBJ_Life(gp);

        battery_low = battery.image;
        battery_quart = battery.image2;
        battery_half = battery.image3;
        battery_3quart = battery.image4;
        battery_full = battery.image5;
        battery_dead = battery.image6;
    }
//
//    /**
//     * @param text
//     */
//    public void showMessage(String text){
//        message = text;
//        messageOn = true;
//    }

    /**
     * draws the UI of the game
     * @param g2
     */
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.black);

        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        if(gp.gameState == gp.playState){
            drawPlayerLife();
            //do playState stuff later
        }
        else if(gp.gameState == gp.pauseState){
            //game is paused and will not be updated
            drawPauseScreen();
        }
        else if(gp.gameState == gp.loseState){
            drawLoseScreen();
        }
        else if(gp.gameState == gp.winState){
            drawWinScreen();
        }
    }

    /**
     * Connor add stuff here
     */
    public void drawPlayerLife(){
        int x = gp.screenWidth - gp.tileSize*3;
        int y = gp.tileSize + 2;
        int i = 0;


        if(gp.tvGuy.life >= 80){
            g2.drawImage(battery_full, x, y, null);
        }
        else if(gp.tvGuy.life < 80 && gp.tvGuy.life >= 60){
            g2.drawImage(battery_3quart, x, y, null);
        }
        else if(gp.tvGuy.life < 60 && gp.tvGuy.life >= 40){
            g2.drawImage(battery_half, x, y, null);
        }
        else if(gp.tvGuy.life < 40 && gp.tvGuy.life >= 20){
            g2.drawImage(battery_quart, x, y, null);
        }
        else if(gp.tvGuy.life < 20 && gp.tvGuy.life > 0){
            g2.drawImage(battery_low, x, y, null);
        }
        else{
            g2.drawImage(battery_dead, x, y, null);
        }
    }

    /**
     * Connor add stuff here
     */
    public void drawLoseScreen(){
        g2.setColor(new Color(255, 0, 0)); //if you want a coloured title scren
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        // YOU LOSE
        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "YOU LOSE";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text,x,y);
        // retry
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
        text = "retry";
        x = getXForCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum == 0) {
            g2.drawString(">", x-40, y);
        }
        // quit
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
        text = "quit";
        x = getXForCenteredText(text);
        y += 55;
        g2.drawString(text,x,y);
        if(commandNum == 1) {
            g2.drawString(">", x-40, y);
        }
    }
    /**
     * draws win screen when player reaches the door (win)
     */
    public void drawWinScreen(){
        g2.setColor(new Color(40, 190, 90)); //if you want a coloured title scren
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "YOU WIN";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text,x,y);

    }

    /**
     * draws the text "Pause" when user clicks p
     */
    public void drawPauseScreen(){
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;

       g2.drawString(text,x,y);
    }

    /**
     * Connor add stuff here
     */
    public void drawTitleScreen(){
        g2.setColor(new Color(255, 255, 255)); //if you want a coloured title scren
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "TV Guy Escape!";
        int x = getXForCenteredText(text);
        int y = gp.tileSize*3;

        //shadow
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);

        //main colour
        g2.setColor(Color.black);
        g2.drawString(text, x, y);

        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.tvGuy.walk, x, y, gp.entityWidth*2, gp.entityHeight*2, null);

        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        text = "PLAY GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize*8;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

    }

    /**
     * gets the x coordinate of the text "Pause"
     * @param text
     * @return int (x coordinate of the text "Pause"
     */
    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
