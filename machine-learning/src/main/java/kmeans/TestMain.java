package kmeans;

import java.util.ArrayList;
import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// ��ʼ��һ��Kmean���󣬽�k��Ϊ3
		int num;
		System.out.println("����Ҫ��Ϊ��������");
		num = (new Scanner(System.in)).nextInt();
		Kmeans k = new Kmeans(num);
		ArrayList<float[]> dataSet = new ArrayList<float[]>();
		ReadData rd = new ReadData();
		String fileName = "E:\\pself\\machine-learning\\src\\main\\test\\testkmeans.txt";
		dataSet = rd.read(fileName);
		// ����ԭʼ���ݼ�
		k.setDataSet(dataSet);
		// ִ���㷨
		k.kmeans();
		// �õ�������
		ArrayList<ArrayList<float[]>> cluster = k.getCluster();
		// �鿴���
		for (int i = 0; i < cluster.size(); i++) {
			k.printDataArray(cluster.get(i), "cluster[" + i + "]");
		}

	}

}
