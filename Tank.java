//д��һ��̹�˵ĸ���
public class Tank {
	// �����̹�˳���ʱ���X����
	int x = 0;
	// �����̹�˳�����ʱ��Y����
	int y = 0;
	// ���������
	int dirction;

	// д�����캯��
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// ����X����ֵ
	public int getX() {
		return x;
	}

	// ����Y����ֵ
	public int getY() {
		return y;
	}

	// ���ط���
	public int getDirction() {
		return dirction;
	}

	public void setDirction(int dirction) {
		this.dirction = dirction;
	}
}
