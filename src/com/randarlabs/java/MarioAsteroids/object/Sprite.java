package com.randarlabs.java.MarioAsteroids.object;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Sprite extends Object {
	private ImageEntity entity;
	protected MyPoint2D pos;
	protected MyPoint2D vel;
	protected double rotRate;
	protected int currentState;
	
	public Sprite(Window w, Graphics2D g) {
		entity = new ImageEntity(w);
		entity.setGraphics(g);
		entity.setAlive(false);
		pos = new MyPoint2D(0, 0);
		vel = new MyPoint2D(0, 0);
		rotRate = 0.0;
		currentState = 0;
	}
	
	//public void load(String filename) {
//		entity.load(filename);
//	}
	
	public void transform() {
		entity.setX(pos.X());
		entity.setY(pos.Y());
		entity.transform();
	}
	
	public AffineTransform getTransform() {
		return entity.at;
	}
	
	public void draw() {
		entity.g.drawImage(entity.getImage(), entity.at, null);// entity.w);
	}
	
	public void drawBounds(Color c) {
		entity.g.setColor(c);
		entity.g.draw(getBounds());
	}
	
	public void updatePosition() {
		pos.setX(pos.X() + vel.X());
		pos.setY(pos.Y() + vel.Y());
	}
	
	public double rotationRate() {
		return rotRate;
	}
	
	public void setRotationRate(double rate) {
		rotRate = rate;
	}
	
	public void updateRotation() {
		setFaceAngle(faceAngle() + rotRate);
		if(faceAngle() < 0) {
			setFaceAngle(360 - rotRate);
		}
		else if(faceAngle() > 360) {
			setFaceAngle(rotRate);
		}
	}
	
	public int state() {
		return currentState;
	}
	
	public void setState(int state) {
		currentState = state;
	}
	
	public Rectangle getBounds() {
		return entity.getBounds();
	}
	
	public MyPoint2D position() {
		return pos;
	}
	
	public void setPosition(MyPoint2D pos) {
		this.pos = pos;
	}
	
	public MyPoint2D velocity() {
		return vel;
	}
	
	public void setVelocity(MyPoint2D vel) {
		this.vel = vel;
	}
	
	public MyPoint2D center() {
		return(new MyPoint2D(entity.getCenterX(), entity.getCenterY()));
	}
	
	public boolean alive() {
		return entity.isAlive();
	}
	
	public void setAlive(boolean alive) {
		entity.setAlive(alive);
	}
	
	public double faceAngle() {
		return entity.getFaceAngle();
	}
	
	public void setFaceAngle(double angle) {
		entity.setFaceAngle(angle);
	}
	
	public void setFaceAngle(float angle) {
		entity.setFaceAngle(angle);
	}
	
	public void setFaceAngle(int angle) {
		entity.setFaceAngle(angle);
	}
	
	public double moveAngle() {
		return entity.getMoveAngle();
	}
	
	public void setMoveAngle(double angle) {
		entity.setMoveAngle(angle);
	}
	
	public void setMoveAngle(float angle) {
		entity.setMoveAngle(angle);
	}
	
	public void setMoveAngle(int angle) {
		entity.setMoveAngle(angle);
	}
	
	public int imageWidth() {
		return entity.width();
	}
	
	public int imageHeight() {
		return entity.height();
	}
	
	public boolean collidesWith(Rectangle rect) {
		return (rect.intersects(getBounds()));
	}
	
	public boolean collidesWith(Sprite sprite) {
		return (getBounds().intersects(sprite.getBounds()));
	}
	
	public boolean collidesWith(MyPoint2D point) {
		return (getBounds().contains(point.X(), point.Y()));
	}
	
	public Window window() {
		return entity.w;
	}
	
	public Graphics2D graphics() {
		return entity.g;
	}
	
	public Image image() {
		return entity.image;
	}
	
	public void setImage(Image image) {
		entity.setImage(image);
	}
}