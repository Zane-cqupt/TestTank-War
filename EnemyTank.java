import java.util.*;
//ʵ��̹�˲��ص���δ��ɣ�
//֮ǰ֮����ֱ����enemytank������϶����ܵõ�����̹�˵���Ϣ����Ϊ�Ҳ�û�а�TestTank����ļ��϶��󴫵ݸ����������ļ��϶���
//����Ҫдһ��������ʵ�ּ��϶���Ĵ���

public class EnemyTank extends Tank implements Runnable {
	// ���ɵз�̹�˶��󼯺�
	Vector<EnemyTank> enemytank = new Vector<EnemyTank>();

	// //����һ���ѷ�̹�˶���
	// Hero hero = null;
	boolean flag=true;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	// ��ʼ������̹�˵�����
	int enemytanknumber = 5;

	int time = 0;

	boolean isLive = true;
	int speed = 3;
	// �����ӵ����϶���
	Vector<Bullet> al = new Vector<Bullet>();

	// Bullet bullet = null;

	public EnemyTank(int x, int y) {
		super(x, y);
	}

	// ʹ����������̹�˼��ϵõ�MyPanel̹�˼��ϵ�����
	public void setEnemytank(Vector<EnemyTank> enemytank) {
		this.enemytank = enemytank;
	}

	// //дһ�������������������ѷ�̹�˶�����Ի��Mypanel�����ѷ�̹�˵Ķ�������
	// public void setHero(Hero hero){
	// this.hero = hero;
	// }

	// дһ�������ж�̹���Ƿ��ص�
	public boolean touchTank() {
		boolean isTouch = false;

		// �Է�����з����
		switch (this.dirction) {
		case 0:
			// ȡ������̹��
			for (int i = 0; i < this.enemytank.size(); i++) {
				// ȡ������̹��
				EnemyTank et = this.enemytank.get(i);
				// ����Ҫȡ����̹�˲����Լ���һ��
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
			// ȡ������̹��
			for (int i = 0; i < this.enemytank.size(); i++) {
				// ȡ������̹��
				EnemyTank et = this.enemytank.get(i);
				// ����Ҫȡ����̹�˲����Լ���һ��
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
			// ȡ������̹��
			for (int i = 0; i < this.enemytank.size(); i++) {
				// ȡ������̹��
				EnemyTank et = this.enemytank.get(i);
				// ����Ҫȡ����̹�˲����Լ���һ��
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
			// ȡ������̹��
			for (int i = 0; i < this.enemytank.size(); i++) {
				// ȡ������̹��
				EnemyTank et = this.enemytank.get(i);
				// ����Ҫȡ����̹�˲����Լ���һ��
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
	// ʹ����̹���ƶ�����������֯��������̹���ص�
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

			// ������������ı䷽��
			this.dirction = (int) (Math.random() * 4);
			// �ж�̹���Ƿ�����
			if (this.isLive == false) {
				break;
			}
			this.time++;
			if (time % 2 == 0) {
				for (int i = 0; i < 3; i++) {

					if (isLive == true) {
						// ���ɵ���̹���ӵ�����
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
