package com.randarlabs.java.MarioAsteroids.user;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.randarlabs.java.MarioAsteroids.core.BaseShape;

public class Fireball extends BaseShape {
	private Image fireball = new ImageIcon("res\\Smb1_bowser_flamef.png").getImage();
	
	public Rectangle getBounds() {
		Rectangle r;
		r = new Rectangle((int)getX(), (int)getY(), fireball.getWidth(null) / 2, fireball.getHeight(null) / 2);
		return r;
	}
	
	public Fireball() {
		setIcon(fireball);
		setCenterX(fireball.getWidth(null) / 2);
		setCenterY(fireball.getHeight(null) / 2);
		setAlive(false);
	}
}