package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {
	
	Random r = new Random();
	Handler handler;
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	// collision detection
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 30, 30);
	}
	
	

	public void tick() {
		x += velX;
		y += velY;
		// game border
		x = Game.clamp(x, 0, Game.WIDTH - 35);
		y = Game.clamp(y, 0, Game.HEIGHT - 59);
		collision();
	}
	private void collision()
	{
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject =  handler.object.get(i);
			
			if(tempObject.getId() ==  ID.BasicEnemy || tempObject.getId() == ID.FastEnemy)
			{
				//collision
				if (getBounds().intersects(tempObject.getBounds()))
				{
					HUD.HEALTH -= 5;
				}
			}
			
		}
	}

	public void render(Graphics g) 
	{
		//Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 30, 30);
		
	}

}
