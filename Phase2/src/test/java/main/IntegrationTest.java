package main;

import Entities.MainCharacterTV;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IntegrationTest {
    public static GamePanel gp = new GamePanel();
    public static KeyHandler kH = new KeyHandler(gp);
    public static MainCharacterTV mC = new MainCharacterTV(gp, kH);

    @BeforeEach
    public void init() {
        gp.setUpGame();
        gp.startGameThread();
    }

    /**
     * Tests the KeyHandler and the UI is interacting as expected in the main menu
     * Tests if the player can go through the options in the main menu
     */
    @Test
    void KeyHandlerandUIInteractionsWithOptionsInMainMenu() {
        assertEquals(gp.titleState, gp.gameState);
        KeyEvent downKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_S, 'S');
        gp.getKeyListeners()[0].keyPressed(downKey);
        assertEquals(1, gp.ui.commandNum);

        KeyEvent upKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_W, 'W');
        gp.getKeyListeners()[0].keyPressed(upKey);
        assertEquals(0, gp.ui.commandNum);
    }

    /**
     * Tests the interaction between the KeyHandler and the UI in the main menu
     * Tests if the player can press enter when the Play option is selected and then checks if the game state is changed to play state
     */
    @Test
    void KeyHandlerUIandGamePanelInteractionsWhenPlayEntered(){
        assertEquals(gp.titleState, gp.gameState);
        assertEquals(0, gp.ui.commandNum);
        KeyEvent enterKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_ENTER, 'e');
        gp.getKeyListeners()[0].keyPressed(enterKey);
        assertEquals(gp.playState, gp.gameState);
    }

    /**
     * Tests the interaction between the KeyHandler and the UI in the lose screen
     * Tests if the player can go through the lose options
     */
    @Test
    void KeyHandlerandUIInteractionsWhenLoseState(){
        gp.gameState = gp.playState;
        mC.life = 0;
        mC.update();
        assertEquals(gp.loseState, gp.gameState);
        KeyEvent downKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_S, 'S');
        gp.getKeyListeners()[0].keyPressed(downKey);
        assertEquals(1, gp.ui.commandNum);

        KeyEvent upKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_W, 'W');
        gp.getKeyListeners()[0].keyPressed(upKey);
        assertEquals(0, gp.ui.commandNum);
    }

    /**
     * Tests when the game is in lose state that the player can enter on the retry option and the game will go back to play state (running game screen)
     */
    @Test
    void KeyHandlerUIandGamePanelInteractionsWhenLoseStateandRetryEntered(){
        gp.gameState = gp.playState;
        mC.life = 0;
        mC.update();
        assertEquals(gp.loseState, gp.gameState);
        assertEquals(0, gp.ui.commandNum);
        KeyEvent enterKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_ENTER, 'e');
        gp.getKeyListeners()[0].keyPressed(enterKey);
        assertEquals(gp.playState, gp.gameState);
    }

    /**
     * Tests when the game is in lose state that the player can go to the exit option and press enter and the game will go to the title state (title screen)
     */
    @Test
    void KeyHandlerUIandGamePanelInteractionsWhenLoseStateandExitEntered(){
        gp.gameState = gp.playState;
        mC.life = 0;
        mC.update();
        assertEquals(gp.loseState, gp.gameState);
        KeyEvent downKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_S, 'S');
        gp.getKeyListeners()[0].keyPressed(downKey);
        assertEquals(1, gp.ui.commandNum);
        KeyEvent enterKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_ENTER, 'e');
        gp.getKeyListeners()[0].keyPressed(enterKey);
        assertEquals(gp.titleState, gp.gameState);
    }

}

