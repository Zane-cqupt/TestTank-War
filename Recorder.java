import java.io.*;
import java.util.*;

public class Recorder {
	Vector<Coordinate> coordinates2 =new Vector<Coordinate>();
	Coordinate coordinate = null;
	int  allTanks;
	// ��ʼ������̹������
	int enemytanknumber = 5;
	int etankNum = 5;
	// ��ʼ���ѷ�̹�˵�����ֵ
	private int tankNum = 3;
	//��ʼ���Ѿ������ĵ���̹������
	int allTank;
	// ����io������
	private static FileReader fr = null;
	private static FileWriter fw = null;
	private static BufferedReader br = null;
	private static BufferedWriter bw = null;
	// ��������̹������
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

	// ����Ϸ�����˳�
	public void recorderTank() {
		try {
			// ʵ����IO����
			fw = new FileWriter("F:\\javaio\\xuzhi.txt");
			bw = new BufferedWriter(fw);
			
			//���Ѿ������ĵ���̹��������ֵ
			allTank = enemytanknumber-getetankNum();
			//��������ĵ���̹������
			bw.write(allTank+"\r\n");
			//bw.newLine();
			

			// ȡ������̹�˶��󣬲������ж�
			for (int i = 0; i < et.size(); i++) {
				EnemyTank ets = this.et.get(i);
				// �жϵ���̹�˵�����
				if (ets.isLive == true) {
					String n = ets.x + " " + ets.y + " " + ets.dirction;
					//�������̹�˵�����
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
			//�ȶ�����������ĵ���̹����
			String n;
			n = br.readLine();
			allTanks = Integer.parseInt(n);
			//ת�ж�ȡ��һ�е�����
			while((n = br.readLine()) != null){
				//��spilt������ȥ��������������ݵĿո�
				String []xyz = n.split(" ");
				//��������Ķ������ʵ����
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
