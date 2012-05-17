package com.randarlabs.java.MarioAsteroids.core;

import java.awt.Image;
import java.awt.Point;

public class BaseShape {
	private Image i;
	private boolean alive;
	private double x;
	private double y;
	private double vX;
	private double vY;
	private double moveAngle;
	private double faceAngle;
	private Point center;
	
	public Image getIcon() {
		return i;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getVelX() {
		return vX;
	}
	
	public double getVelY() {
		return vY;
	}
	
	public double getMoveAngle() {
		return moveAngle;
	}
	
	public double getFaceAngle() {
		return faceAngle;
	}
	
	public void setIcon(Image i) {
		this.i = i;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void incX(double i) {
		this.x += i;
	}
	
	public void incY(double i) {
		this.y += i;
	}
	
	public void setVelX(double velX) {
		this.vX = velX;
	}
	
	public void setVelY(double velY) {
		this.vY = velY;
	}
	
	public void incVelX(double i) {
		this.vX += i;
	}
	
	public void incVelY(double i) {
		vY += i;
	}
	
	public void setFaceAngle(double angle) {
		faceAngle = angle;
	}
	
	public void incFaceAngle(double i) {
		faceAngle += i;
	}
	
	public void setMoveAngle(double angle) {
		this.moveAngle = angle;
	}
	
	public void incMoveAngle(double i) {
		this.moveAngle += i;
	}
	
	public int getCenterX() {
		return center.x;
	}
	
	public int getCenterY() {
		return center.y;
	}
	
	public void setCenterX(int x) {
		center.x = x;
	}
	
	public void setCenterY(int y) {
		center.y = y;
	}
	
	public BaseShape() {
		setIcon(null);
		setAlive(false);
		setX(0.0);
		setY(0.0);
		setVelX(0.0);
		setVelY(0.0);
		setMoveAngle(0.0);
		setFaceAngle(0.0);
		center = new Point();
		setCenterX(0);
		setCenterY(0);
	}
}
