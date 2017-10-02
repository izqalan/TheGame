package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.tutorial.main.Game.STATE;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
		
		for(int i = 0; i < 4 ; i++)
		{
			keyDown[i] = false;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player)
			{
				
				if (key == KeyEvent.VK_W) { tempObject.setVelY(-handler.speed); keyDown[0] = true;}
				if (key == KeyEvent.VK_S) { tempObject.setVelY(handler.speed); keyDown[1] = true;}
				if (key == KeyEvent.VK_A) { tempObject.setVelX(-handler.speed); keyDown[2] = true;}
				if (key == KeyEvent.VK_D) { tempObject.setVelX(handler.speed); keyDown[3] = true;}
				
			}
			
			// pause the game
			if(key == KeyEvent.VK_ESCAPE)
			{
				if (game.gameState == STATE.Game)
				{
					if(Game.pause) 
						Game.pause = false;
					else 
						Game.pause = true;
				}
			}
			// Shop
			else if(key == KeyEvent.VK_SPACE)
			{
				if(game.gameState == STATE.Game)
					game.gameState = STATE.Shop;
				else if(game.gameState == STATE.Shop)
					game.gameState =STATE.Game;
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		//Player stop
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player)
			{
				if (key == KeyEvent.VK_W) keyDown[0] = true; //tempObject.setVelY(0);
				if (key == KeyEvent.VK_S) keyDown[1] = true; //tempObject.setVelY(0);
				if (key == KeyEvent.VK_A) keyDown[2] = true; //tempObject.setVelX(0);
				if (key == KeyEvent.VK_D) keyDown[3] = true; //tempObject.setVelX(0);
				
				//vertical movement
				if(keyDown[0] && keyDown[1])
					tempObject.setVelY(0);
				//Horizontal movement
				if(keyDown[2] && keyDown[3])
					tempObject.setVelX(0);
			}
			
		
		}
	}
	
}
