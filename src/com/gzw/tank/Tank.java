package com.gzw.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.gzw.tank.abstractfactory.BaseTank;

public class Tank extends BaseTank{
	private Random random = new Random();
	
	int x,y;

	Dir dir = Dir.DOWN;
	
	private boolean moving = false;
	
	TankFrame tf = null;
	
	public static final int HEIGHT = ResourceMgr.goodTankU.getHeight(),
			WIDTH = ResourceMgr.goodTankU.getWidth();

	private static final int SPEED = 2;
	
	private boolean living = true;
	
	
	
//	FireStrategy fs = new DefaultFireStrategy();
	FireStrategy fs = new FourDirFireStrategy();
	public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
		this.group = group;
		
		this.rect.height = Tank.HEIGHT;
		this.rect.width = Tank.WIDTH;
	}

	public void paint(Graphics g) {
		if (!living) tf.enemys.remove(this);
		
		switch (dir) {
		case LEFT:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
			break;
		case UP:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
			break;
		case DOWN:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
			break;
		}
		
		move();
	}
	
	private void move() {
		if (!moving) return;
		
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
		}
		
		this.rect.x = this.x; 
		this.rect.y = this.y;
		
		if (this.group == Group.BAD && random.nextInt(100) > 97) {
			this.fire();
		}
		if (this.group == Group.BAD && random.nextInt(100) >= 95) {
			randomDir();
		}
		
		boundsCheck();
	}

	private void boundsCheck() {
		if (this.x < 0) this.x = 0;
		if (this.y < 30) this.y = 30;
		if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) this.x = TankFrame.GAME_WIDTH - Tank.WIDTH;
		if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) this.y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
	}

	private void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}
	
	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void fire() {
		fs.fire(this);
	}

	public void die() {
		this.living = false;
	}
	
	/**
	 * 获取射出子弹的x与y的坐标
	 * @return
	 */
	public int[] getBulletXAndY() {
		int[] xAndY = new int[2];
		return xAndY;
	}
}
