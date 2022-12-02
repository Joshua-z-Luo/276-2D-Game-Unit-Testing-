package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class that will take input from the keyboard and move the character accordingly
 * @author Connor, Hayato, Rose, Joshua
 */
public class KeyHandler implements KeyListener {

    private static KeyHandler keyHandler = null;
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
     * Instance method that implements the singleton creational pattern
     * @param gp GamePanel that will contain the game
     * @return the single instance of KeyHandler
     */
    public static KeyHandler instance(GamePanel gp){
        if(keyHandler == null){
            keyHandler = new KeyHandler(gp);
        }
        return keyHandler;
    }

    /**
     * Not used but needed as key listener is an interface
     * @param e the key event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Checks which key was pressed and sets the variable accordingly so the character will move
     * Also checks what keys have been in the main menu or the win/lose screen
     * @param e KeyEvent object that contains the key that was pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //Title state
        if(gp.gameState == gp.titleState){
            titleStateOptions(e);
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
        PauseInstructionOptions(e);
        if(gp.gameState == gp.loseState) {
            loseState(code);
        }
        if(gp.gameState == gp.winState) {
            winState(code);
        }
    }

    private void titleStateOptions(KeyEvent e){
        int code = e.getKeyCode();
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
                gp.startTime = System.currentTimeMillis();
            }
            if(gp.ui.commandNum == 1){
                System.exit(0);
            }
        }
    }

    private void PauseInstructionOptions(KeyEvent e){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_P){
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;

            }
        }
        if(code == KeyEvent.VK_I) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.instructionsState;
            }
            else if (gp.gameState == gp.instructionsState) {
                gp.gameState = gp.playState;
            }

        }
    }
    /**
     * Checks if the key was released
     * @param e KeyEvent object that contains the key that was pressed
     */
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

    /**
     * Implements the functionality for the lose screen, so you can cycle through the options
     * @param code KeyEvent object that contains the key that was pressed
     */
    public void loseState(int code) {
        if(gp.retries > 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.retry();
                } else if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.titleState;
                    gp.restart();
                }
            }
        }
        else {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.titleState;
                gp.level--;
                gp.restart();
            }
        }
    }

    /**
     * Implements the functionality for the win screen, so you can cycle through the options
     * @param code KeyEvent object that contains the key that was pressed
     */
    public void winState(int code) {
        if(code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
        }
        if(code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.level++;
                gp.retries = 5;
                gp.retry();
            }
            else if(gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }
}
