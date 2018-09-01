package kmeans;

import java.util.ArrayList;
import java.util.Random;

public class Kmeans {

	private int k;// �ֳɶ��ٴ�
	private int m;// ��������
	private int dataSetLength;// ���ݼ�Ԫ�ظ����������ݼ��ĳ���
	private ArrayList<float[]> dataSet;// ���ݼ�����
	private ArrayList<float[]> center;// ��������
	private ArrayList<ArrayList<float[]>> cluster; // ��
	private ArrayList<Float> jc;// ���ƽ���ͣ�kԽ�ӽ�dataSetLength�����ԽС
	private Random random;

	public void setDataSet(ArrayList<float[]> dataSet) {
		// ����������ԭʼ���ݼ�
		this.dataSet = dataSet;
	}

	public ArrayList<ArrayList<float[]>> getCluster() {
		return cluster;
	}

	public Kmeans(int k) {
		// ������Ҫ�ֳɵĴ�����
		if (k <= 0) {
			k = 1;
		}
		this.k = k;
	}

	private void init() {
		// ��ʼ��
		m = 0;
		random = new Random();
		if (dataSet == null || dataSet.size() == 0) {
			System.out.println("����Ϊ�գ����������ݣ�������");
		} else {
			dataSetLength = dataSet.size();
			if (k > dataSetLength) {
				k = dataSetLength;
			}
			center = initCenters();
			cluster = initCluster();
			jc = new ArrayList<Float>();
		}
	}

	private ArrayList<float[]> initCenters() {
		// ��ʼ���������������ֳɶ��ٴؾ��ж��ٸ����ĵ�
		ArrayList<float[]> center = new ArrayList<float[]>();
		int[] randoms = new int[k];
		boolean flag;
		int temp = random.nextInt(dataSetLength);
		randoms[0] = temp;
		for (int i = 1; i < k; i++) {
			flag = true;
			while (flag) {
				temp = random.nextInt(dataSetLength);
				int j = 0;
				while (j < i) {
					if (temp == randoms[j]) {
						break;
					}
					j++;
				}
				if (j == i) {
					flag = false;
				}
			}
			randoms[i] = temp;
		}
		for (int i = 0; i < k; i++) {
			center.add(dataSet.get(randoms[i]));// ���ɳ�ʼ����������
		}
		return center;
	}

	private ArrayList<ArrayList<float[]>> initCluster() {
		// ��ʼ���ؼ���
		ArrayList<ArrayList<float[]>> cluster = new ArrayList<ArrayList<float[]>>();
		for (int i = 0; i < k; i++) {
			cluster.add(new ArrayList<float[]>());
		}

		return cluster;
	}

	private float distance(float[] element, float[] center) {
		// ����������֮��ľ���
		float distance = 0.0f;
		float x = element[0] - center[0];
		float y = element[1] - center[1];
		float z = x * x + y * y;
		distance = (float) Math.sqrt(z);

		return distance;
	}

	private int minDistance(float[] distance) {
		// ��ȡ���뼯������С�����λ��
		float minDistance = distance[0];
		int minLocation = 0;
		for (int i = 1; i < distance.length; i++) {
			if (distance[i] < minDistance) {
				minDistance = distance[i];
				minLocation = i;
			} else if (distance[i] == minDistance) // �����ȣ��������һ��λ��
			{
				if (random.nextInt(10) < 5) {
					minLocation = i;
				}
			}
		}

		return minLocation;
	}

	private void clusterSet() {
		// ����ǰԪ�طŵ���С����������صĴ���
		float[] distance = new float[k];
		for (int i = 0; i < dataSetLength; i++) {
			for (int j = 0; j < k; j++) {
				distance[j] = distance(dataSet.get(i), center.get(j));
			}
			int minLocation = minDistance(distance);
			cluster.get(minLocation).add(dataSet.get(i));

		}
	}

	private float errorSquare(float[] element, float[] center) {
		// ���������ƽ���ķ���
		float x = element[0] - center[0];
		float y = element[1] - center[1];

		float errSquare = x * x + y * y;

		return errSquare;
	}

	private void countRule() {
		// �������ƽ����׼��������
		float jcF = 0;
		for (int i = 0; i < cluster.size(); i++) {
			for (int j = 0; j < cluster.get(i).size(); j++) {
				jcF += errorSquare(cluster.get(i).get(j), center.get(i));

			}
		}
		jc.add(jcF);
	}

	private void setNewCenter() {
		// �����µĴ����ķ���
		for (int i = 0; i < k; i++) {
			int n = cluster.get(i).size();
			if (n != 0) {
				float[] newCenter = { 0, 0 };
				for (int j = 0; j < n; j++) {
					newCenter[0] += cluster.get(i).get(j)[0];
					newCenter[1] += cluster.get(i).get(j)[1];
				}
				// ����һ��ƽ��ֵ
				newCenter[0] = newCenter[0] / n;
				newCenter[1] = newCenter[1] / n;
				center.set(i, newCenter);
			}
		}
	}

	public void printDataArray(ArrayList<float[]> dataArray, String dataArrayName) {
		// ��ӡ����
		for (int i = 0; i < dataArray.size(); i++) {
			System.out.println("print:(" + dataArray.get(i)[0] + "," + dataArray.get(i)[1] + ")");
		}
		System.out.println("===================================");
	}

	void kmeans() {
		init();
		// ѭ�����飬ֱ������Ϊֹ
		while (true) {
			clusterSet();
			countRule();
			// �����ˣ��������
			if (m != 0) {
				if (jc.get(m) - jc.get(m - 1) == 0) {
					break;
				}
			}

			setNewCenter();
			m++;
			cluster.clear();
			cluster = initCluster();
		}
	}

}
