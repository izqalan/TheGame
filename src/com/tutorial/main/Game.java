package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
//import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	
	private static final long serialVersionUID = 1550691097823471818L;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12*9;
	
	private Thread thread;
	private boolean running = false;
	//private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;
	public static boolean pause = false;
	
	public enum STATE{
		Menu, Game, End, Shop;
	};
	
	public STATE gameState = STATE.Menu;
	
	public Game() 
	{
		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler, hud);
		menu = new Menu(this, handler, hud, shop);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		AudioPlayer.load();	// load sound but not playing it yet
		AudioPlayer.getMusic("bgm").loop(1, 0.03f);;	// the music will play now (pitch, volume)
		
		
		
		new Window(WIDTH, HEIGHT, "My game", this);
		
		spawner = new Spawn(handler, hud);

		//r = new Random();
		
		if(gameState == STATE.Game)
		{
			//Directly Adding Object onto the game
			//handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));

			//handler.addObject(new Player(WIDTH/2-100, HEIGHT/2, ID.Player, handler));
			//handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 42, -120, ID.EnemyBoss, handler));
		}
		/*else
		{
			// FX for menu screen
			for(int i = 0; i < 10; i++) {
				handler.addObject(new MenuFx(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuFx, handler));
			}
				
		}*/
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
		
		
		//Game state behavior
		if(gameState == STATE.Game) {
			
			if(!pause)
			{
				hud.tick();
				spawner.tick();
				handler.tick();
			
				if(HUD.HEALTH <= 0)
				{
					handler.object.clear();
					HUD.HEALTH = 100;
					hud.setLevel(1);
					hud.score(0);
					gameState = STATE.End;	
				}
			}
		}
		else if(gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
			handler.tick();
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

		
		// PAUSED 
		if(pause)
		{
			g.setColor(Color.white);
			g.drawString("Game Paused", 300, 300);
		}
		//	Game state behavior
		if(gameState == STATE.Game) {
			hud.render(g);
			handler.render(g);
		}
		else if(gameState == STATE.Menu || gameState == STATE.End)
		{
			menu.render(g);
			handler.render(g);
		}
		else if(gameState == STATE.Shop)
		{
			shop.render(g);
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
