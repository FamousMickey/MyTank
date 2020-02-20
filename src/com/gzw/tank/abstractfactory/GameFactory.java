package com.gzw.tank.abstractfactory;

import com.gzw.tank.Dir;
import com.gzw.tank.Group;
import com.gzw.tank.TankFrame;

public abstract class GameFactory {
	public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);
	public abstract BaseExplode createExplode(int x, int y, Dir dir, TankFrame tf);
	public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);
}
