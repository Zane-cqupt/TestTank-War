
import java.util.*;

public  class Coordinate {
	//private static FileReader fr = null;
	//private static BufferedReader br = null;
	//Vector<Coordinate> coordinates = null;

	int x, y, dirction;
	
	public Coordinate(int x, int y, int dirction){
		this.x = x;
		this.y = y;
		this.dirction = dirction;
	}
	
//	public static void getTankCoordinate(){
//		try {
//			fr = new FileReader("F:\\javaio\\xuzhi.txt");
//			br = new BufferedReader(fr);
//			//�ȶ�����������ĵ���̹����
//			String n;
//			n = br.readLine();
//			allTank = Integer.parseInt(n);
//			//ת�ж�ȡ��һ�е�����
//			while((n = br.readLine()) != null){
//				//��spilt������ȥ��������������ݵĿո�
//				String []xyz = n.split(" ");
//				//��������Ķ������ʵ����
//				Coordinate coordinate = new Coordinate(Integer.parseInt(xyz[0]),Integer.parseInt(xyz[1]),Integer.parseInt(xyz[2]));
//				coordinates.add(coordinate);
//			}
//			
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		finally{
//			try {
//				br.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
		
	//}
}
