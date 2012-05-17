package com.randarlabs.java.MarioAsteroids.core;


import java.awt.*;

public abstract class Core {
	private static final DisplayMode modes[] = {
		new DisplayMode(1366,768,32,0),
		new DisplayMode(1366,768,24,0),
		new DisplayMode(1366,768,16,0),
		new DisplayMode(1360,768,32,0),
		new DisplayMode(1360,768,24,0),
		new DisplayMode(1360,768,16,0),
		new DisplayMode(1280,768,32,0),
		new DisplayMode(1280,768,24,0),
		new DisplayMode(1280,768,16,0),
		new DisplayMode(1280,720,32,0),
		new DisplayMode(1280,720,24,0),
		new DisplayMode(1280,720,16,0),
		new DisplayMode(1280,600,32,0),
		new DisplayMode(1280,600,24,0),
		new DisplayMode(1280,600,16,0),
		new DisplayMode(1024,768,32,0),
		new DisplayMode(1024,768,24,0),
		new DisplayMode(1024,768,16,0),
		new DisplayMode(800,600,32,0),
		new DisplayMode(800,600,24,0),
		new DisplayMode(800,600,16,0),
		new DisplayMode(640,480,32,0),
		new DisplayMode(640,480,24,0),
		new DisplayMode(640,480,16,0),
	};
	private boolean running;
	protected ScreenManager sm;
	protected Graphics2D g;
	
	//stop method
	public void stop() {
		running = false;
	}
	
	public void run() {
		try {
			init();
			gameLoop();
		}finally {
			sm.restoreScreen();
		}
	}
	
	public void init() {
		sm = new ScreenManager();
		DisplayMode dm = sm.findFirstCompatibleMode(modes);
		sm.setFullScreen(dm);
		
		Window w = sm.getFullScreenWindow();
		w.setFont(new Font("Arial", Font.PLAIN, 20));
		w.setBackground(Color.BLACK);
		w.setForeground(Color.WHITE);
		running = true;
	}
	
	public void gameLoop() {
		long startTime = System.currentTimeMillis();
		long cumlTime = startTime;
		
		while(running) {
			long timePassed = System.currentTimeMillis() - cumlTime;
			cumlTime += timePassed;
			
			update(timePassed);
			
			g = sm.getGraphics();
			draw(g);
			g.dispose();
			sm.update();
			
			try{
				Thread.sleep(20);
			}catch(Exception ex) {}
		}
	}
	
	/*	public void start() {
	gameloop = new Thread(this);
	gameloop.start();
}

public void run() {
	Thread t = Thread.currentThread();
	
	while(t == gameloop) {
		try{
			gameUpdate();
			Graphics2D g = sm.getGraphics();
			draw(g);
			g.dispose();
			sm.update();
			Thread.sleep(20);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public void stop() {
	gameloop = null;
}*/
	
	public void update(long timePassed) {
		
	}
	
	public abstract void draw(Graphics2D g);
}
