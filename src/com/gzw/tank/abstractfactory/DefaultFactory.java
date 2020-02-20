package com.gzw.tank.abstractfactory;

import com.gzw.tank.Bullet;
import com.gzw.tank.Dir;
import com.gzw.tank.Explode;
import com.gzw.tank.Group;
import com.gzw.tank.Tank;
import com.gzw.tank.TankFrame;

public class DefaultFactory extends GameFactory {

	@Override
	public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new Tank(x, y, dir, group, tf);
	}

	@Override
	public BaseExplode createExplode(int x, int y, Dir dir, TankFrame tf) {
		return new Explode(x, y, tf);
	}

	@Override
	public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new Bullet(x, y, dir, group, tf);
	}

}
