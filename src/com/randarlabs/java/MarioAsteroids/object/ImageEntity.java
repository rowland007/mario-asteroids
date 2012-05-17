package com.randarlabs.java.MarioAsteroids.object;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.geom.AffineTransform;

public class ImageEntity extends BaseGameEntity {
	protected Image image;
	protected Window w;
	//protected ScreenManager sm;
	protected AffineTransform at;
	protected Graphics2D g;
	
	public ImageEntity(Window w) {
		this.w = w;
		setImage(null);
		setAlive(true);
	}
	
	public Image getImage() {
		try {
			return image;
		} catch(Exception e) {
			System.out.println("Could not ImageEntity.getImage()");
		}
		return null;
	}
	
	public void setImage(Image file) {
		//Window w = sm.getFullScreenWindow();
		this.image = file;
		double x = w.getSize().width / 2 - width() / 2;
		double y = w.getSize().height / 2 - height() / 2;
		at = AffineTransform.getTranslateInstance(x, y);
	}
	
	public int width() {
		if(image != null) {
			return image.getWidth(null);
		}else{
			return 0;
		}
	}
	
	public int height() {
		if(image != null) {
			return image.getHeight(null);
		}else{
			return 0;
		}
	}
	
	public double getCenterX() {
		return getX() + width() / 2;
	}
	
	public double getCenterY() {
		return getY() + height() / 2;
	}
	
	public void setGraphics(Graphics2D g) {
		this.g = g;
	}
	
//	public void load(String filename) {
		//Window w = sm.getFullScreenWindow();
	//	image = new ImageIcon(filename).getImage();
	//	while(getImage().getWidth(w) <= 0);
	//	double x = w.getSize().width / 2 - width() / 2;
	//	double y = w.getSize().height / 2 - height() / 2;
	//	at = AffineTransform.getTranslateInstance(x, y);
	//}
	
	public void transform() {
		try {
			at.setToIdentity();
			at.translate((int)getX() + width() / 2, (int)getY() + height() / 2);
			at.rotate(Math.toRadians(getFaceAngle()));
			at.translate(-width() / 2, -height() / 2);
		} catch(Exception e) {
			System.out.println("could not ImageEntity.transform()");
		}
	}
	
	public void draw() {
		//Window w = sm.getFullScreenWindow();
		g.drawImage(getImage(), at, w);
	}
	
	public Rectangle getBounds() {
		Rectangle r;
		r = new Rectangle((int)getX(), (int)getY(), width(), height());
		return r;
	}
}
