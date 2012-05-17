package com.randarlabs.java.MarioAsteroids.object;

public class MyPoint2D extends Object {
	private double x;
	private double y;
	
	public MyPoint2D() {
		setX(0.0);
		setY(0.0);
	}
	
	public MyPoint2D(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public MyPoint2D(float x, float y) {
		setX(x);
		setY(y);
	}
	
	public MyPoint2D(double x, double y) {
		setX(x);
		setY(y);
	}
	
	public double X() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public double Y() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
