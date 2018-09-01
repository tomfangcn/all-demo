package decisiontree.model;

public class Hunt {
	public double min_entropy;
	public double value_value;
	public int type;

	public Hunt(double[][] dataset, ProjectInfo pInfo) {
		// 1. calculate the entropy of initial dataset
		// 2. find best attritube from 4
		double[][] rs = new double[pInfo.AttrNum][2];

		int mintype = -1;
		double minentropy = 1;
		double valve_value = -1;

		for (int i = 0; i < pInfo.AttrNum; i++) {
			if (pInfo.AttrType[i] == 0)
				rs[i] = FindBestValve(preDeal(dataset, i, pInfo), pInfo);
			else if (pInfo.AttrType[i] == 1) {
				rs[i] = BoolAttr(dataset, i);
			} ///////// undefined
			else
				System.out.println("ERROR：Hunt_Hunt_pInfo.AttrType[" + i + "]设定有问题");
			// rs[i][0]=entropy rs[i][1]=valve
			if (rs[i][0] < minentropy) {
				minentropy = rs[i][0];
				valve_value = rs[i][1];
				mintype = i;
			}
		}
		// 3. find the best one and output
		this.min_entropy = minentropy;
		this.value_value = valve_value;
		this.type = mintype;
	}

	private static double[] BoolAttr(double[][] input, int Attr) {
		int total_len = input.length;
		int len1 = 0, len2 = 0;
		for (int i = 0; i < total_len; i++) {
			if (input[i][3 + Attr] == 0)
				len1++;
			if (input[i][3 + Attr] == 1)
				len2++;
		}
		if ((len1 + len2) != total_len)
			System.out.println("ERROR:Hunt_BoolAttr_bool变量中有异值");
		DataProperty dp = new DataProperty();
		double[] rs = new double[2];
		rs[0] = dp.getEntropy(len1, len2);
		rs[1] = 0.5;
		return rs;
	}

	private static double[][] preDeal(double[][] dataset, int attr, ProjectInfo pInfo) { // transfer
																							// IrisData[]
																							// to
																							// int[][]
																							// to
																							// fit
																							// the
																							// followign
																							// processing
		if ((attr < pInfo.AttrNum) && (attr >= 0)) {
			double[][] rs = new double[dataset.length][3]; // 3
															// attributes:Attribute
															// Value,Number,,tempType
			for (int i = 0; i < dataset.length; i++) {
				rs[i][0] = dataset[i][attr + 3];
				rs[i][1] = dataset[i][0];
				rs[i][2] = dataset[i][2]; // ATTENTION the taken value is
											// tempTyoe!
			}
			return rs;
		} else {
			System.out.println("ERROR:Hunt_preDeal_type输入值不正确");
			return null;
		}
	}

	private static double[] FindBestValve(double[][] input, ProjectInfo pInfo) {
		// 要考虑Type的多值性，最好只有两个值
		// print(input) ; // used for debug
		double[][] sorted = QuickSort(input, 0, input.length - 1); // 1st
																	// step:sort
																	// the input
																	// array
		// 接下来应该要在不同值区间内循环，挑一个熵值最小的。
		double min_entropy = 2;
		double valve_value = -1;
		// System.out.println(sorted==null); // used for debug
		for (int i = 0; i < sorted.length - 1; i++) {
			// calculate the entropy of the division whose valve is between i
			// and i+1
			if (sorted[i][0] != sorted[i + 1][0]) { // 避免在两个相同值之间分析的情况
				double temp_entropy = CalculateEntropy(sorted, i);
				if (temp_entropy < min_entropy) {
					min_entropy = temp_entropy;
					valve_value = (sorted[i][0] + sorted[i + 1][0]) / 2;
				}
			}
		}
		double[] rs = new double[2];
		rs[0] = min_entropy;
		rs[1] = valve_value;
		return rs;
	}

	private static double CalculateEntropy(double[][] sorted, int i) { // can
																		// only
																		// deal
																		// with
																		// the
																		// data
																		// which
																		// have
																		// only
																		// two
																		// classes
		DataProperty dp = new DataProperty(); // initialization of dataproperty

		double rs_entropy = -1;
		// double tagclass=sorted[0][2] ;

		int num1 = 0;
		int num2 = 0;
		for (int x = 0; x < i + 1; x++) {
			if (sorted[x][2] == 0)
				num1++;
			else if (sorted[x][2] == 1)
				num2++;
			else
				System.out.println("ERROR from CalculateEntropy: the value of tempType of a item is -1");
		}
		double entropy1 = dp.getEntropy(num1, num2);
		int tnum1 = num1 + num2; // total number of the former sequence

		num1 = 0;
		num2 = 0;
		for (int x = i + 1; x < sorted.length; x++) {
			if (sorted[x][2] == 0)
				num1++;
			else if (sorted[x][2] == 1)
				num2++;
			else
				System.out.println("ERROR from CalculateEntropy: the value of tempType of a item is -1");
		}
		double entropy2 = dp.getEntropy(num1, num2);
		int tnum2 = num1 + num2;
		rs_entropy = (entropy1 * tnum1 + entropy2 * tnum2) / (tnum1 + tnum2);
		return rs_entropy;
	}

	private static double[][] QuickSort(double[][] input, int low, int high) {
		if (low >= high)
			return null;
		int first = low;
		int last = high;
		double[] key = input[low];
		while (first < last) {
			while ((first < last) && (input[last][0] >= key[0]))
				--last;
			input[first] = input[last];
			while ((first < last) && (input[first][0] <= key[0]))
				++first;
			input[last] = input[first];
		}
		input[first] = key;

		double[][] res1, res2;

		if (first - 1 > low) {
			res1 = QuickSort(input, low, first - 1);
		} else if (first - 1 == low) {
			double[][] temp = { input[low] };
			res1 = temp;
		} else {
			res1 = null;
		}

		if (high > first + 1) {
			res2 = QuickSort(input, first + 1, high);
		} else if (high == first + 1) {
			double[][] temp = { input[high] };
			res2 = temp;
		} else {
			res2 = null;
		}

		double[][] finalres;
		finalres = Combine(res1, res2, key);
		return finalres;
	}

	private static double[][] Combine(double[][] res1, double[][] res2, double[] key) {
		int len1, len2;
		if (res1 == null)
			len1 = 0;
		else
			len1 = res1.length;
		if (res2 == null)
			len2 = 0;
		else
			len2 = res2.length;

		double[][] res = new double[len1 + len2 + 1][3];
		int index = 0;
		for (int i = 0; i < len1; i++)
			res[index++] = res1[i];
		res[index++] = key;
		for (int i = 0; i < len2; i++)
			res[index++] = res2[i];
		return res;
	}
}
