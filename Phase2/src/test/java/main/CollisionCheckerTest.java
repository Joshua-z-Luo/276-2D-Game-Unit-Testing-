package main;

import Entities.MainCharacterTV;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class CollisionCheckerTest {
    static GamePanel gp = new GamePanel();
    static KeyHandler kH = new KeyHandler(gp);
    static MainCharacterTV tvGuy =   new MainCharacterTV(gp,kH);
    static AssetSetter aSetter = new AssetSetter(gp);

    static CollisionChecker cChecker = new CollisionChecker(gp);

    @BeforeAll
    static void init(){
        //initiate monsters
        gp.setUpGame();
        gp.startGameThread();
        gp.gameState = gp.playState;

    }

    /**
     * Tests if the player can collide with a certain enemy
     */
    @Test
    void testCheckIfMainCharacterCollidesWithMonsterAt350350(){
        tvGuy.x = 350;
        tvGuy.y = 360;
        tvGuy.direction = "up";
        int result =  cChecker.checkEntity(tvGuy, gp.monster);
        //if result is 999, no collision
        //else, collision
        //So this assertion assume collision between tvGuy and a monster at 350, 350
        assertNotEquals(999,result);
    }

    /**
     * Tests if the player can collide with a certain enemy
     */
    @Test
    void testCheckIfMainCharacterCollidesWithMonsterAt150150(){
        tvGuy.x = 150;
        tvGuy.y = 160;
        tvGuy.direction = "up";
        int result =  cChecker.checkEntity(tvGuy, gp.monster);
        //if result is 999, no collision
        //else, collision
        //So this assertion assume collision between tvGuy and a monster at 350, 350
        assertNotEquals(999,result);

    }

    /**
     * Tests if the player can collide with a certain enemy
     */
    @Test
    void testCheckIfMainCharacterCollidesWithMonsterAt350100(){
        tvGuy.x = 350;
        tvGuy.y = 110;
        tvGuy.direction = "up";

        int result =  cChecker.checkEntity(tvGuy, gp.monster);

        assertNotEquals(999,result);

    }

    /**
     * Tests if the player can collide with a certain enemy
     */
    @Test
    void testCheckIfMainCharacterCollidesWithMonsterAt1000350(){
        tvGuy.x = 1000;
        tvGuy.y = 350;
        tvGuy.direction = "up";

        int result =  cChecker.checkEntity(tvGuy, gp.monster);

        assertNotEquals(999,result);

    }

    /**
     * Tests if the player can reach the door with 3 keycards
     */
    @Test
    void mainCharacterCanReachDoor(){
        tvGuy.keyCardCount = 3;
        tvGuy.x = 1050;
        tvGuy.y = 700;
        int result =  cChecker.checkObject(tvGuy, true);
        assertEquals(7, result);
    }

    /**
     * Tests if the player gets stuck when colliding with a wall going left
     */
    @Test
    void mainCharacterGetsStuckOnWallGoingLeft(){
        KeyEvent leftKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_A, 'A');
        gp.getKeyListeners()[0].keyPressed(leftKey);
        for(int i = 0; i < 100; i++) {
            gp.keyH.keyPressed(leftKey);
            gp.tvGuy.update();
        }

        assertTrue(gp.tvGuy.collisionOn);
    }

    /**
     * Tests if the player gets stuck when colliding with a wall going right
     */
    @Test
    void mainCharacterGetsStuckOnWallGoingRight(){
        gp.tvGuy.y = 600;
        KeyEvent rightKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_D, 'D');
        gp.getKeyListeners()[0].keyPressed(rightKey);
        for(int i = 0; i < 25; i++) {
            gp.keyH.keyPressed(rightKey);
            gp.tvGuy.update();
        }

        assertTrue(gp.tvGuy.collisionOn);
    }

    /**
     * Tests if the player gets stuck when colliding with a wall going up
     */
    @Test
    void mainCharacterGetsStuckOnWallGoingUp(){
        gp.tvGuy.x = 50;
        KeyEvent upKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_W, 'W');
        gp.getKeyListeners()[0].keyPressed(upKey);
        for(int i = 0; i < 100; i++) {
            gp.keyH.keyPressed(upKey);
            gp.tvGuy.update();
        }

        assertTrue(gp.tvGuy.collisionOn);
    }

    /**
     * Tests if the player gets stuck when colliding with a wall going down
     */
    @Test
    void mainCharacterGetsStuckOnWallGoingDown(){
        gp.tvGuy.x = 150;
        gp.tvGuy.y = 600;
        KeyEvent downKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_S, 'S');
        gp.getKeyListeners()[0].keyPressed(downKey);
        for(int i = 0; i < 100; i++) {
            gp.keyH.keyPressed(downKey);
            gp.tvGuy.update();
        }

        assertTrue(gp.tvGuy.collisionOn);
    }
}