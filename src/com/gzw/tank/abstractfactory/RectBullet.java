package com.gzw.tank.abstractfactory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.gzw.tank.Dir;
import com.gzw.tank.Explode;
import com.gzw.tank.Group;
import com.gzw.tank.ResourceMgr;
import com.gzw.tank.Tank;
import com.gzw.tank.TankFrame;
import com.gzw.tank.abstractfactory.BaseBullet;

public class RectBullet extends BaseBullet{
	private static final int SPEED = 3;
	private static int WIDTH = 30, HEIGHT = 30;
	
	private int x,y;
	private Dir dir;
	private Group group = Group.BAD;
	
	private TankFrame tf = null;
	
	private boolean living = true;
	
	//×Óµ¯Î»ÖÃ¾ØÐÎ
	private Rectangle rect = new Rectangle();
	
	public RectBullet(int x,int y,Dir dir, Group group, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
		this.group  = group;
		
		this.rect.height = RectBullet.HEIGHT;
		this.rect.width = RectBullet.WIDTH;
		
		tf.bullets.add(this);
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void paint(Graphics g) {
		if (!living) {
			tf.bullets.remove(this);
			return;
		}
		
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.fillRect(this.x, this.y, 20, 20);
		g.setColor(c);
		
		move();
	}
	
	private void move() {
		
		switch (dir) {
		case LEFT:
			x -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		default:
			break;
		}
		
		this.rect.x = this.x; 
		this.rect.y = this.y;
		
		if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
			this.living = false;
		}
	}

	public void collideWith(BaseTank tank) {
		
		if (this.group == tank.getGroup()) return;
		
		if (this.rect.intersects(tank.rect)) {
			tank.die();
			this.die();
			int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
			int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
//			tf.explodes.add(new Explode(eX, eY, tf));
			tf.explodes.add(tf.gf.createExplode(eX, eY, dir, tf));
		}
	}

	private void die() {
		this.living = false;
	}
}
