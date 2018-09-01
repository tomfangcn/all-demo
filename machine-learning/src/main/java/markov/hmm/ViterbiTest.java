package markov.hmm;

public class ViterbiTest {

	public static void main(String[] args) {
		/*
		 * ������״̬ϵ����1��2��3 O��(�죬�ף���)���������������ת��������к����0���׼�Ϊ1 ��۲��Ϊ0��1��0
		 */
		double[] pi = { 0.2, 0.4, 0.4 }; // ��ʼ���� 1,2,3�ĳ�ʼ����

		// 1 2 3
		double[][] A = { { 0.5, 0.2, 0.3 }, // 1
				{ 0.3, 0.5, 0.2 }, // 2
				{ 0.2, 0.3, 0.5 } };// 3 �������ڲ�ת�ƾ���

		// �� ��
		double[][] B = { { 0.5, 0.5 }, // 1
				{ 0.4, 0.6 }, // 2
				{ 0.7, 0.3 } };// 3 �������

		int[] O = { 0, 1, 0 };

		// weight���ʾ������״̬���������ʾ�۲�״ֵ̬��״̬

		double[][] weight = new double[A.length][O.length];
		// ��t=0��ʱ��ʼ���㣬��ʼ��

		// for (double p : pi) {
		for (int i = 0; i < weight.length; i++) {
			weight[i][O[0]] = pi[i] * B[i][O[0]];
		}

		// }

		for (int t = 1; t < O.length; t++) { // t=0״̬�ɳ�ʼ��������t=1��ʼ
			for (int j = 0; j < A.length; j++) {// ����Aʼ����һ������
				weight[j][t] = Double.MIN_VALUE;
				for (int k = 0; k < A.length; k++) {
					// ����һ��Ҫ��� hmm��ǰһ��״̬*��kת�Ƶ�j״̬*�������
					// ������˵j=1��ʱ�򣬱�ʾ��1ת�Ƶ�1������2ת�Ƶ�1��״̬
					double temp = weight[k][t - 1] * A[k][j] * B[j][O[t]];
					if (temp > weight[j][t]) {
						weight[j][t] = temp;
					}
				}

			}
		}

		// ��ӡweight����
		for (int i = 0; i < weight.length; i++) {
			for (int j = 0; j < weight[0].length; j++) {
				System.out.print(weight[i][j] + " ");
			}
			System.out.println();
		}

	}
}
