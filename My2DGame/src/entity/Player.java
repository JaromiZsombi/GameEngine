package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		worldX = gp.tileSize*23;
		worldY = gp.tileSize*21;
		speed = 4;
		direction = "down";
	}
	public void getPlayerImage() {
		try {
			
			fel1 = ImageIO.read(getClass().getResourceAsStream("/player/fel1.png"));
			fel2 = ImageIO.read(getClass().getResourceAsStream("/player/fel2.png"));
			le1 = ImageIO.read(getClass().getResourceAsStream("/player/le1.png"));
			le2= ImageIO.read(getClass().getResourceAsStream("/player/le2.png"));
			jobbra1 = ImageIO.read(getClass().getResourceAsStream("/player/jobbra1.png"));
			jobbra2 = ImageIO.read(getClass().getResourceAsStream("/player/jobbra2.png"));
			balra1 = ImageIO.read(getClass().getResourceAsStream("/player/balra1.png"));
			balra2 = ImageIO.read(getClass().getResourceAsStream("/player/balra2.png"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true) {
			
			//mozgás + irány szerinti hitbox
			if(keyH.upPressed == true) {
				direction = "up";
				solidArea.x = 22;
				solidArea.y = 8;
				solidArea.width = 4;
				solidArea.height = 32;
			}
			
			else if(keyH.downPressed == true) {
				direction = "down";
				solidArea.x = 22;
				solidArea.y = 8;
				solidArea.width = 4;
				solidArea.height = 32;
			}
			
			else if(keyH.leftPressed == true) {
				direction = "left";
				solidArea.x = 8;
				solidArea.y = 23;
				solidArea.width = 32;
				solidArea.height = 2;
			}
			
			else if(keyH.rightPressed == true) {
				direction = "right";
				solidArea.x = 8;
				solidArea.y = 23;
				solidArea.width = 32;
				solidArea.height = 2;
				
			}
			
			//Check tile collision
			
			collisionOn = false;
			gp.cChecker.checkTile(this);
			//collision false = nem mozog, collision true = mozog
			
			if(collisionOn == false) {
				
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		
	}
	public void draw(Graphics2D g2) {
		
//		g2.setColor(Color.white);	
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		//fel
		case "up":
			if(spriteNum == 1) {
				image = fel1;
			}
			if(spriteNum == 2) {
				image = fel2;
			}
			break;
		//le
		case "down":
			if(spriteNum == 1) {
				image = le1;
			}
			if(spriteNum == 2) {
				image = le2;
			}
			break;
		//balra	
		case "left":
			if(spriteNum == 1) {
				image = balra1;
			}
			if(spriteNum == 2) {
				image = balra2;
			}
			break;
		//jobbra	
		case "right":
			if(spriteNum == 1) {
				image = jobbra1;
			}
			if(spriteNum == 2) {
				image = jobbra2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}

}
