package main;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * Hayato add stuff here
 * @author Hayato
 */
public class UI {

    GamePanel gp;

    Graphics2D g2;
    Font arial_40, arial_80B;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    /**
     * Hayato add stuff here
     * @param gp
     */
    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN,40 );
        arial_80B = new Font("Arial",Font.BOLD,80 );
    }

    /**
     * Hayato add stuff here
     * @param text
     */
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    /**
     * Hayato add stuff here
     * @param g2
     */
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if(gp.gameState==gp.playState){
            //do playState stuff later
        }
        if(gp.gameState== gp.pauseState){
            drawPauseScreen();
        }
    }

    /**
     * Hayato add stuff here
     */
    public void drawPauseScreen(){
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;

       g2.drawString(text,x,y);
    }

    /**
     * Hayato add stuff here
     * @param text
     * @return
     */
    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
