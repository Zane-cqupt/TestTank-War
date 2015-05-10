public class Bomb {
	int lifeTime = 4;
	boolean isLive = true;
	int x, y;

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void lifeDown() {
		lifeTime -= 2;
		if (lifeTime == 0) {
			isLive = false;
		}
	}
}
