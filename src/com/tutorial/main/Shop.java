package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter{
	
	Handler handler;
	HUD hud;
	
	private int bo1 = 200;	// box 1
	private int bo2 = 200;
	
	public Shop(Handler handler, HUD hud)
	{
		this.handler = handler;
		this.hud = hud;
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", 0, 48));
		g.drawString("SHOP", Game.WIDTH/2-100, 50);
		
		// box 1
		g.setFont(new Font("Arial", 0, 12));
		g.drawString("Upgrade Health", 110, 120);
		g.drawString("Price"+ bo1, 110, 140);
		g.drawRect(100, 100, 100, 80);
		
		// box 2
		g.setFont(new Font("Arial", 0, 12));
		g.drawString("Upgrade speed", 260, 120);
		g.drawString("Price"+ bo2, 260, 140);
		g.drawRect(250, 100, 100, 80);
	}
	
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		// box 1 x and y coordinates 
		if(mx >= 100 && mx <= 200)
		{
			if(my >= 100 && my <= 180)
			{
				if(hud.getScore() >= bo1)
				{
					hud.score(hud.getScore() - bo1);
					bo1 += 100;
					hud.bounds += 20;
					hud.HEALTH = (100 + (hud.bounds / 2));
					
				}
			}
		}
		
		// box 2 x and y coordinates 
		if(mx >= 250 && mx <= 350)
		{
			if(my >= 100 && my <= 180)
			{
				hud.score(hud.getScore() - bo2);
				bo2 += 100;
				handler.speed += 2;
			}
		}
		
	}
}
