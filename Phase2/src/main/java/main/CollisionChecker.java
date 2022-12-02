package main;

import Entities.MovingObject;

/**
 * This class checks the characters' position on the map and sees if it is touching any tiles that you should not walk through
 * @author Connor, Hayato, Rose, Joshua
 */
public class CollisionChecker {
    private static CollisionChecker collisionChecker = null;
    GamePanel gp;

    /**
     * Constructor that takes in the main GamePanel
     * @param gp GamePanel that will contain the game
     *           Currently public for testing purpose(I don't know if that is legit ting to do)
     */
//    protected CollisionChecker(GamePanel gp){
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    /**
     * Instance method that implements the singleton creational pattern
     * @param gp GamePanel that will contain the game
     * @return the single instance of CollisionChecker
     */
    public static CollisionChecker instance(GamePanel gp){
        if(collisionChecker == null){
            collisionChecker = new CollisionChecker(gp);
        }
        return collisionChecker;
    }

    /**
     * This will check the tiles the character is on and make sure the character will not walk through walls or blocked areas
     * @param character The character that will be checked. Could be enemy or main character
     */
    public void checkWall(MovingObject character){
        int characterLeftX = character.x + character.solidArea.x;
        int characterRightX = character.x + character.solidArea.x + character.solidArea.width;
        int characterTopY = character.y + character.solidArea.y;
        int characterBottomY = character.y + character.solidArea.y + character.solidArea.height;

        int characterLeftCol = characterLeftX/gp.tileSize;
        int characterRightCol = characterRightX/gp.tileSize;
        int characterTopRow = characterTopY/gp.tileSize;
        int characterBottomRow = characterBottomY/gp.tileSize;

        int wallNum1, wallNum2;

        switch(character.direction){
            case "up":
                characterTopRow = (characterTopY - character.speed)/gp.tileSize;
                wallNum1 = gp.wallM.mapWallNum[characterLeftCol][characterTopRow];
                wallNum2 = gp.wallM.mapWallNum[characterRightCol][characterTopRow];
                if(gp.wallM.wall[wallNum1].collision || gp.wallM.wall[wallNum2].collision){
                    character.collisionOn = true;
                }
                break;
            case "down":
                characterBottomRow = (characterBottomY + character.speed)/gp.tileSize;
                wallNum1 = gp.wallM.mapWallNum[characterLeftCol][characterBottomRow];
                wallNum2 = gp.wallM.mapWallNum[characterRightCol][characterBottomRow];
                if(gp.wallM.wall[wallNum1].collision || gp.wallM.wall[wallNum2].collision){
                    character.collisionOn = true;
                }
                break;
            case "left":
                characterLeftCol = (characterLeftX - character.speed)/gp.tileSize;
                wallNum1 = gp.wallM.mapWallNum[characterLeftCol][characterTopRow];
                wallNum2 = gp.wallM.mapWallNum[characterLeftCol][characterBottomRow];
                if(gp.wallM.wall[wallNum1].collision || gp.wallM.wall[wallNum2].collision){
                    character.collisionOn = true;
                }
                break;
            case "right":
                characterRightCol = (characterRightX + character.speed)/gp.tileSize;
                wallNum1 = gp.wallM.mapWallNum[characterRightCol][characterTopRow];
                wallNum2 = gp.wallM.mapWallNum[characterRightCol][characterBottomRow];
                if(gp.wallM.wall[wallNum1].collision || gp.wallM.wall[wallNum2].collision){
                    character.collisionOn = true;
                }
                break;
        }


    }

