
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
//			//先读出所有消灭的敌人坦克数
//			String n;
//			n = br.readLine();
//			allTank = Integer.parseInt(n);
//			//转行读取下一行的坐标
//			while((n = br.readLine()) != null){
//				//用spilt函数来去除保存的坐标数据的空格
//				String []xyz = n.split(" ");
//				//对坐标类的对象进行实例化
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
