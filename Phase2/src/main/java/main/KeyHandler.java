package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class that will take input from the keyboard and move the character accordingly
 * @author Connor, Hayato
 */
public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    /**
     * Constructor that takes the main game panel
     * @param gp Main game panel that will be associated with this key handler
     */
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    /**
     * Not used but needed as key listener is an interface
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Checks which key was pressed and sets the variable accordingly so the character will move
     * @param e KeyEvent Entities.object that contains the key that was pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //Title state
        if(gp.gameState == gp.titleState){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 1;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 1){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNum == 1){
                    System.exit(0);
                }
            }
        }

        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;

            }
        }
        if(gp.gameState == gp.loseState) {
            if((((code>=65)&&(code<=90))||((code>=97)&&(code<=122))||((code>=48)&&(code<=57)))) {
                gp.gameState = gp.titleState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
