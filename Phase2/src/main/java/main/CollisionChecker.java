package main;

import MainCharacter.Mobile;

/**
 * This class checks the characters' position on the map and sees if it is touching any tiles that you should not walk through
 * @author Connor
 */
public class CollisionChecker {
    GamePanel gp;

    /**
     * Constructor that takes in the main GamePanel
     * @param gp GamePanel that will contain the game
     */
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    /**
     * This will check the tiles the character is on and make sure the character will not walk through walls or blocked areas
     * @param character The character that will be checked. Could be enemy or main character
     */
    public void checkTile(Mobile character){
        int characterLeftX = character.x + character.solidArea.x;
        int characterRightX = character.x + character.solidArea.x + character.solidArea.width;
        int characterTopY = character.y + character.solidArea.y;
        int characterBottomY = character.y + character.solidArea.y + character.solidArea.height;

        int characterLeftCol = characterLeftX/gp.tileSize;
        int characterRightCol = characterRightX/gp.tileSize;
        int characterTopRow = characterTopY/gp.tileSize;
        int characterBottomRow = characterBottomY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(character.direction){
            case "up":
                characterTopRow = (characterTopY - character.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[characterLeftCol][characterTopRow];
                tileNum2 = gp.tileM.mapTileNum[characterRightCol][characterTopRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    character.collisionOn = true;
                }
                break;
            case "down":
                characterBottomRow = (characterBottomY + character.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[characterLeftCol][characterBottomRow];
                tileNum2 = gp.tileM.mapTileNum[characterRightCol][characterBottomRow];
                if(gp.tileM.tile[tileNum1].collision|| gp.tileM.tile[tileNum2].collision){
                    character.collisionOn = true;
                }
            case "left":
                characterLeftCol = (characterLeftX - character.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[characterLeftCol][characterTopRow];
                tileNum2 = gp.tileM.mapTileNum[characterLeftCol][characterBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    character.collisionOn = true;
                }
                break;
            case "right":
                characterRightCol = (characterRightX + character.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[characterRightCol][characterTopRow];
                tileNum2 = gp.tileM.mapTileNum[characterRightCol][characterBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    character.collisionOn = true;
                }
                break;
        }

    }
}
