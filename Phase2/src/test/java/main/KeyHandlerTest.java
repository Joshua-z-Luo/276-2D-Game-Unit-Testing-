package main;

import main.GamePanel;
import main.KeyHandler;
import Entities.MainCharacterTV;
import main.UI;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.junit.jupiter.api.Assertions.*;


public class KeyHandlerTest {
    public static boolean upPressed, downPressed, leftPressed, rightPressed; // do we need?
    KeyEvent e;
    public static GamePanel gp = new GamePanel();
    public static KeyHandler kH = new KeyHandler(gp);
    public static MainCharacterTV tvGuy = new MainCharacterTV(gp,kH);

    public void keyTyped(KeyEvent e) {}

    @Test
    void MainCharacterMovesDownWhenDownKeyPressed(){
        // simulate key being pressed
        KeyEvent key = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_S, 'S');
        gp.getKeyListeners()[0].keyPressed(key);
        // check if direction is down
        assertEquals("down",tvGuy.direction);
    }
    @Test
    void MainCharacterMovesUpWhenUpKeyPressed(){
        // fill in

    }
    @Test
    void MainCharacterMovesLeftWhenLeftKeyPressed(){
        // fill in

    }
    @Test
    void MainCharacterMovesRightWhenRightKeyPressed(){
        // fill in

    }
    @Test
    void PauseScreenDisplayedWhenPKeyPressed(){
        // fill in

    }
    @Test
    void InstructionsScreenDisplayedWhenIKeyPressed(){
        // fill in

    }
    @Test
    void GameRestartAfterLosingWhenKeyPressed(){
        // fill in

    }
    @Test
    void GameRestartAfterWinningWhenKeyPressed(){
        // fill in

    }
    @Test
    void GameContinueAfterLosingWhenKeyPressed(){
        // fill in

    }
    @Test
    void GameContinueAfterWinningWhenKeyPressed(){
        // fill in

    }
}
