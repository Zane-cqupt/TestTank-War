//写出一个坦克的父类
public class Tank {
	// 定义出坦克出来时候的X坐标
	int x = 0;
	// 定义出坦克出来的时候Y坐标
	int y = 0;
	// 定义出方向
	int dirction;

	// 写出构造函数
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// 返回X坐标值
	public int getX() {
		return x;
	}

	// 返回Y坐标值
	public int getY() {
		return y;
	}

	// 返回方向
	public int getDirction() {
		return dirction;
	}

	public void setDirction(int dirction) {
		this.dirction = dirction;
	}
}
