package main;

import main.GamePanel;
import main.KeyHandler;
import Entities.MainCharacterTV;
import main.UI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.junit.jupiter.api.Assertions.*;


public class KeyHandlerTest {
    public static boolean upPressed, downPressed, leftPressed, rightPressed; // do we need?
    KeyEvent e;
    public static GamePanel gp = new GamePanel();
    public static KeyHandler kH = new KeyHandler(gp);

    static AssetSetter aSetter = new AssetSetter(gp);
    public static MainCharacterTV tvGuy = new MainCharacterTV(gp,kH);

    public void keyTyped(KeyEvent e) {}

    @BeforeAll
    static void init(){
        //initiate monsters
        aSetter.setMonster();
        gp.setUpGame();
        gp.startGameThread();
        gp.gameState = gp.playState;
    }
    @Test
    void MainCharacterMovesDownWhenDownKeyPressed(){
        // simulate key being pressed
        KeyEvent downKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_S, 'S');
        gp.getKeyListeners()[0].keyPressed(downKey);
        kH.keyPressed(downKey);
        tvGuy.update();
        // check if direction is down
        assertEquals("down",tvGuy.direction);
    }
    @Test
    void MainCharacterMovesUpWhenUpKeyPressed(){
        // simulate key being pressed
        KeyEvent upKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_W, 'W');
        gp.getKeyListeners()[0].keyPressed(upKey);
        kH.keyPressed(upKey);
        tvGuy.update();
        // check if direction is down
        assertEquals("up",tvGuy.direction);
    }
    @Test
    void MainCharacterMovesLeftWhenLeftKeyPressed(){
        // simulate key being pressed
        KeyEvent upKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_A, 'A');
        gp.getKeyListeners()[0].keyPressed(upKey);
        kH.keyPressed(upKey);
        tvGuy.update();
        // check if direction is down
        assertEquals("left",tvGuy.direction);
    }
    @Test
    void MainCharacterMovesRightWhenRightKeyPressed(){
        // simulate key being pressed
        KeyEvent upKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_D, 'D');
        gp.getKeyListeners()[0].keyPressed(upKey);
        kH.keyPressed(upKey);
        tvGuy.update();
        // check if direction is down
        assertEquals("right",tvGuy.direction);
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

    @Test
    void GamePausesWhenPIsPressed(){
        KeyEvent key = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_P, 'P');
        gp.getKeyListeners()[0].keyPressed(key);
        assertEquals(gp.pauseState,gp.gameState);
    }
}
