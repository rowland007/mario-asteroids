package com.randarlabs.java.MarioAsteroids.user;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;

import javax.swing.ImageIcon;

import com.randarlabs.java.MarioAsteroids.object.MyPoint2D;
import com.randarlabs.java.MarioAsteroids.object.Sprite;

public class Mario {
	private Sprite mario;
	private MyPoint2D pos = new MyPoint2D(0, 0);
	private MyPoint2D vel = new MyPoint2D(0, 0);
	private Image marioIcon = new ImageIcon("res\\Smb1_mario.png").getImage();
	
	public void setX(double x) {
		pos.setX(x);
	}
	
	public void setX(float x) {
		pos.setX(x);
	}
	
	public void setX(int x) {
		pos.setX(x);
	}
	
	public double getX() {
		return pos.X();
	}
	
	public void setY(double y) {
		pos.setY(y);
	}
	
	public void setY(float y) {
		pos.setY(y);
	}
	
	public void setY(int y) {
		pos.setY(y);
	}
	
	public double getY() {
		return pos.Y();
	}
	
	public void setVelX(double x) {
		vel.setX(x);
	}
	
	public void setVelX(float x) {
		vel.setX(x);
	}
	
	public void setVelX(int x) {
		vel.setX(x);
	}
	
	public double getVelX() {
		return vel.X();
	}
	
	public void setImage(Image image) {
		mario.setImage(image);
	}
	
	
	
	public Mario(Window w, Graphics2D g) {
		mario = new Sprite(w, g);
		mario.setImage(marioIcon);
		mario.setAlive(true);
	}
}