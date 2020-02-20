package com.gzw.tank.abstractfactory;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.gzw.tank.Dir;
import com.gzw.tank.FireStrategy;
import com.gzw.tank.FourDirFireStrategy;
import com.gzw.tank.Group;
import com.gzw.tank.ResourceMgr;
import com.gzw.tank.TankFrame;
import com.gzw.tank.abstractfactory.BaseTank;

public class RectTank extends BaseTank{
	private Random random = new Random();
	
	int x,y;

	Dir dir = Dir.DOWN;
	Group group = Group.BAD;
	
	private boolean moving = false;
	
	TankFrame tf = null;
	
	public static final int HEIGHT = ResourceMgr.goodTankU.getHeight(),
			WIDTH = ResourceMgr.goodTankU.getWidth();

	private static final int SPEED = 2;
	
	private boolean living = true;
	
	public Rectangle rect = new Rectangle();
	
//	FireStrategy fs = new DefaultFireStrategy();
	FireStrategy fs = new FourDirFireStrategy();
	public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
		this.group = group;
		
		this.rect.height = RectTank.HEIGHT;
		this.rect.width = RectTank.WIDTH;
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
		if (this.x > TankFrame.GAME_WIDTH - RectTank.WIDTH) this.x = TankFrame.GAME_WIDTH - RectTank.WIDTH;
		if (this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT) this.y = TankFrame.GAME_HEIGHT - RectTank.HEIGHT;
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
//		fs.fire(this);
		int extraX = 0;
		int extraY = 0;
		switch (this.dir) {
		case RIGHT:
			extraX += ResourceMgr.goodTankR.getWidth();
			extraX -= ResourceMgr.bulletR.getWidth();
			extraX += 3;
			extraY += ResourceMgr.goodTankR.getHeight()/2;
			extraY -= ResourceMgr.bulletR.getHeight()/2;
			break;

		case LEFT:
			extraY += ResourceMgr.goodTankL.getHeight()/2;
			extraY -= ResourceMgr.bulletL.getHeight()/2;
			extraY -= 1;
			break;
			
		case DOWN:
			extraX += ResourceMgr.goodTankD.getWidth()/2;
			extraX -= ResourceMgr.bulletD.getWidth()/2;
			extraX -= 1;
			extraY += ResourceMgr.goodTankD.getHeight();
			extraY -= ResourceMgr.bulletD.getHeight();
			break;
			
		case UP:
			extraX += ResourceMgr.goodTankU.getWidth()/2;
			extraX -= ResourceMgr.bulletU.getWidth()/2;
			break;
		}
		
		for (Dir dir:Dir.values()) {
			tf.gf.createBullet(this.x + extraX, this.y+ extraY, dir, this.group, this.tf);
		}
	}

	public void die() {
		this.living = false;
	}
	
	/**
	 * 获取射出子弹的x坐标
	 * @return
	 */
	public int getFireBulletX() {
		return 0;
	}
	
	/**
	 * 获取射出子弹的y的坐标
	 * @return
	 */
	public int getFireBulletY() {
		return 0;
	}
}
