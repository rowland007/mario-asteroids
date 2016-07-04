package com.randarlabs.java.MarioAsteroids.core;

import com.randarlabs.java.MarioAsteroids.audio.SoundClip;
import com.randarlabs.java.MarioAsteroids.object.MyPoint2D;
import com.randarlabs.java.MarioAsteroids.object.Sprite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;

public class MarioAsteroids extends Core implements KeyListener, Runnable {
	public static void main(String[] args) {
		new MarioAsteroids().run();
	}
	
	final static int ENEMIES = 5;
	final static int FIREBALLS = 6;
	
	private Image bg;
	private Image joe;
	private Image marioIcon;
	private Image hammerIcon;
	private Image fireballIcon;
	
	private MyPoint2D marioPoint = new MyPoint2D(0, 0);
	private MyPoint2D marioVel = new MyPoint2D(0, 0);
	private MyPoint2D[] hammerPoint = new MyPoint2D[ENEMIES];
	private MyPoint2D[] hammerVel = new MyPoint2D[ENEMIES];
	private MyPoint2D[] fireballPoint = new MyPoint2D[FIREBALLS];
	private MyPoint2D[] fireballVel = new MyPoint2D[FIREBALLS];
	
	private Sprite mario;
	private Sprite[] fireball = new Sprite[FIREBALLS];
	private Sprite[] hammer = new Sprite[ENEMIES];
	
	boolean showBounds = false;
	boolean debug = false;
	boolean joeMode = false;
	
	int frameRate = 0;
	int frameCount = 0;
	long startTime = System.currentTimeMillis();
	
	private int enemiesLeft;

	int currentFireball = 0;
	
	SoundClip fire = new SoundClip();
	SoundClip destroyed = new SoundClip();
	SoundClip marioDie = new SoundClip();
	SoundClip stageCleared = new SoundClip();
	SoundClip bgMusic = new SoundClip();
	
	//AffineTransform idenity = new AffineTransform();
	
	
	Random rand = new Random();
	
	public void init() {
		super.init();
		Window w = sm.getFullScreenWindow();
		w.addKeyListener(this);
		w.setFocusTraversalKeysEnabled(false);
		
		Graphics2D g = this.g;
		
		fire.load("res/raw/smb_bowserfire.wav");
		destroyed.load("res/raw/smb_breakblock.wav");
		marioDie.load("res/raw/smb_mariodie.wav");
		stageCleared.load("res/raw/smb_stage_clear.wav");
		bgMusic.load("res/raw/sm64_happy_message.wav");
		bgMusic.setLooping(true);
		
		bg = new ImageIcon("res/mario_pokemon.jpeg").getImage();
		marioIcon = new ImageIcon("res/Smb1_mario.png").getImage();
		hammerIcon = new ImageIcon("res/33px-Smb1_hammer.png").getImage();
		fireballIcon = new ImageIcon("res/Smb1_bowser_flamef.png").getImage();
		
		mario = new Sprite(w, g);
		mario.setImage(marioIcon);
		marioPoint.setX(w.getSize().width / 2 - mario.center().X());
		marioPoint.setY(w.getSize().height / 2 - mario.center().Y());
		mario.setPosition(marioPoint);
		mario.setAlive(true);
		
		
		for(int n = 0; n < FIREBALLS; n++) {
			fireball[n] = new Sprite(w, g);
			fireball[n].setImage(fireballIcon);
			fireballPoint[n] = new MyPoint2D(0, 0);
			fireballVel[n] = new MyPoint2D(0.3, 0.3);
			fireball[n].setPosition(fireballPoint[n]);
			fireball[n].setAlive(false);
		}
		
		for(int n = 0; n < ENEMIES; n++) {
			hammer[n] = new Sprite(w, g);
			hammer[n].setImage(hammerIcon);
			hammer[n].setRotationRate(rand.nextInt(5) + 1);
			hammerPoint[n] = new MyPoint2D((double)rand.nextInt(w.getSize().width) - 20, (double)rand.nextInt(w.getSize().height) - 20);
			hammer[n].setPosition(hammerPoint[n]);
			hammer[n].setMoveAngle(rand.nextInt(360));
			hammer[n].setFaceAngle(rand.nextInt(360));
			double ang = hammer[n].moveAngle();
			hammerVel[n] = new MyPoint2D(calcAngleMoveX(ang), calcAngleMoveY(ang));
			hammer[n].setVelocity(hammerVel[n]);
			hammer[n].setAlive(true);
			
			setEnemiesLeft(ENEMIES);
			
			bgMusic.play();
		}
	}
		
