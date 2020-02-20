package com.gzw.tank;

public class Main {
	
	public static void main(String[] args) {
		TankFrame tf = new TankFrame();
		
		int initEnemyTankCount = Integer.parseInt(PropertyMgr.get("initEnemyTankCount"));
		
		for (int i = 0; i < initEnemyTankCount; i++) {
			Tank tank = new Tank(50 + i*50, 200, Dir.DOWN, Group.BAD, tf);
			tank.setMoving(true);
			tf.enemys.add(tank);
		}
		
		while(true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tf.repaint();
		}
	}
}
