import java.util.*;
//实现坦克不重叠（未完成）
//之前之所以直接用enemytank这个集合对象不能得到敌人坦克的信息是因为我并没有吧TestTank里面的集合对象传递给这个类里面的集合对象
//所以要写一个方法来实现集合对象的传递

public class EnemyTank extends Tank implements Runnable {
	// 生成敌方坦克对象集合
	Vector<EnemyTank> enemytank = new Vector<EnemyTank>();

	// //创建一个友方坦克对象
	// Hero hero = null;
	boolean flag=true;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	// 初始化敌人坦克的数量
	int enemytanknumber = 5;

	int time = 0;

	boolean isLive = true;
	int speed = 3;
	// 生成子弹集合对象
	Vector<Bullet> al = new Vector<Bullet>();

	// Bullet bullet = null;

	public EnemyTank(int x, int y) {
		super(x, y);
	}

	// 使这个类里面的坦克集合得到MyPanel坦克集合的数据
	public void setEnemytank(Vector<EnemyTank> enemytank) {
		this.enemytank = enemytank;
	}

	// //写一个方法是这个类里面的友方坦克对象可以获得Mypanel里面友方坦克的对象数据
	// public void setHero(Hero hero){
	// this.hero = hero;
	// }

	// 写一个方法判断坦克是否重叠
	public boolean touchTank() {
		boolean isTouch = false;

		// 对方向进行分情况
		switch (this.dirction) {
		case 0:
			// 取出敌人坦克
			for (int i = 0; i < this.enemytank.size(); i++) {
				// 取出敌人坦克
				EnemyTank et = this.enemytank.get(i);
				// 必须要取出的坦克不跟自己是一辆
				if (et != this) {
					if (et.dirction == 0 || et.dirction == 1) {
						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 20
								&& this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
						if (this.x <= et.x + 20 && this.x >= et.x
								&& this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
					}
					if (et.dirction == 2 || et.dirction == 3) {
						if (this.x >= et.x && this.x <= et.x + 30
								&& this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
						if (this.x <= et.x && this.x + 20 >= et.x
								&& this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case 1:
			// 取出敌人坦克
			for (int i = 0; i < this.enemytank.size(); i++) {
				// 取出敌人坦克
				EnemyTank et = this.enemytank.get(i);
				// 必须要取出的坦克不跟自己是一辆
				if (et != this) {
					if (et.dirction == 0 || et.dirction == 1) {
						if (this.x <= et.x + 20 && this.x >= et.x
								&& et.y >= this.y && et.y <= this.y + 30) {
							return true;
						}
						if (this.x <= et.x && this.x + 20 >= et.x
								&& et.y >= this.y && et.y <= this.y + 30) {
							return true;
						}
					}
					if (et.dirction == 2 || et.dirction == 3) {
						if (this.x >= et.x && this.x <= et.x + 30
								&& this.y <= et.y && this.y + 30 >= et.y) {
							return true;
						}
						if (this.x <= et.x && this.x + 20 >= et.x
								&& this.y <= et.y && this.y + 30 >= et.y) {
							return true;
						}
					}
				}
			}
			break;
		case 2:
			// 取出敌人坦克
			for (int i = 0; i < this.enemytank.size(); i++) {
				// 取出敌人坦克
				EnemyTank et = this.enemytank.get(i);
				// 必须要取出的坦克不跟自己是一辆
				if (et != this) {
					if (et.dirction == 0 || et.dirction == 1) {
						if (this.x >= et.x && this.x <= et.x + 20
								&& et.y <= this.y && et.y + 30 >= this.y) {
							return true;
						}
						if (this.x >= et.x && this.x <= et.x + 20
								&& et.y >= this.y && et.y <= this.y + 20) {
							return true;
						}
					}
					if (et.dirction == 2 || et.dirction == 3) {
						if (this.x >= et.x && this.x <= et.x + 30
								&& this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
						if (this.x >= et.x && this.x <= et.x + 30
								&& this.y <= et.y && this.y + 20 >= et.y) {
							return true;
						}
					}
				}
			}
			break;
		case 3:
			// 取出敌人坦克
			for (int i = 0; i < this.enemytank.size(); i++) {
				// 取出敌人坦克
				EnemyTank et = this.enemytank.get(i);
				// 必须要取出的坦克不跟自己是一辆
				if (et != this) {
					if (et.dirction == 0 || et.dirction == 1) {
						if (this.x <= et.x && this.x + 30 >= et.x
								&& et.y <= this.y && et.y + 30 >= this.y) {
							return true;
						}
						if (this.x <= et.x && this.x + 30 >= et.x
								&& et.y >= this.y && et.y <= this.y + 20) {
							return true;
						}
					}
					if (et.dirction == 2 || et.dirction == 3) {
						if (this.x <= et.x && this.x + 30 >= et.x
								&& this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
						if (this.x <= et.x && this.x + 30 >= et.x
								&& this.y <= et.y && this.y + 20 >= et.y) {
							return true;
						}
					}
				}
			}
			break;
		}

		return isTouch;
	}

	@Override
	// 使敌人坦克移动起来并且组织两个敌人坦克重叠
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			switch (this.dirction) {
			case 0:
				for (int i = 0; i < 30; i++) {
					if (this.getY() - 5 > 0 && !this.touchTank()) {
						y -= speed;
					} else {
						break;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				break;
			case 1:
				for (int i = 0; i < 30; i++) {
					if (this.getY() + 70 < 300 && !this.touchTank()) {
						y += speed;
					} else {
						break;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				break;
			case 2:

				for (int i = 0; i < 30; i++) {
					if (this.getX() - 5 > 0 && !this.touchTank()) {
						x -= speed;
					} else {
						break;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				break;
			case 3:

				for (int i = 0; i < 30; i++) {
					if (this.getX() + 50 < 400 && !this.touchTank()) {
						x += speed;
					} else {
						break;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						System.out.println(e);
					}
				}

				break;
			}

			// 产生随机数来改变方向
			this.dirction = (int) (Math.random() * 4);
			// 判断坦克是否死亡
			if (this.isLive == false) {
				break;
			}
			this.time++;
			if (time % 2 == 0) {
				for (int i = 0; i < 3; i++) {

					if (isLive == true) {
						// 生成敌人坦克子弹对象
						Bullet bullet = null;
						if (this.al.size() < 5) {
							switch (this.dirction) {
							case 0:
								bullet = new Bullet(this.getX() + 10,
										this.getY() - 5, 0);
								this.al.add(bullet);
								Thread t0 = new Thread(bullet);
								t0.start();
								break;
							case 1:
								bullet = new Bullet(this.getX() + 10,
										this.getY() + 30, 1);
								Thread t1 = new Thread(bullet);
								this.al.add(bullet);
								t1.start();
								break;
							case 2:
								bullet = new Bullet(this.getX() - 5,
										this.getY() + 10, 2);
								Thread t2 = new Thread(bullet);
								this.al.add(bullet);
								t2.start();
								break;
							case 3:
								bullet = new Bullet(this.getX() + 30,
										this.getY() + 10, 3);
								Thread t3 = new Thread(bullet);
								this.al.add(bullet);
								t3.start();
								break;
							}
						}
					}
				}
			}
			//System.out.println("flag is "+ flag);
		if(flag==false) break;
		
		}
	}
}
