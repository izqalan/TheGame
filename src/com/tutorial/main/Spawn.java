package com.tutorial.main;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private int scoreKeep = 0;
	private Random r = new Random();
	
	public Spawn(Handler handler, HUD hud)
	{
		this.handler= handler;
		this.hud = hud;
	}
	
	public void tick()
	{
		scoreKeep++;
		//	score will resets to 0 when it reaches 1000 and gain level
		if (scoreKeep >= 300)
		{
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			//	will spawn an enemy every 300 pts
			//handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			if(hud.getLevel() == 2)
			{
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}
			else if(hud.getLevel() == 3)
			{
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}
			else if(hud.getLevel() == 4)
			{
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
			}
			else if(hud.getLevel() == 5)
			{
				handler.clearEnemy();
				handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 42, -120, ID.EnemyBoss, handler));
			}
			
		}
			
	}
	
}
