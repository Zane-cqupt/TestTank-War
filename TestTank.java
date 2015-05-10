//生成一个可以移动的友方坦克
//生成一个墙类（x,y,islive），在mypanel中写出画墙的方法，墙分两种，一种一下就破，一种打两下，在画之前生成用for语句
//生成一系列对象，然后在写一个判断是否击中墙的函数，

import java.awt.*;

import javax.swing.*;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.*;

public class TestTank extends JFrame implements ActionListener{

	MyPanel mp = null;
	MyShowPanel msp = null;
	JMenuBar jmb = null;
	JMenu jm1 = null;
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	JMenuItem jmi3 = null;
//	//生成读取之后的坐标类对象
	//Coordinate coordinate = null;
//	// 生成敌方坦克对象集合
//	Vector<EnemyTank> enemytank = new Vector<EnemyTank>();
	

	//建立记录信息的类的对象
	Recorder recorder = new Recorder();


	// 初始化敌人坦克的数量
	int enemytanknumber  = recorder.etankNum;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestTank tt = new TestTank();
	}

	// 构造函数
	public TestTank() {

		//生成信息显示面板的对象
		msp = new MyShowPanel();
		//添加面板
		this.add(msp);
		//设置JFream
		this.setVisible(true);
		this.setSize(550, 450);
		this.setTitle("TankGame");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//初始化菜单
		jmb = new JMenuBar();
		//一级菜单
		jm1 = new JMenu("游戏");
		
		//二级菜单
		jmi1 = new JMenuItem("新游戏");
		jmi2 = new JMenuItem("存盘退出");
		jmi3 = new JMenuItem("继续游戏");
		
		//添加菜单
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jmb.add(jm1);
		//JFream添加菜单
		//将菜单添加到窗体上
		this.setJMenuBar(jmb);
		//注册监听
		jmi1.addActionListener(this);
		jmi2.addActionListener(this);
		jmi3.addActionListener(this);
		//发出事件
		jmi1.setActionCommand("newgame");
		jmi2.setActionCommand("saveexit");
		jmi3.setActionCommand("geton");
		
	}
	//创建出可以显示出开始游戏，分关信息的面板
	class MyShowPanel extends JPanel{

		public void paint(Graphics g){
			super.paint(g);
			//画出面板
			g.fillRect(0, 0, 400, 300);
			//设置字体颜色
			g.setColor(Color.yellow);
			//设置字体风格
			Font font = new Font("楷体", Font.BOLD, 30);
			//添加字体
			g.setFont(font);
			//写出字
			g.drawString("stage  1", 150, 150);
		}

	}

	// 创建出自己的面板,继承线程，键盘监听的接口
	class MyPanel extends JPanel implements KeyListener, Runnable {
		
		int speed = 3;
		// 生成敌方坦克对象集合
		Vector<EnemyTank> enemytank = new Vector<EnemyTank>();
//		//初始化继续游戏的敌人坦克的坐标向量对象
		Vector<Coordinate> coordinates = null;
		EnemyTank et = null;
	
		// 初始化友方坦克并生成对象
		Hero hero = new Hero(30, 60);
//		// 生成敌方坦克对象集合
//		Vector<EnemyTank> enemytank = new Vector<EnemyTank>();
		//生成炸弹对象集合
		Vector<Bomb> bombs = new Vector<Bomb>();
//		//生成读取之后的坐标类对象
//		Coordinate coordinate = null;
		//生成图片对象
		Image image1 = new ImageIcon("`D@9{)W(0[TV~XQ632CY~RM.png").getImage();
		Image image2 = new ImageIcon("IE~HHG_XV8H)W(NV@P}{_NQ.png").getImage();
		//创建出一个标志位用来控制是否执行画出第一次生成的敌人坦克还是画出读出了记录的新坦克
		String flag;
		
		public MyPanel(String flag) {
			
			this.flag = flag;
			// 敌方坦克进行初始化,如果flag等于newgame就开始新游戏
			if(flag.equals("newgame")){
				for (int i = 0; i < 5; i++) {
					EnemyTank et = new EnemyTank((i + 1) * 50, 0);
					et.setDirction(2);
					//启动敌人坦克线程
					Thread t = new Thread(et);
					t.start();
				
					//敌人坦克集合添加子弹
					enemytank.add(et);
					//在这里将MyPanel里面的集合对象传入到敌人坦克类里面
					et.setEnemytank(enemytank);
				}
			}
			//如果flag不是newgame就继续上一局
			else{
				
				coordinates = recorder.getTankCoordinate();
				System.out.println(coordinates.size());
				for(int i = 0; i < coordinates.size(); i++){
					Coordinate coordinate = coordinates.get(i);
//				}
//				for (int i = 0; i < recorder.etankNum; i++) {
					et = new EnemyTank(coordinate.x, coordinate.y);
					et.setDirction(coordinate.dirction);
					//启动敌人坦克线程
					Thread t = new Thread(et);
					t.start();
				
					//敌人坦克集合添加子弹
					enemytank.add(et);
					//在这里将MyPanel里面的集合对象传入到敌人坦克类里面
					et.setEnemytank(enemytank);
				}
			}
			
		}

		// 复写paint函数
		public void paint(Graphics g) {
			super.paint(g);
			
			// 画出面板
			g.fillRect(0, 0, 400, 300);
			
			// 画出第一条命的友方坦克
			if(hero.isLive == true){
				this.drawTank(hero.getX(), hero.getY(), g, hero.getDirction(), 0);
			}
			//如果友方坦克生命还没有用完就继续画出坦克（一共三条命）
			if(recorder.gettankNum() > 0){
				if(hero.isLive == false){
					//如果上一个坦克死了，那么就重新在生成一个新坦克对象
					hero = new Hero(30, 60);
					//然后这就是一个全新的坦克
					this.drawTank(hero.getX(), hero.getY(), g, hero.getDirction(), 0);
				}
			}
			
			// 画出开始新游戏的敌方坦克
			//if(this.flag.equals("newgame")){
			int n = enemytanknumber-recorder.allTanks;
				System.out.println(n);
				for (int i = 0; i < recorder.enemytanknumber-recorder.allTanks; i++) {
					// 取出坦克
					EnemyTank et = enemytank.get(i);

					if (et.isLive == true) {
						this.drawTank(et.getX(), et.getY(), g, et.getDirction(), 1);
					}
				// 如果敌方坦克死亡，则不能再集合里面删除元素，因为循环的次数不是由集合的元素量来控制的
				
					//画出敌人坦克子弹（坦克还活着，上一课子弹已经死了）
					for(int j = 0; j < et.al.size(); j++){
						//取出子弹
						Bullet bl = et.al.get(j);
						if(bl.isLive == true ){
							g.draw3DRect(bl.getx(), bl.gety(), 1, 1, true);
						}
						if(bl.isLive == false || et.isLive == false){
							et.al.remove(j);
						}
					}				
				}
			//}
//			//否则就画出继续上盘游戏的敌人坦克
//			else if(flag.equals("geton")){
//				coordinates = recorder.getTankCoordinate();
//				System.out.println("222");
//				for(int k = 0; k < coordinates.size(); k++){
//					Coordinate coordinate = coordinates.get(k);
//					for (int i = 0; i < recorder.allTanks; i++) {
//						// 取出坦克
//						EnemyTank et = enemytank.get(i);
//
//						if (et.isLive == true) {
//							this.drawTank(coordinate.x, coordinate.y, g, coordinate.dirction, 1);
//						}
//					// 如果敌方坦克死亡，则不能再集合里面删除元素，因为循环的次数不是由集合的元素量来控制的
//					
//						//画出敌人坦克子弹（坦克还活着，上一课子弹已经死了）
//						for(int j = 0; j < et.al.size(); j++){
//							//取出子弹
//							Bullet bl = et.al.get(j);
//							if(bl.isLive == true ){
//								g.draw3DRect(bl.getx(), bl.gety(), 1, 1, true);
//							}
//							if(bl.isLive == false || et.isLive == false){
//								et.al.remove(j);
//							}
//						}				
//					}
//				}
//			}
			
			
			
			
			//新游戏的时候提供的信息
			if (this.flag.equals("newgame")) {
				// 画出提示信息的友方坦克
				this.drawTank(100, 310, g, 0, 0);
				// 改变字体颜色
				g.setColor(Color.BLACK);
				// 画出友方坦克数量
				g.drawString(recorder.gettankNum() + " ", 130, 330);
				// 画出提示信息的地方坦克
				this.drawTank(100, 350, g, 0, 1);
				// 改变字体颜色
				g.setColor(Color.BLACK);
				// 画出敌人坦克的数量
				g.drawString(recorder.getetankNum() + " ", 130, 370);
				// 画出提示所有击中的敌人坦克数的坦克
				this.drawTank(430, 150, g, 0, 1);
				// 改变字体颜色和字体
				g.setColor(Color.black);
				Font font = new Font("楷体", Font.BOLD, 15);
				g.setFont(font);
				// 画出一些提示信息
				g.drawString("所有击中的坦克数", 400, 130);
				// 改变字体颜色
				g.setColor(Color.black);
				// 画出所有击中坦克的数量
				g.drawString(enemytanknumber-recorder.getetankNum() + "", 470, 170);
			}
			//继续游戏的时候提供的信息
			if(this.flag.equals("geton")){
			//画出提示信息的友方坦克
			this.drawTank(100, 310, g, 0, 0);
			//改变字体颜色
			g.setColor(Color.BLACK);
			//画出友方坦克数量
			g.drawString(recorder.gettankNum()+" ", 130, 330);
			//画出提示信息的地方坦克
			this.drawTank(100, 350, g, 0, 1);
			//改变字体颜色
			g.setColor(Color.BLACK);
			//画出敌人坦克的数量
			g.drawString(recorder.enemytanknumber-recorder.allTanks+" ", 130, 370);
			//画出提示所有击中的敌人坦克数的坦克
			this.drawTank(430, 150, g, 0, 1);
			//改变字体颜色和字体
			g.setColor(Color.black);
			Font font = new Font("楷体", Font.BOLD, 15);
			g.setFont(font);
			//画出一些提示信息
			g.drawString("所有击中的坦克数", 400, 130);
			//改变字体颜色
			g.setColor(Color.black);
			//画出所有击中坦克的数量
			g.drawString(recorder.allTanks+"", 470, 170);
			}
			
			
			
			//改变字体颜色
			g.setColor(Color.yellow);	
			// 画出子弹
			for (int i = 0; i < this.hero.al.size(); i++) {
				// 取出子弹
				Bullet bl = hero.al.get(i);
				if (bl != null && bl.isLive == true) {
					//画出友方坦克子弹
					g.draw3DRect(bl.getx(), bl.gety(), 1, 1, false);
				}
				// 如果子弹死亡，则在集合里面清除这个子弹
				if (bl.isLive == false) {
					hero.al.remove(bl);
				}
			}
			
			//画出炸弹,每死一个坦克就爆炸一次
			for(int i = 0; i < bombs.size(); i++){
				//取出炸弹
				Bomb bomb = bombs.get(i);
				
				if(bomb.lifeTime == 4){
					g.drawImage(image1, bomb.x, bomb.y, 30, 30, this);
				}
				else if(bomb.lifeTime == 2){
					g.drawImage(image2, bomb.x, bomb.y, 30, 30, this);
				}
				bomb.lifeDown();
				if(bomb.isLive == false){
					bombs.remove(bomb);
				}
			}
		}
		// 写一个判断子弹是否击中友方坦克的函数
		public void hitTank_2(Hero hero, Bullet bullet) {
			switch (hero.dirction) {
			case 0:
			case 1:
				if (bullet.x > hero.x && bullet.x < hero.x + 20 && bullet.y > hero.y
						&& bullet.y < hero.y + 30) {
					// 击中，友方坦克和子弹死亡
					bullet.isLive = false;
					this.hero.isLive = false;
					//调用数量减少方法
					recorder.TankNumReduce();
//					//重新设定有房坦克的数量
//					recorder.settankNum(recorder.tankNum);
					//生成炸弹对象
					Bomb bomb = new Bomb(hero.x, hero.y);
					//对炸弹集合对象进行实例化
					bombs.add(bomb);
				}
				break;
			case 2:
			case 3:
				if (bullet.x > hero.x && bullet.x < hero.x + 30 && bullet.y > hero.y
						&& bullet.y < hero.y + 20) {
					// 击中，友方坦克和子弹都死亡
					hero.isLive = false;
					bullet.isLive = false;
					//调用数量减少方法
					recorder.TankNumReduce();
					//生成炸弹对象
					Bomb bomb = new Bomb(hero.x, hero.y);
					//对炸弹集合对象进行实例化
					bombs.add(bomb);
				}

			}
		}

		// 写一个判断子弹是否击中敌方坦克的函数
		public void hitTank(EnemyTank et, Bullet bullet) {
			switch (et.dirction) {
			case 0:
			case 1:
				if (bullet.x > et.x && bullet.x < et.x + 20 && bullet.y > et.y
						&& bullet.y < et.y + 30) {
					// 击中，敌人坦克和子弹都死亡
					et.isLive = false;
					bullet.isLive = false;
					//调用数量减少方法
					recorder.eTankNumReduce();
					//生成炸弹对象
					Bomb bomb = new Bomb(et.x, et.y);
					//对炸弹集合对象进行实例化
					bombs.add(bomb);
				}
				break;
			case 2:
			case 3:
				if (bullet.x > et.x && bullet.x < et.x + 30 && bullet.y > et.y
						&& bullet.y < et.y + 20) {
					// 击中，敌人坦克和子弹都死亡
					et.isLive = false;
					bullet.isLive = false;
					//调用数量减少方法
					recorder.eTankNumReduce();
					//生成炸弹对象
					Bomb bomb = new Bomb(et.x, et.y);
					//对炸弹集合对象进行实例化
					bombs.add(bomb);
				}

			}
		}

		// 封装一个用来画坦克的函数drawTank,方向里面：0表示向上，1表示向下，2表示向左，3表示向右。类型里面，0表示友方坦克，1表示敌方坦克。
		public void drawTank(int x, int y, Graphics g, int direction, int type) {
			// 判断类型
			switch (type) {
			case 0:
				g.setColor(Color.CYAN);
				break;
			case 1:
				g.setColor(Color.YELLOW);
				break;
			}
			// 判断方向
			switch (direction) {
			case 0:
				// g.setColor(Color.CYAN);
				// 画出左边的矩形(起始x，起始y，宽，高，true表示亮，false表示暗）
				g.fill3DRect(x, y, 5, 30, false);
				// 画出右边矩形
				g.fill3DRect(x + 15, y, 5, 30, false);
				// 画出中间矩形
				g.fill3DRect(x + 5, y + 5, 10, 20, false);
				// 画出圆形
				g.fillOval(x + 4, y + 10, 10, 10);
				// 画出线
				g.drawLine(x + 9, y + 15, x + 9, y - 4);
				// this.repaint();
				// g.fill3DRect(x + 8, y, width, height, raised)
				break;
			case 1:
				// g.setColor(Color.CYAN);
				// 画出左边的矩形(起始x，起始y，宽，高，true表示亮，false表示暗）
				g.fill3DRect(x, y, 5, 30, false);
				// 画出右边矩形
				g.fill3DRect(x + 15, y, 5, 30, false);
				// 画出中间矩形
				g.fill3DRect(x + 5, y + 5, 10, 20, false);
				// 画出圆形
				g.fillOval(x + 4, y + 10, 10, 10);
				// 画出线
				g.drawLine(x + 9, y + 15, x + 9, y + 34);
				// this.repaint();
				break;
			case 2:
				// g.setColor(Color.CYAN);
				// 画出上面的矩形(起始x，起始y，宽，高，true表示亮，false表示暗）
				g.fill3DRect(x, y, 30, 5, false);
				// 画出下边矩形
				g.fill3DRect(x, y + 15, 30, 5, false);
				// 画出中间矩形
				g.fill3DRect(x + 5, y + 5, 20, 10, false);
				// 画出圆形
				g.fillOval(x + 10, y + 4, 10, 10);
				// 画出线
				g.drawLine(x + 9, y + 9, x - 6, y + 9);
				// this.repaint();
				break;
			case 3:
				// g.setColor(Color.CYAN);
				// 画出上面的矩形(起始x，起始y，宽，高，true表示亮，false表示暗）
				g.fill3DRect(x, y, 30, 5, false);
				// 画出下边矩形
				g.fill3DRect(x, y + 15, 30, 5, false);
				// 画出中间矩形
				g.fill3DRect(x + 5, y + 5, 20, 10, false);
				// 画出圆形
				g.fillOval(x + 10, y + 4, 10, 10);
				// 画出线
				g.drawLine(x + 11, y + 9, x + 34, y + 9);
				// this.repaint();
				break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		// 让坦克在规定的范围内移动
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == e.VK_W) {
				this.hero.setDirction(0);
				this.repaint();
				if (hero.getY() - 5 > 0) {
					this.hero.moveUp();
				}
			} else if (e.getKeyCode() == e.VK_S) {
				this.hero.setDirction(1);
				this.repaint();
				if (hero.getY() + 70 < 300) {
					this.hero.moveDown();
				}
			} else if (e.getKeyCode() == e.VK_A) {
				this.hero.setDirction(2);
				this.repaint();
				if (hero.getX() - 5 > 0) {
					this.hero.moveLeft();
				}
			} else if (e.getKeyCode() == e.VK_D) {
				this.hero.setDirction(3);
				this.repaint();
				if (hero.getX() + 50 < 400) {
					this.hero.moveRight();
				}
			}
			if (e.getKeyCode() == e.VK_F) {
				if (this.hero.al.size() < 5) {
					this.hero.shot(this.hero.getX(), this.hero.getY(),this.hero.getDirction());
				}
			}
			if(e.getKeyCode() == e.VK_C){
				this.hero.moveFast(this.hero.dirction);
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				// 判断是否子弹击中敌人坦克
				for (int i = 0; i < this.hero.al.size(); i++) {
					// 取出子弹
					Bullet bl = hero.al.get(i);
					if (bl.isLive == true) {
						for (int j = 0; j < enemytank.size(); j++) {
							// 取出坦克
							EnemyTank et = enemytank.get(j);
							if (et.isLive == true) {
								this.hitTank(et, bl);
							}
						}
						// 子弹的重绘
						this.repaint();
					}
				}
				//判断敌人坦克的子弹是否击中友方坦克
				for(int i = 0; i < enemytank.size(); i++){
					//取出敌人坦克
					EnemyTank et = enemytank.get(i);
					//取出敌人坦克子弹(只有一颗）
					for (int j = 0; j < et.al.size(); j++) {
						Bullet bl = et.al.get(j);
						if(bl.isLive == true){
							//前面已经取出友方坦克
							if(hero.isLive == true){
								this.hitTank_2(hero, bl);
							}							
						}
					}

				}
				this.repaint();
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("newgame")){
			//去除显示信息的面板
			this.remove(msp);
			// 生成面板对象
			mp = new MyPanel("newgame");
			// 生成线程
			Thread t = new Thread(mp);
			t.start();	
			// 添加面板
			this.add(mp);
			// 注册监听
			this.addKeyListener(mp);
			//刷新界面
			this.setVisible(true);
		}
		else if(e.getActionCommand().equals("saveexit")){

			System.out.print(111);
			recorder.setEt(mp.enemytank);
			recorder.recorderTank();
/*			for(int i=0;i<mp.enemytank.size();i++)
			{
				EnemyTank en=mp.enemytank.get(i);
				en.setFlag(false);
			}*/
			System.exit(0);
		}
		else if(e.getActionCommand().equals("geton")){
			//recorder.getTankCoordinate();
			//去除显示信息的面板
			this.remove(msp);
			// 生成面板对象
			mp = new MyPanel("geton");
			// 生成线程
			Thread t2 = new Thread(mp);
			t2.start();	
			// 添加面板
			this.add(mp);
			// 注册监听
			this.addKeyListener(mp);
			//刷新界面
			this.setVisible(true);
		}
		
	}
}
