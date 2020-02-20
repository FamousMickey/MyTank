package com.gzw.tank.abstractfactory;

import java.awt.Color;
import java.awt.Graphics;

import com.gzw.tank.ResourceMgr;
import com.gzw.tank.TankFrame;

public class RectExplode extends BaseExplode {

	final static int WIDTH = 35,HEIGHT = 50;
	private int x,y;
	
	private TankFrame tf = null;
	
	private int pictureNum = 0;
	
	private int step = 0;
	
	public RectExplode(int x, int y, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;
	}
	
	@Override
	public void paint(Graphics g) {
//		g.drawImage(ResourceMgr.explodes[pictureNum], this.x, this.y, null);
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillRect(this.x, this.y, 10 * step, 10 * step);
		
		step++;
		if (step >= 5) {
			tf.explodes.remove(this);
		}
		
		g.setColor(c);
	}

}
