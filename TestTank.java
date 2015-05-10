//����һ�������ƶ����ѷ�̹��
//����һ��ǽ�ࣨx,y,islive������mypanel��д����ǽ�ķ�����ǽ�����֣�һ��һ�¾��ƣ�һ�ִ����£��ڻ�֮ǰ������for���
//����һϵ�ж���Ȼ����дһ���ж��Ƿ����ǽ�ĺ�����

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
//	//���ɶ�ȡ֮������������
	//Coordinate coordinate = null;
//	// ���ɵз�̹�˶��󼯺�
//	Vector<EnemyTank> enemytank = new Vector<EnemyTank>();
	

	//������¼��Ϣ����Ķ���
	Recorder recorder = new Recorder();


	// ��ʼ������̹�˵�����
	int enemytanknumber  = recorder.etankNum;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestTank tt = new TestTank();
	}

	// ���캯��
	public TestTank() {

		//������Ϣ��ʾ���Ķ���
		msp = new MyShowPanel();
		//������
		this.add(msp);
		//����JFream
		this.setVisible(true);
		this.setSize(550, 450);
		this.setTitle("TankGame");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//��ʼ���˵�
		jmb = new JMenuBar();
		//һ���˵�
		jm1 = new JMenu("��Ϸ");
		
		//�����˵�
		jmi1 = new JMenuItem("����Ϸ");
		jmi2 = new JMenuItem("�����˳�");
		jmi3 = new JMenuItem("������Ϸ");
		
		//��Ӳ˵�
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jmb.add(jm1);
		//JFream��Ӳ˵�
		//���˵���ӵ�������
		this.setJMenuBar(jmb);
		//ע�����
		jmi1.addActionListener(this);
		jmi2.addActionListener(this);
		jmi3.addActionListener(this);
		//�����¼�
		jmi1.setActionCommand("newgame");
		jmi2.setActionCommand("saveexit");
		jmi3.setActionCommand("geton");
		
	}
	//������������ʾ����ʼ��Ϸ���ֹ���Ϣ�����
	class MyShowPanel extends JPanel{

		public void paint(Graphics g){
			super.paint(g);
			//�������
			g.fillRect(0, 0, 400, 300);
			//����������ɫ
			g.setColor(Color.yellow);
			//����������
			Font font = new Font("����", Font.BOLD, 30);
			//�������
			g.setFont(font);
			//д����
			g.drawString("stage  1", 150, 150);
		}

	}

	// �������Լ������,�̳��̣߳����̼����Ľӿ�
	class MyPanel extends JPanel implements KeyListener, Runnable {
		
		int speed = 3;
		// ���ɵз�̹�˶��󼯺�
		Vector<EnemyTank> enemytank = new Vector<EnemyTank>();
//		//��ʼ��������Ϸ�ĵ���̹�˵�������������
		Vector<Coordinate> coordinates = null;
		EnemyTank et = null;
	
		// ��ʼ���ѷ�̹�˲����ɶ���
		Hero hero = new Hero(30, 60);
//		// ���ɵз�̹�˶��󼯺�
//		Vector<EnemyTank> enemytank = new Vector<EnemyTank>();
		//����ը�����󼯺�
		Vector<Bomb> bombs = new Vector<Bomb>();
//		//���ɶ�ȡ֮������������
//		Coordinate coordinate = null;
		//����ͼƬ����
		Image image1 = new ImageIcon("`D@9{)W(0[TV~XQ632CY~RM.png").getImage();
		Image image2 = new ImageIcon("IE~HHG_XV8H)W(NV@P}{_NQ.png").getImage();
		//������һ����־λ���������Ƿ�ִ�л�����һ�����ɵĵ���̹�˻��ǻ��������˼�¼����̹��
		String flag;
		
		public MyPanel(String flag) {
			
			this.flag = flag;
			// �з�̹�˽��г�ʼ��,���flag����newgame�Ϳ�ʼ����Ϸ
			if(flag.equals("newgame")){
				for (int i = 0; i < 5; i++) {
					EnemyTank et = new EnemyTank((i + 1) * 50, 0);
					et.setDirction(2);
					//��������̹���߳�
					Thread t = new Thread(et);
					t.start();
				
					//����̹�˼�������ӵ�
					enemytank.add(et);
					//�����ｫMyPanel����ļ��϶����뵽����̹��������
					et.setEnemytank(enemytank);
				}
			}
			//���flag����newgame�ͼ�����һ��
			else{
				
				coordinates = recorder.getTankCoordinate();
				System.out.println(coordinates.size());
				for(int i = 0; i < coordinates.size(); i++){
					Coordinate coordinate = coordinates.get(i);
//				}
//				for (int i = 0; i < recorder.etankNum; i++) {
					et = new EnemyTank(coordinate.x, coordinate.y);
					et.setDirction(coordinate.dirction);
					//��������̹���߳�
					Thread t = new Thread(et);
					t.start();
				
					//����̹�˼�������ӵ�
					enemytank.add(et);
					//�����ｫMyPanel����ļ��϶����뵽����̹��������
					et.setEnemytank(enemytank);
				}
			}
			
		}

		// ��дpaint����
		public void paint(Graphics g) {
			super.paint(g);
			
			// �������
			g.fillRect(0, 0, 400, 300);
			
			// ������һ�������ѷ�̹��
			if(hero.isLive == true){
				this.drawTank(hero.getX(), hero.getY(), g, hero.getDirction(), 0);
			}
			//����ѷ�̹��������û������ͼ�������̹�ˣ�һ����������
			if(recorder.gettankNum() > 0){
				if(hero.isLive == false){
					//�����һ��̹�����ˣ���ô������������һ����̹�˶���
					hero = new Hero(30, 60);
					//Ȼ�������һ��ȫ�µ�̹��
					this.drawTank(hero.getX(), hero.getY(), g, hero.getDirction(), 0);
				}
			}
			
			// ������ʼ����Ϸ�ĵз�̹��
			//if(this.flag.equals("newgame")){
			int n = enemytanknumber-recorder.allTanks;
				System.out.println(n);
				for (int i = 0; i < recorder.enemytanknumber-recorder.allTanks; i++) {
					// ȡ��̹��
					EnemyTank et = enemytank.get(i);

					if (et.isLive == true) {
						this.drawTank(et.getX(), et.getY(), g, et.getDirction(), 1);
					}
				// ����з�̹�������������ټ�������ɾ��Ԫ�أ���Ϊѭ���Ĵ��������ɼ��ϵ�Ԫ���������Ƶ�
				
					//��������̹���ӵ���̹�˻����ţ���һ���ӵ��Ѿ����ˣ�
					for(int j = 0; j < et.al.size(); j++){
						//ȡ���ӵ�
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
//			//����ͻ�������������Ϸ�ĵ���̹��
//			else if(flag.equals("geton")){
//				coordinates = recorder.getTankCoordinate();
//				System.out.println("222");
//				for(int k = 0; k < coordinates.size(); k++){
//					Coordinate coordinate = coordinates.get(k);
//					for (int i = 0; i < recorder.allTanks; i++) {
//						// ȡ��̹��
//						EnemyTank et = enemytank.get(i);
//
//						if (et.isLive == true) {
//							this.drawTank(coordinate.x, coordinate.y, g, coordinate.dirction, 1);
//						}
//					// ����з�̹�������������ټ�������ɾ��Ԫ�أ���Ϊѭ���Ĵ��������ɼ��ϵ�Ԫ���������Ƶ�
//					
//						//��������̹���ӵ���̹�˻����ţ���һ���ӵ��Ѿ����ˣ�
//						for(int j = 0; j < et.al.size(); j++){
//							//ȡ���ӵ�
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
			
			
			
			
			//����Ϸ��ʱ���ṩ����Ϣ
			if (this.flag.equals("newgame")) {
				// ������ʾ��Ϣ���ѷ�̹��
				this.drawTank(100, 310, g, 0, 0);
				// �ı�������ɫ
				g.setColor(Color.BLACK);
				// �����ѷ�̹������
				g.drawString(recorder.gettankNum() + " ", 130, 330);
				// ������ʾ��Ϣ�ĵط�̹��
				this.drawTank(100, 350, g, 0, 1);
				// �ı�������ɫ
				g.setColor(Color.BLACK);
				// ��������̹�˵�����
				g.drawString(recorder.getetankNum() + " ", 130, 370);
				// ������ʾ���л��еĵ���̹������̹��
				this.drawTank(430, 150, g, 0, 1);
				// �ı�������ɫ������
				g.setColor(Color.black);
				Font font = new Font("����", Font.BOLD, 15);
				g.setFont(font);
				// ����һЩ��ʾ��Ϣ
				g.drawString("���л��е�̹����", 400, 130);
				// �ı�������ɫ
				g.setColor(Color.black);
				// �������л���̹�˵�����
				g.drawString(enemytanknumber-recorder.getetankNum() + "", 470, 170);
			}
			//������Ϸ��ʱ���ṩ����Ϣ
			if(this.flag.equals("geton")){
			//������ʾ��Ϣ���ѷ�̹��
			this.drawTank(100, 310, g, 0, 0);
			//�ı�������ɫ
			g.setColor(Color.BLACK);
			//�����ѷ�̹������
			g.drawString(recorder.gettankNum()+" ", 130, 330);
			//������ʾ��Ϣ�ĵط�̹��
			this.drawTank(100, 350, g, 0, 1);
			//�ı�������ɫ
			g.setColor(Color.BLACK);
			//��������̹�˵�����
			g.drawString(recorder.enemytanknumber-recorder.allTanks+" ", 130, 370);
			//������ʾ���л��еĵ���̹������̹��
			this.drawTank(430, 150, g, 0, 1);
			//�ı�������ɫ������
			g.setColor(Color.black);
			Font font = new Font("����", Font.BOLD, 15);
			g.setFont(font);
			//����һЩ��ʾ��Ϣ
			g.drawString("���л��е�̹����", 400, 130);
			//�ı�������ɫ
			g.setColor(Color.black);
			//�������л���̹�˵�����
			g.drawString(recorder.allTanks+"", 470, 170);
			}
			
			
			
			//�ı�������ɫ
			g.setColor(Color.yellow);	
			// �����ӵ�
			for (int i = 0; i < this.hero.al.size(); i++) {
				// ȡ���ӵ�
				Bullet bl = hero.al.get(i);
				if (bl != null && bl.isLive == true) {
					//�����ѷ�̹���ӵ�
					g.draw3DRect(bl.getx(), bl.gety(), 1, 1, false);
				}
				// ����ӵ����������ڼ��������������ӵ�
				if (bl.isLive == false) {
					hero.al.remove(bl);
				}
			}
			
			//����ը��,ÿ��һ��̹�˾ͱ�ըһ��
			for(int i = 0; i < bombs.size(); i++){
				//ȡ��ը��
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
		// дһ���ж��ӵ��Ƿ�����ѷ�̹�˵ĺ���
		public void hitTank_2(Hero hero, Bullet bullet) {
			switch (hero.dirction) {
			case 0:
			case 1:
				if (bullet.x > hero.x && bullet.x < hero.x + 20 && bullet.y > hero.y
						&& bullet.y < hero.y + 30) {
					// ���У��ѷ�̹�˺��ӵ�����
					bullet.isLive = false;
					this.hero.isLive = false;
					//�����������ٷ���
					recorder.TankNumReduce();
//					//�����趨�з�̹�˵�����
//					recorder.settankNum(recorder.tankNum);
					//����ը������
					Bomb bomb = new Bomb(hero.x, hero.y);
					//��ը�����϶������ʵ����
					bombs.add(bomb);
				}
				break;
			case 2:
			case 3:
				if (bullet.x > hero.x && bullet.x < hero.x + 30 && bullet.y > hero.y
						&& bullet.y < hero.y + 20) {
					// ���У��ѷ�̹�˺��ӵ�������
					hero.isLive = false;
					bullet.isLive = false;
					//�����������ٷ���
					recorder.TankNumReduce();
					//����ը������
					Bomb bomb = new Bomb(hero.x, hero.y);
					//��ը�����϶������ʵ����
					bombs.add(bomb);
				}

			}
		}

		// дһ���ж��ӵ��Ƿ���ез�̹�˵ĺ���
		public void hitTank(EnemyTank et, Bullet bullet) {
			switch (et.dirction) {
			case 0:
			case 1:
				if (bullet.x > et.x && bullet.x < et.x + 20 && bullet.y > et.y
						&& bullet.y < et.y + 30) {
					// ���У�����̹�˺��ӵ�������
					et.isLive = false;
					bullet.isLive = false;
					//�����������ٷ���
					recorder.eTankNumReduce();
					//����ը������
					Bomb bomb = new Bomb(et.x, et.y);
					//��ը�����϶������ʵ����
					bombs.add(bomb);
				}
				break;
			case 2:
			case 3:
				if (bullet.x > et.x && bullet.x < et.x + 30 && bullet.y > et.y
						&& bullet.y < et.y + 20) {
					// ���У�����̹�˺��ӵ�������
					et.isLive = false;
					bullet.isLive = false;
					//�����������ٷ���
					recorder.eTankNumReduce();
					//����ը������
					Bomb bomb = new Bomb(et.x, et.y);
					//��ը�����϶������ʵ����
					bombs.add(bomb);
				}

			}
		}

		// ��װһ��������̹�˵ĺ���drawTank,�������棺0��ʾ���ϣ�1��ʾ���£�2��ʾ����3��ʾ���ҡ��������棬0��ʾ�ѷ�̹�ˣ�1��ʾ�з�̹�ˡ�
		public void drawTank(int x, int y, Graphics g, int direction, int type) {
			// �ж�����
			switch (type) {
			case 0:
				g.setColor(Color.CYAN);
				break;
			case 1:
				g.setColor(Color.YELLOW);
				break;
			}
			// �жϷ���
			switch (direction) {
			case 0:
				// g.setColor(Color.CYAN);
				// ������ߵľ���(��ʼx����ʼy�����ߣ�true��ʾ����false��ʾ����
				g.fill3DRect(x, y, 5, 30, false);
				// �����ұ߾���
				g.fill3DRect(x + 15, y, 5, 30, false);
				// �����м����
				g.fill3DRect(x + 5, y + 5, 10, 20, false);
				// ����Բ��
				g.fillOval(x + 4, y + 10, 10, 10);
				// ������
				g.drawLine(x + 9, y + 15, x + 9, y - 4);
				// this.repaint();
				// g.fill3DRect(x + 8, y, width, height, raised)
				break;
			case 1:
				// g.setColor(Color.CYAN);
				// ������ߵľ���(��ʼx����ʼy�����ߣ�true��ʾ����false��ʾ����
				g.fill3DRect(x, y, 5, 30, false);
				// �����ұ߾���
				g.fill3DRect(x + 15, y, 5, 30, false);
				// �����м����
				g.fill3DRect(x + 5, y + 5, 10, 20, false);
				// ����Բ��
				g.fillOval(x + 4, y + 10, 10, 10);
				// ������
				g.drawLine(x + 9, y + 15, x + 9, y + 34);
				// this.repaint();
				break;
			case 2:
				// g.setColor(Color.CYAN);
				// ��������ľ���(��ʼx����ʼy�����ߣ�true��ʾ����false��ʾ����
				g.fill3DRect(x, y, 30, 5, false);
				// �����±߾���
				g.fill3DRect(x, y + 15, 30, 5, false);
				// �����м����
				g.fill3DRect(x + 5, y + 5, 20, 10, false);
				// ����Բ��
				g.fillOval(x + 10, y + 4, 10, 10);
				// ������
				g.drawLine(x + 9, y + 9, x - 6, y + 9);
				// this.repaint();
				break;
			case 3:
				// g.setColor(Color.CYAN);
				// ��������ľ���(��ʼx����ʼy�����ߣ�true��ʾ����false��ʾ����
				g.fill3DRect(x, y, 30, 5, false);
				// �����±߾���
				g.fill3DRect(x, y + 15, 30, 5, false);
				// �����м����
				g.fill3DRect(x + 5, y + 5, 20, 10, false);
				// ����Բ��
				g.fillOval(x + 10, y + 4, 10, 10);
				// ������
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
		// ��̹���ڹ涨�ķ�Χ���ƶ�
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
				// �ж��Ƿ��ӵ����е���̹��
				for (int i = 0; i < this.hero.al.size(); i++) {
					// ȡ���ӵ�
					Bullet bl = hero.al.get(i);
					if (bl.isLive == true) {
						for (int j = 0; j < enemytank.size(); j++) {
							// ȡ��̹��
							EnemyTank et = enemytank.get(j);
							if (et.isLive == true) {
								this.hitTank(et, bl);
							}
						}
						// �ӵ����ػ�
						this.repaint();
					}
				}
				//�жϵ���̹�˵��ӵ��Ƿ�����ѷ�̹��
				for(int i = 0; i < enemytank.size(); i++){
					//ȡ������̹��
					EnemyTank et = enemytank.get(i);
					//ȡ������̹���ӵ�(ֻ��һ�ţ�
					for (int j = 0; j < et.al.size(); j++) {
						Bullet bl = et.al.get(j);
						if(bl.isLive == true){
							//ǰ���Ѿ�ȡ���ѷ�̹��
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
			//ȥ����ʾ��Ϣ�����
			this.remove(msp);
			// ����������
			mp = new MyPanel("newgame");
			// �����߳�
			Thread t = new Thread(mp);
			t.start();	
			// ������
			this.add(mp);
			// ע�����
			this.addKeyListener(mp);
			//ˢ�½���
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
			//ȥ����ʾ��Ϣ�����
			this.remove(msp);
			// ����������
			mp = new MyPanel("geton");
			// �����߳�
			Thread t2 = new Thread(mp);
			t2.start();	
			// ������
			this.add(mp);
			// ע�����
			this.addKeyListener(mp);
			//ˢ�½���
			this.setVisible(true);
		}
		
	}
}
