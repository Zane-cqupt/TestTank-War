import java.util.*;

public class Bullet implements Runnable {
	int x;
	int y;
	// ģ���ӵ�����
	boolean isLive = true;
	// ��ʼ���ӵ����ٶ�
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
			//�����ӵ����߽磬�����ӵ����е���̹��֮������
			if (x < -5 || x > 400 || y < -5 || y > 300) {
				isLive = false;
				break;
			}
		}
	}
}