	private void gameUpdate(Graphics2D g) {
		Window w = sm.getFullScreenWindow();
		if(debug) {
			frameCount++;
			if(System.currentTimeMillis() > startTime + 1000) {
				startTime = System.currentTimeMillis();
				frameRate = frameCount;
				frameCount = 0;
			}
			g.setColor(Color.RED);
			g.drawString("DEBUG MODE", ((float)w.getWidth() / 5) * 2, 37.5f);
			g.drawString("Mario: " + Math.round(mario.center().X()) + ", " + Math.round(mario.center().Y()), 5, 15);
			g.drawString("Move Angle: " + Math.round(mario.moveAngle()) + 90, 5, 30);
			g.drawString("Face Angle: " + Math.round(mario.faceAngle()) + 90, 5, 45);
			g.drawString("Enemies: " + getEnemiesLeft(), 5, 60);
			g.drawString("FPS: " + frameRate, ((float)w.getWidth() / 6) * 5.5f, 15.0f);		
		}
		else {
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, w.getWidth() / 5, 20);
			
			g.setColor(Color.WHITE);
			g.drawString("Enemies: " + getEnemiesLeft(), 5, 18);
			if(showBounds) {
				g.drawString("SHOWING BOUNDRIES", (w.getWidth() / 5) * 2, 18);
			}
		}
		if(getEnemiesLeft() <= 0) {
			bgMusic.stop();
			debug = false;
			showBounds = false;
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, w.getSize().width, w.getSize().height);
			g.setColor(Color.GREEN);
			g.drawString("Congradulations! You've destoryed all the enemies", (w.getSize().width / 3) + (w.getSize().width / 20), w.getSize().height / 2);
			//stageCleared.play();
			mario.setAlive(false);
			marioPoint.setX(0);
			marioPoint.setY(0);
			mario.setFaceAngle(0);
			mario.setMoveAngle(0);
		}else {
			drawFireballs(g);
			drawMario(g);
			drawEnemies(g);
		}		
	}
	
	public void drawMario(Graphics2D g) {
		if(mario.alive()) {
			if(showBounds) {
				g.draw(mario.getBounds());
			}
			mario.transform();
			mario.getTransform().scale(0.5, 0.5);
			g.drawImage(mario.image(), mario.getTransform(), null);
		}
	}
	
	public void drawFireballs(Graphics2D g) {
		for(int n = 0; n < FIREBALLS; n++) {
			if(fireball[n].alive()) {
				if(showBounds) {
					g.draw(fireball[n].getBounds());
				}
				if(debug) {
					g.drawString(" " + n, (int)fireball[n].center().X(), (int)fireball[n].center().Y());
				}
				fireball[n].transform();
				fireball[n].getTransform().scale(0.5, 0.5);
				g.drawImage(fireball[n].image(), fireball[n].getTransform(), null);
			}
		}
	}
	
	public void drawEnemies(Graphics2D g) {
		for(int n = 0; n < ENEMIES; n++) {
			if(hammer[n].alive()) {
				if(showBounds) {
					g.draw(hammer[n].getBounds());
				}
				if(debug) {
					g.drawString(" " + n, (int)hammer[n].center().X(), (int)hammer[n].center().Y());
				}
				hammer[n].transform();
				g.drawImage(hammer[n].image(), hammer[n].getTransform(), null);
			}
		}
	}
	
	public void draw(Graphics2D g) {
		Window w = sm.getFullScreenWindow();
		g.drawImage(bg, 0, 0, w.getWidth() - 1, w.getHeight() - 1, null);
		gameUpdate(g);
	}
	
	public void update(long timePassed) {
		updateMario();
		updateFireballs();
		updateEnemies();
		checkCollisions();
	}
	
	public void updateMario() {
		Window w = sm.getFullScreenWindow();
		mario.updatePosition();
		
		if(marioPoint.X() < -10)
			marioPoint.setX(w.getSize().width + 10);
		else if(marioPoint.X() > w.getSize().width + 10)
			marioPoint.setX(-10);
		
		if(marioPoint.Y() < -10)
			marioPoint.setY(w.getSize().height + 10);
		else if(marioPoint.Y() > w.getSize().height + 10)
			marioPoint.setY(-10);
	}
	
	public void updateFireballs() {
		Window w = sm.getFullScreenWindow();
		for(int n = 0; n < FIREBALLS; n++) {
			if(fireball[n].alive()) {
				fireball[n].updatePosition();
				
				fireballPoint[n].setX(fireball[n].velocity().X() + fireball[n].position().X());
				fireballPoint[n].setY(fireball[n].velocity().X() + fireball[n].position().Y());
				
				if(fireball[n].position().X() < 0 || fireball[n].position().X() > w.getSize().width) {
					fireball[n].setAlive(false);
				}
				
				if(fireball[n].position().Y() < 0 || fireball[n].position().Y() > w.getSize().height) {
					fireball[n].setAlive(false);
				}
			}
		}
	}
	
	public void updateEnemies() {
		Window w = sm.getFullScreenWindow();
		for(int n = 0; n < ENEMIES; n++) {
			if(hammer[n].alive()) {
				hammer[n].updatePosition();
				
				hammerPoint[n].setX(hammer[n].velocity().X() + hammer[n].position().X());
				hammerPoint[n].setY(hammer[n].velocity().Y() + hammer[n].position().Y());
				
				if(hammer[n].position().X() < -20)
					hammerPoint[n].setX(w.getSize().width + 20);
				else if(hammer[n].position().X() > w.getSize().width + 20)
					hammerPoint[n].setX(-20);
				
				if(hammer[n].position().Y() < -20)
					hammerPoint[n].setY(w.getSize().height + 20);
				else if(hammer[n].position().Y() > w.getSize().height + 20)
					hammerPoint[n].setY(-20);
				
				hammer[n].updateRotation();
				hammer[n].setMoveAngle(hammer[n].rotationRate()+ hammer[n].faceAngle());
				
				if(hammer[n].moveAngle() < 0)
					hammer[n].setMoveAngle(360 - hammer[n].rotationRate());
				else if(hammer[n].moveAngle() > 360)
					hammer[n].setMoveAngle(hammer[n].rotationRate());
			}
		}
	}
	
	public void checkCollisions() {
		Window w = sm.getFullScreenWindow();
		
		for(int m = 0; m < ENEMIES; m++) {
			if(hammer[m].alive()) {
				for(int n = 0; n < FIREBALLS; n++) {
					if(fireball[n].alive()) {
						if(hammer[m].getBounds().intersects(fireball[n].getBounds())) {
						//if(ham[m].getBounds().contains(fireball[n].getX(), fireball[n].getY())) {
							fireball[n].setAlive(false);
							//hammer[m].setAlive(false);
							destroyed.play();
							if(!hammer[m].alive()) {
								setEnemiesLeft(getEnemiesLeft() - 1);
							}
							continue;
						}
					}
				}
				// TODO
				for(int n = 0; n < ENEMIES; n++) {
					if(hammer[n].alive()) {
						if(hammer[m].getBounds().intersects(hammer[n].getBounds())) {
							double angN = hammer[n].moveAngle();
							double angM = hammer[m].moveAngle();
							angN += 180;
							angM += 180;
							hammerVel[n].setX(calcAngleMoveX(angN));
							hammerVel[n].setY(calcAngleMoveY(angN));
							hammerVel[m].setX(calcAngleMoveX(angM));
							hammerVel[m].setY(calcAngleMoveY(angM));
							continue;
						}
					}
				}
				
				if(hammer[m].getBounds().intersects(mario.getBounds())) {
					//hammer[m].setAlive(false);
					destroyed.play();
					if(!hammer[m].alive()) {
						setEnemiesLeft(getEnemiesLeft() - 1);
					}
					marioDie.play();
					marioPoint.setX(w.getSize().width / 2);
					marioPoint.setY(w.getSize().height / 2);
					mario.setFaceAngle(0);
					marioVel.setX(0);
					marioVel.setY(0);
					mario.setVelocity(marioVel);
					continue;
				}
			}
		}
	}
	
	public void setEnemiesLeft(int i) {
		if(i >= 0)
			enemiesLeft = i;
		
	}

	public int getEnemiesLeft() {
		return enemiesLeft;
	}

	public void keyReleased(KeyEvent e) {
		e.consume();
	}
	public void keyTyped(KeyEvent e) {
		e.consume();
	}
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		switch(keyCode) {
		case KeyEvent.VK_LEFT:
			mario.updateRotation();
			mario.setRotationRate(-5);
			//mario.updateRotation();
			e.consume();
			break;
		case KeyEvent.VK_RIGHT:
			mario.updateRotation();
			mario.setRotationRate(5);
			//mario.updateRotation();
			e.consume();
			break;
		case KeyEvent.VK_UP:
			mario.setMoveAngle(mario.faceAngle());
			marioVel.setX(1 + mario.moveAngle() * 0.1);
			marioVel.setY(1 + mario.moveAngle() * 0.1);
			mario.setVelocity(marioVel);
			e.consume();
			break;
		case KeyEvent.VK_CONTROL:
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_SPACE:
			currentFireball++;
			if(currentFireball > FIREBALLS - 1)
				currentFireball = 0;
			fireball[currentFireball].setAlive(true);
			
			fire.play();
			
			fireballPoint[currentFireball].setX(mario.center().X());
			fireballPoint[currentFireball].setY((mario.center().Y()) - fireball[currentFireball].center().Y());
			fireball[currentFireball].setFaceAngle(mario.faceAngle());
			fireball[currentFireball].setMoveAngle(mario.moveAngle());
			
			double angle = fireball[currentFireball].moveAngle();
			double svx = mario.velocity().X();
			double svy = mario.velocity().Y();
			fireballVel[currentFireball].setX(svx + calcAngleMoveX(angle) * 2);
			fireballVel[currentFireball].setY(svy + calcAngleMoveY(angle) * 2);
			fireball[currentFireball].setVelocity(fireballVel[currentFireball]);
			e.consume();
			break;
		case KeyEvent.VK_ESCAPE:
			stop();
			e.consume();
			break;
		case KeyEvent.VK_B:
			if(showBounds == false)
				showBounds = true;
			else
				showBounds = false;
			e.consume();
			break;
		case KeyEvent.VK_D:
			if(debug == false)
			{
				debug = true;
				showBounds = true;
			}
			else
			{
				debug = false;
				showBounds = false;
			}
			e.consume();
			break;
		case KeyEvent.VK_J:
			joeMode = true;
			joe = new ImageIcon("res\\Smb1_super_luigi.png").getImage();
			mario.setImage(joe);
			break;
			default:
				e.consume();
				break;
		}
	}
	
	public double calcAngleMoveX(double angle) {
		return (double)(Math.cos(angle * Math.PI / 180));
	}
	
	public double calcAngleMoveY(double angle) {
		return (double)(Math.sin(angle * Math.PI / 180));
	}
}