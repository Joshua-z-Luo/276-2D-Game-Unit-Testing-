package main;

import main.GamePanel;
import main.KeyHandler;
import Entities.MainCharacterTV;
import main.UI;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;


public class KeyHandlerTest {
    public static boolean upPressed, downPressed, leftPressed, rightPressed; // do we need?
    public static GamePanel gp = new GamePanel();
    public static KeyHandler kH = new KeyHandler(gp);
    public static MainCharacterTV mC = new MainCharacterTV(gp,kH);

    @Test
    void MainCharacterMovesDownWhenDownKeyPressed(){
        // fill in

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
