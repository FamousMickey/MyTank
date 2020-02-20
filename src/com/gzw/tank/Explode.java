package com.gzw.tank;

import java.awt.Graphics;

import com.gzw.tank.abstractfactory.BaseExplode;

public class Explode extends BaseExplode {
	public final static int WIDTH = 35,HEIGHT = 50;
	private int x,y;
	
	private TankFrame tf = null;
	
	private int pictureNum = 0;
	
	public Explode(int x, int y, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[pictureNum], this.x, this.y, null);
		pictureNum++;
		if (pictureNum >= ResourceMgr.explodes.length) {
			tf.explodes.remove(this);
		}
	}
}
