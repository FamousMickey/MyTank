package com.gzw.tank.abstractfactory;

import com.gzw.tank.Dir;
import com.gzw.tank.Group;
import com.gzw.tank.TankFrame;

public class RectFactory extends GameFactory{

	@Override
	public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new RectTank(x, y, dir, group, tf);
	}

	@Override
	public BaseExplode createExplode(int x, int y, Dir dir, TankFrame tf) {
		return new RectExplode(x, y, tf);
	}

	@Override
	public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new RectBullet(x, y, dir, group, tf);
	}

}
