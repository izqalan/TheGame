package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject{
	
	private Handler handler;
	private int timer = 60;
	private int timer2 = 50;
	Random r = new Random();
	public EnemyBoss(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 2;
	}
	// collision detection
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 64, 64);
	}
	
	public void tick()
	{
		x += velX;
		y += velY;
		
		if (timer <= 0)
			velY = 0;
		else
			timer--;
		
		if(timer <= 0)timer2--;
		if(timer2 <= 0)
		{
			if(velX == 0) velX = 2;
			
			if(velX > 0)
				velX += 0.01f;
			else if(velX < 0)
				velX -= 0.01f;
			
			velX = Game.clamp(velX, -10, 10);
			
			int spawn =r.nextInt(10);
			if(spawn == 0)
				handler.addObject(new EnemyBullet((int) x + 42, (int) y + 42, ID.BasicEnemy, handler));
			
		}
		
		//bouncing BasicEnemy
		if(x <= 0 || x >= Game.WIDTH - 64) velX *= -1;
		
		
		
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.pink);
		g.fillRect((int)x, (int)y, 64, 64);
	}

}
