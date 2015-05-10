import java.io.*;
import java.util.*;

public class Recorder {
	Vector<Coordinate> coordinates2 =new Vector<Coordinate>();
	Coordinate coordinate = null;
	int  allTanks;
	// 初始化敌人坦克数量
	int enemytanknumber = 5;
	int etankNum = 5;
	// 初始化友方坦克的生命值
	private int tankNum = 3;
	//初始化已经打死的敌人坦克数量
	int allTank;
	// 生成io流对象
	private static FileReader fr = null;
	private static FileWriter fw = null;
	private static BufferedReader br = null;
	private static BufferedWriter bw = null;
	// 建立敌人坦克向量
	Vector<EnemyTank> et = new Vector<EnemyTank>();

	public Vector<EnemyTank> getEt() {
		return et;
	}
	
	public void setEt(Vector<EnemyTank> ets1) {
		System.out.println(22);
		this.et = ets1;
	}

	public int getetankNum() {
		return etankNum;
	}

	public void setetankNum(int etankNum) {
		this.etankNum = etankNum;
	}

	public int gettankNum() {
		return tankNum;
	}

	public void settankNum(int tankNum) {
		this.tankNum = tankNum;
	}

	public void eTankNumReduce() {
		this.etankNum--;
	}

	public void TankNumReduce() {
		this.tankNum--;
	}

	// 让游戏存盘退出
	public void recorderTank() {
		try {
			// 实例化IO对象
			fw = new FileWriter("F:\\javaio\\xuzhi.txt");
			bw = new BufferedWriter(fw);
			
			//给已经打死的敌人坦克数量赋值
			allTank = enemytanknumber-getetankNum();
			//输出打死的敌人坦克数量
			bw.write(allTank+"\r\n");
			//bw.newLine();
			

			// 取出敌人坦克对象，并进行判断
			for (int i = 0; i < et.size(); i++) {
				EnemyTank ets = this.et.get(i);
				// 判断敌人坦克的死活
				if (ets.isLive == true) {
					String n = ets.x + " " + ets.y + " " + ets.dirction;
					//输出敌人坦克的坐标
					bw.write(n+"\r\n");

					//System.out.println(n);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				bw.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	public Vector<Coordinate> getTankCoordinate(){
		try {
			fr = new FileReader("F:\\javaio\\xuzhi.txt");
			br = new BufferedReader(fr);
			//先读出所有消灭的敌人坦克数
			String n;
			n = br.readLine();
			allTanks = Integer.parseInt(n);
			//转行读取下一行的坐标
			while((n = br.readLine()) != null){
				//用spilt函数来去除保存的坐标数据的空格
				String []xyz = n.split(" ");
				//对坐标类的对象进行实例化
				coordinate = new Coordinate(Integer.parseInt(xyz[0]),Integer.parseInt(xyz[1]),Integer.parseInt(xyz[2]));
				coordinates2.add(coordinate);
				System.out.println(n);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			try {
				fr.close();
				br.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return coordinates2;
	}
}
