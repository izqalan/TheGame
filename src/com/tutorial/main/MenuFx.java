package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuFx extends GameObject{
	
	private Handler handler;
	private Random r;
	private Color col;
	
	public MenuFx(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 5;
		velY = 10;
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}
	// collision detection
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick()
	{
		x += velX;
		y += velY;
		//bouncing BasicEnemy
		if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		handler.addObject(new Trail((int)x,(int) y, ID.Trail, col, 16, 16, 0.04f, handler));
		
	}
	
	public void render(Graphics g)
	{
		
		g.setColor(col);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
