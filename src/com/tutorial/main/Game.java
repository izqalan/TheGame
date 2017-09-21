package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	
	private static final long serialVersionUID = 1550691097823471818L;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12*9;
	
	private Thread thread;
	private boolean running = false;
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	public enum STATE{
		Menu, Game, Pause
	};
	
	public STATE gameState = STATE.Menu;
	
	public Game() 
	{
		handler = new Handler();
		menu = new Menu(this, handler);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		new Window(WIDTH, HEIGHT, "My game", this);
		
		hud = new HUD();
		spawner = new Spawn(handler, hud);

		r = new Random();
		
		if(gameState == STATE.Game)
		{
			//Directly Adding Object onto the game
			handler.addObject(new BasicEnemy((Game.WIDTH / 2) - 42, -120, ID.BasicEnemy, handler));
			handler.addObject(new Player(WIDTH/2-100, HEIGHT/2, ID.Player, handler));
			//handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 42, -120, ID.EnemyBoss, handler));
			
		
		}
		
		
		
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//game loop
	public void run() {
		this.requestFocus(); //with this function I don't have to click the program to send KeyInput value
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;	//change to 30 for better performance
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta >= 1)
			{
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			
			if(System.currentTimeMillis()-timer > 1000)
			{
				timer += 1000;
				System.out.println("FPS: "+frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		if(gameState == STATE.Game) {
			hud.tick();
			spawner.tick();
		}
		else if(gameState == STATE.Menu) {
			menu.tick();
		}
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);
		
		if(gameState == STATE.Game)
			hud.render(g);
		else if(gameState == STATE.Menu) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	//game border
	public static float clamp(float var, float min, float max)
	{
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String[] args) {
		new Game();

	}

	

}
