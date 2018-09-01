package markov.hmm;

public class ViterbiTest {

	public static void main(String[] args) {
		/*
		 * 隐含层状态系列是1，2，3 O＝(红，白，红)，现在求隐含层的转移情况其中红记作0，白记为1 则观察变为0，1，0
		 */
		double[] pi = { 0.2, 0.4, 0.4 }; // 初始矩阵 1,2,3的初始概率

		// 1 2 3
		double[][] A = { { 0.5, 0.2, 0.3 }, // 1
				{ 0.3, 0.5, 0.2 }, // 2
				{ 0.2, 0.3, 0.5 } };// 3 隐含层内部转移矩阵

		// 红 白
		double[][] B = { { 0.5, 0.5 }, // 1
				{ 0.4, 0.6 }, // 2
				{ 0.7, 0.3 } };// 3 发射矩阵

		int[] O = { 0, 1, 0 };

		// weight横表示隐含成状态，竖方向表示观察状态值得状态

		double[][] weight = new double[A.length][O.length];
		// 当t=0的时候开始计算，初始化

		// for (double p : pi) {
		for (int i = 0; i < weight.length; i++) {
			weight[i][O[0]] = pi[i] * B[i][O[0]];
		}

		// }

		for (int t = 1; t < O.length; t++) { // t=0状态由初始化，故又t=1开始
			for (int j = 0; j < A.length; j++) {// 这里A始终是一个方阵
				weight[j][t] = Double.MIN_VALUE;
				for (int k = 0; k < A.length; k++) {
					// 这里一定要清楚 hmm的前一个状态*由k转移到j状态*发射概率
					// ，比如说j=1的时候，表示由1转移到1，和又2转移到1的状态
					double temp = weight[k][t - 1] * A[k][j] * B[j][O[t]];
					if (temp > weight[j][t]) {
						weight[j][t] = temp;
					}
				}

			}
		}

		// 打印weight矩阵
		for (int i = 0; i < weight.length; i++) {
			for (int j = 0; j < weight[0].length; j++) {
				System.out.print(weight[i][j] + " ");
			}
			System.out.println();
		}

	}
}
