import java.util.*;

public class Bullet implements Runnable {
	int x;
	int y;
	// 模拟子弹生死
	boolean isLive = true;
	// 初始化子弹的速度
	int speed = 4;
	int dirction;

	public Bullet(int x, int y, int dirction) {
		this.x = x;
		this.y = y;
		this.dirction = dirction;
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
			switch (this.dirction) {
			case 0:
				y -= speed;
				break;
			case 1:
				y += speed;
				break;
			case 2:
				x -= speed;
				break;
			case 3:
				x += speed;
				break;
			}
			//不让子弹出边界，并且子弹击中敌人坦克之后死亡
			if (x < -5 || x > 400 || y < -5 || y > 300) {
				isLive = false;
				break;
			}
		}
	}
}