    /**
     * Checks if a moving entity (enemy) has collided with anything
     * @param mobile The enemy being checked
     * @param player True if the player is being hit
     * @return Index of the enemy in the monster array
     */
    public int checkObject(MovingObject mobile, boolean player){
        int index = 999;

        for(int i = 0;i<gp.obj.length;i++){
            if(gp.obj[i]!=null){
                //Get MainCharacter.Entity's solid area position
                setSolidAreaOfMobile(mobile);
                //Get the Entities.object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].x + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].y + gp.obj[i].solidArea.y;

                switch(mobile.direction){
                    case "up":
                        mobile.solidArea.y -= mobile.speed;
                        if(mobile.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collisionOn==true){
                                mobile.collisionOn=true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        mobile.solidArea.y += mobile.speed;
                        if(mobile.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collisionOn==true){
                                mobile.collisionOn=true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        mobile.solidArea.x -= mobile.speed;
                        if(mobile.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collisionOn==true){
                                mobile.collisionOn=true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        mobile.solidArea.x += mobile.speed;
                        if(mobile.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collisionOn==true){
                                mobile.collisionOn=true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                }
                mobile.solidArea.x = mobile.solidAreaDefaultX;
                mobile.solidArea.y = mobile.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

            }
        }
        return index;
    }

    /**
     * Checks if the main character is colliding with any other moving objects (enemies)
     * @param mobile The main character
     * @param target Array of other moving objects (enemies)
     * @return The index of the enemy that was collided with
     */
    public int checkEntity(MovingObject mobile, MovingObject[] target){
        int index = 999;

        for(int i = 0;i<target.length;i++){
            if(target[i]!=null){
                //Get MainCharacter.Entity's solid area position
                setSolidAreaOfMobile(mobile);
                //Get the Entities.object's solid area position
                target[i].solidArea.x = target[i].x + target[i].solidArea.x;
                target[i].solidArea.y = target[i].y + target[i].solidArea.y;

                switch(mobile.direction){
                    case "up":
                        mobile.solidArea.y -= mobile.speed;
                        if(mobile.solidArea.intersects(target[i].solidArea)){
                            if(target[i]!=mobile){
                                mobile.collisionOn=true;
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        mobile.solidArea.y += mobile.speed;
                        if(mobile.solidArea.intersects(target[i].solidArea)){
                            if(target[i]!=mobile){
                                mobile.collisionOn=true;
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        mobile.solidArea.x -= mobile.speed;
                        if(mobile.solidArea.intersects(target[i].solidArea)){
                            if(target[i]!=mobile){
                                mobile.collisionOn=true;
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        mobile.solidArea.x += mobile.speed;
                        if(mobile.solidArea.intersects(target[i].solidArea)){
                            if(target[i]!=mobile){
                                mobile.collisionOn=true;
                                index = i;
                            }
                        }
                        break;
                }
                mobile.solidArea.x = mobile.solidAreaDefaultX;
                mobile.solidArea.y = mobile.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }
        }
        return index;
    }

    /**
     * Checks if the player is intersecting with a wall
     * @param mobile The main character
     */
    public void checkPlayer(MovingObject mobile){

        //Get MainCharacter.Entity's solid area position
        setSolidAreaOfMobile(mobile);
        //Get the Entities.object's solid area position
        gp.tvGuy.solidArea.x = gp.tvGuy.x + gp.tvGuy.solidArea.x;
        gp.tvGuy.solidArea.y = gp.tvGuy.y + gp.tvGuy.solidArea.y;

        switch(mobile.direction){
            case "up":
                mobile.solidArea.y -= mobile.speed;
                if(mobile.solidArea.intersects(gp.tvGuy.solidArea)){
                    gp.tvGuy.life = 0;
                    mobile.collisionOn=true;
                }
                break;
            case "down":
                mobile.solidArea.y += mobile.speed;
                if(mobile.solidArea.intersects(gp.tvGuy.solidArea)){
                    gp.tvGuy.life = 0;
                    mobile.collisionOn=true;
                }
                break;
            case "left":
                mobile.solidArea.x -= mobile.speed;
                if(mobile.solidArea.intersects(gp.tvGuy.solidArea)){
                    gp.tvGuy.life = 0;
                    mobile.collisionOn=true;

                }
                break;
            case "right":
                mobile.solidArea.x += mobile.speed;
                if(mobile.solidArea.intersects(gp.tvGuy.solidArea)){
                    gp.tvGuy.life = 0;
                    mobile.collisionOn=true;
                }
                break;
        }
        mobile.solidArea.x = mobile.solidAreaDefaultX;
        mobile.solidArea.y = mobile.solidAreaDefaultY;
        gp.tvGuy.solidArea.x = gp.tvGuy.solidAreaDefaultX;
        gp.tvGuy.solidArea.y = gp.tvGuy.solidAreaDefaultY;
    }

    /**
     * helper function to reduce duplicated codes
     * @param mobile
     */
    private void setSolidAreaOfMobile(MovingObject mobile){
        mobile.solidArea.x = mobile.x + mobile.solidArea.x;
        mobile.solidArea.y = mobile.y + mobile.solidArea.y;
    }
}

