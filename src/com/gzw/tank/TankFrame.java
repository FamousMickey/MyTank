package com.gzw.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import com.gzw.tank.abstractfactory.BaseBullet;
import com.gzw.tank.abstractfactory.BaseExplode;
import com.gzw.tank.abstractfactory.BaseTank;
import com.gzw.tank.abstractfactory.DefaultFactory;
import com.gzw.tank.abstractfactory.GameFactory;

public class TankFrame extends Frame {
	
	public static final int GAME_WIDTH = 1280, GAME_HEIGHT = 800;
	
	Tank myTank = new Tank(200, 200, Dir.DOWN, Group.GOOD, this);
	
	public List<BaseBullet> bullets = new ArrayList<>();
	public List<BaseTank> enemys = new ArrayList<>();
	public List<BaseExplode> explodes  = new ArrayList<>();
	
	public GameFactory gf = new DefaultFactory();
	
	public TankFrame() {
		setSize(GAME_WIDTH,GAME_HEIGHT);
		setResizable(false);
		setTitle("tank war");
		setVisible(true);
		
		this.addKeyListener(new MyKeyListener());
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量:" + bullets.size(), 10, 60);
		g.drawString("敌方坦克数量:" + enemys.size(), 10, 80);
		g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
		g.setColor(c);
		myTank.paint(g);
		
		//画子弹
		for(int i = 0; i<bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
		
		//画敌方坦克
		for (int i = 0; i < enemys.size(); i++) {
			enemys.get(i).paint(g);
		}
		
		//画爆炸效果
		for (int i = 0; i < explodes.size(); i++) {
			explodes.get(i).paint(g);
		}
		
		//碰撞检测
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < enemys.size(); j++) {
				bullets.get(i).collideWith(enemys.get(j));
			}
		}
	}
	
	
	class MyKeyListener extends KeyAdapter{
		
		boolean bL = false;
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;
		
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;
			default:
				break;
			}
			setMainTankDir();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;
			case KeyEvent.VK_CONTROL:
				myTank.fire();
				break;
			default:
				break;
			}
			setMainTankDir();
		}
		
		private void setMainTankDir() {
			if(!bL && !bU && !bR && !bD) {
				myTank.setMoving(false);
			}else {
				myTank.setMoving(true);
			}
			
			if (bL) myTank.setDir(Dir.LEFT);
			if (bU) myTank.setDir(Dir.UP);
			if (bD) myTank.setDir(Dir.DOWN);
			if (bR) myTank.setDir(Dir.RIGHT);
		}
	}
}
