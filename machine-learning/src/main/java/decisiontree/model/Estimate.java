package decisiontree.model;

import java.util.ArrayList;
import java.util.List;

public class Estimate {
	List<double[]> list0;
	List<double[]> list1;
	double[][] array0;
	double[][] array1;
	RuleNode examtree;
	double ErrRatio;

	public Estimate(RuleNode rule, double[][] examset, ProjectInfo pInfo) {
		this.list0 = new ArrayList<double[]>();
		this.list1 = new ArrayList<double[]>();
		this.examtree = examTree(rule, examset, pInfo);
		this.ErrRatio = getErrRatio(this.list0, this.list1);
		this.array0 = convert(list0, pInfo);
		this.array1 = convert(list1, pInfo);
	}

	private double getErrRatio(List<double[]> list0, List<double[]> list1) {
		double len1 = list0.size();
		double len2 = list1.size();
		double errnum1 = 0, errnum2 = 0;
		for (int i = 0; i < len1; i++) {
			double[] temp = (double[]) list0.get(i);
			if (temp[2] == 1)
				errnum1++;
		}
		for (int i = 0; i < len2; i++) {
			double[] temp = (double[]) list1.get(i);
			if (temp[2] == 0)
				errnum2++;
		}
		double erratio = (errnum1 + errnum2) / (len1 + len2);
		return erratio;
	}

	private double[][] convert(List list, ProjectInfo pInfo) {
		int len = list.size();
		double[][] rs = new double[len][3 + pInfo.AttrNum];
		for (int i = 0; i < len; i++) {
			rs[i] = (double[]) list.get(i);
		}
		return rs;
	}

	private RuleNode examTree(RuleNode node, double[][] data, ProjectInfo pInfo) {
		node.datalist = data;
		node.formerEntropy = getDataListEntropy(data);
		if (node.nodeType == -1) { // this node is not a leaf node
			double[][] left = RuleNode.Divide(data, node.divideType, node.valveValue, 0, pInfo);
			double[][] right = RuleNode.Divide(data, node.divideType, node.valveValue, 1, pInfo);
			if (left.length == 0)
				node.leftChild = null;
			else
				node.leftChild = examTree(node.leftChild, left, pInfo);
			if (right.length == 0)
				node.rightChild = null;
			else
				node.rightChild = examTree(node.rightChild, right, pInfo);
			return node;
		} else { // this node is a leaf node
			node.leftChild = null;
			node.rightChild = null;

			int len = node.datalist.length; // 将判定为0或者1的类调入到list0,list1中
			int num = 0;
			if (node.nodeType == 0) {
				for (int i = 0; i < len; i++)
					list0.add(node.datalist[i]);
			}
			if (node.nodeType == 1) {
				for (int i = 0; i < len; i++)
					list1.add(node.datalist[i]);
			}
			return node;
		}
	}

	private static double getDataListEntropy(double[][] input) { // 根据输入的二维数组确定datalist的熵
		DataProperty dp = new DataProperty();
		double rs_entropy = -1;
		// 通过tempType的值来计算irisdata数组的熵
		// tempType只有3个值，0表示类1，1表示类2，-1表示其他类 一般用于表示异常
		int num1 = 0, num2 = 0;
		for (int i = 0; i < input.length; i++) {
			if (input[i][2] == 0)
				num1++;
			if (input[i][2] == 1)
				num2++;
		}
		rs_entropy = dp.getEntropy(num1, num2);
		return rs_entropy;
	}
}
