package com.gzw.tank;

public class FourDirFireStrategy implements FireStrategy {

	@Override
	public void fire(Tank t) {
		int extraX = 0;
		int extraY = 0;
		switch (t.dir) {
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
			t.tf.gf.createBullet(t.x + extraX, t.y+ extraY, dir, t.group, t.tf);
		}
		
	}

}
