package kmeans;

import java.util.ArrayList;
import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 初始化一个Kmean对象，将k置为3
		int num;
		System.out.println("输入要分为的类数：");
		num = (new Scanner(System.in)).nextInt();
		Kmeans k = new Kmeans(num);
		ArrayList<float[]> dataSet = new ArrayList<float[]>();
		ReadData rd = new ReadData();
		String fileName = "E:\\pself\\machine-learning\\src\\main\\test\\testkmeans.txt";
		dataSet = rd.read(fileName);
		// 设置原始数据集
		k.setDataSet(dataSet);
		// 执行算法
		k.kmeans();
		// 得到聚类结果
		ArrayList<ArrayList<float[]>> cluster = k.getCluster();
		// 查看结果
		for (int i = 0; i < cluster.size(); i++) {
			k.printDataArray(cluster.get(i), "cluster[" + i + "]");
		}

	}

}
