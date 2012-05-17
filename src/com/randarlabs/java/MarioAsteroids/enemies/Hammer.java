package com.randarlabs.java.MarioAsteroids.enemies;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.randarlabs.java.MarioAsteroids.core.BaseShape;

public class Hammer extends BaseShape {
	//private int[] astX = { -20, -13, 0, 20, 22, 20, 12, 2, -10, -22, -16 };
	//private int[] astY = { 20, 23, 17, 20, 16, -20, -22, -14, -17, -20, -5 };
	private Image hammer = new ImageIcon("res\\33px-Smb1_hammer.png").getImage();
	
	protected double rotVel;
	public double getRotationVelocity() {
		return rotVel;
	}
	
	public void setRotationVelocity(double v) {
		rotVel = v;
	}
	
	public Rectangle getBounds() {
		Rectangle r;
		r = new Rectangle((int)getX(), (int)getY(), hammer.getWidth(null), hammer.getHeight(null));
		return r;
	}
	
	public Hammer() {
		setIcon(hammer);
		setCenterX(hammer.getWidth(null) / 2);
		setCenterY(hammer.getHeight(null) / 2);
		setAlive(true);
		setRotationVelocity(0.0);
	}
}
