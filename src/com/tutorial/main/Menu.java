package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	
	public Menu(Game game, Handler handler)
	{
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		// if user click "play" the state will change
		if(mouseOver(mx, my, 210, 150, 200, 64))
		{
			game.gameState = STATE.Game;
			handler.addObject(new Player(Game.WIDTH/2-100, Game.HEIGHT/2, ID.Player, handler));
			handler.addObject(new BasicEnemy((Game.WIDTH / 2) - 42, -120, ID.BasicEnemy, handler));
		}
		//exit button
		if(mouseOver(mx, my, 500, 375, 100, 50))
		{
			System.exit(1);
		}
		//github button
		if(mouseOver(mx, my,210, 250, 200, 64))
		{
			
		}
			
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{
		//boundry of the Menu boxes
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		
		// menu front-end
		Font fnt = new Font("impact", 1, 50);
		Font fnt2 = new Font("courier", 1, 25);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Menu", 240, 70);
		
		g.setFont(fnt2);
		g.drawString("Play", 280, 190);
		g.drawRect(210, 150, 200, 64);
		
		g.drawString("Github", 270, 290);
		g.drawRect(210, 250, 200, 64);
		
		g.drawString("Quit", 520, 405);
		g.drawRect(500, 375, 100, 50);
	}
}