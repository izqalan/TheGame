package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public int bounds = 0;
	public static float HEALTH = 100;
	
	private int score = 0;
	private int level = 1;
	
	private float greenValue = 255;
	
	public void tick()
	{
		HEALTH =  Game.clamp(HEALTH, 0, 100 + (bounds / 2));
		greenValue = HEALTH*2;
		greenValue = Game.clamp(greenValue, 0, 255);
		
		
		score++;
	}
	public void render(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect(15,15, 200 + bounds, 22);
		//						RGB VALUE
		g.setColor(new Color(75, (int)greenValue, 0));
		g.fillRect(15,15, (int) (HEALTH*2), 22);
		g.setColor(Color.white);
		g.drawRect(15,15, 200 + bounds, 22);
		//	SPAWN & LEVEL
		g.drawString("Score: "+ score, 15, 64);
		g.drawString("Level: "+ level, 15, 80);
		g.drawString("[space] for shop", 15, 90);
	}
	
	public void score(int score)
	{
		this.score = score;
	}
	
	public int getScore()
	{
		return score;
	}
	
	
	public int getLevel()
	{
		return level;
	}
	public void setLevel(int level)
	{
		this.level  = level;
	}
	
	
	
	
	
}
