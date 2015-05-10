import java.util.*;

//用于生成我的坦克（自己控制的）
public class Hero extends Tank {

	// 生成子弹集合对象
	Vector<Bullet> al = new Vector<Bullet>();
	Bullet bullet = null;
	// 生成敌人坦克对象
	EnemyTank et = null;

	boolean isLive = true;

	// 定义速度初始值
	int speed = 3;

	public Hero(int x, int y) {

		super(x, y);
	}

	public void moveUp() {
		y = y - speed;
	}

	public void moveDown() {
		y = y + speed;
	}

	public void moveLeft() {
		x = x - speed;
	}

	public void moveRight() {
		x = x + speed;
	}

	public void moveFast(int dirction) {
		switch (this.dirction) {
		case 0:
			for (int i = 0; i < 10; i++) {
				// for(int j = 0; j < et.al.size(); j++){
				// //取出子弹
				// bullet = et.al.get(j);
				// if(this.y==bullet.y&&this.x<=bullet.x&&this.x+20>=bullet.x){
				// isLive = false;
				// }
				// }
				y -= i;
			}
			break;
		case 1:
			y += 50;
			break;
		case 2:
			x -= 50;
			break;
		case 3:
			x += 50;
			break;

		}
	}

	public void shot(int x, int y, int dirction) {
		switch (dirction) {
		case 0:
			bullet = new Bullet(x + 10, y - 5, 0);
			al.add(bullet);
			Thread t0 = new Thread(bullet);
			t0.start();
			break;
		case 1:
			bullet = new Bullet(x + 10, y + 30, 1);
			Thread t1 = new Thread(bullet);
			al.add(bullet);
			t1.start();
			break;
		case 2:
			bullet = new Bullet(x - 5, y + 10, 2);
			Thread t2 = new Thread(bullet);
			al.add(bullet);
			t2.start();
			break;
		case 3:
			bullet = new Bullet(x + 30, y + 10, 3);
			Thread t3 = new Thread(bullet);
			al.add(bullet);
			t3.start();
			break;
		}
	}
}
