package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBullet extends GameObject{
	
	private Handler handler;
	Random r = new Random();
	public EnemyBullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = (r.nextInt(5 - -5)+ -5);
		velY = 8;
	}
	// collision detection
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 9, 9);
	}
	
	public void tick()
	{
		x += velX;
		y += velY;
		//bouncing BasicEnemy
		//if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		//if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		// remove from the game
		if(y >= Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.orange, 16, 16, 0.04f, handler));
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.orange);
		g.fillRect((int)x, (int)y, 9, 9);
	}

}
