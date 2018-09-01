package decisiontree.model;

public class RuleNode {
	public int deep;
	public double formerEntropy;
	public double[][] datalist;
	public String tag;

	public int nodeType = -1;
	// public int minSetNum=1 ; //一个有效枝节点的最少数据数 若少于这个，则不再分裂

	public int divideType = -1;
	public double valveValue = -1;
	public double deltaEntropy = 1;

	public RuleNode leftChild = null;
	public RuleNode rightChild = null;
	public double laterEntropy = 2;

	public RuleNode(double[][] input, int deep, String tag, ProjectInfo pInfo) {
		// System.out.println("正在建立第 "+tag+" 号节点"); //used for debug
		// System.out.println("第 "+tag+" 号节点数组数目："+input.length);

		this.deep = deep;
		this.tag = tag;
		this.datalist = input;
		this.formerEntropy = getDataListEntropy(input); // undefined
		this.nodeType = -1;

		if ((this.deep > pInfo.RuleDeep) || (this.datalist.length <= pInfo.NodeLimitNum)
				|| (this.formerEntropy <= pInfo.NodeLimitEntropy)) {
			// 深度过大或者点数过小，或者分来的数组足够纯净，则不再分类
			this.leftChild = this.rightChild = null;
			int temp = decideType(this.datalist); // undefined
			if ((temp == 0) || (temp == 1))
				this.nodeType = temp;
			else
				System.out.println("ERROR:函数decideType输出值不合法");
		} else {
			Hunt hunt = new Hunt(input, pInfo); // undefined
			this.divideType = hunt.type;
			this.valveValue = hunt.value_value;
			this.laterEntropy = hunt.min_entropy;
			this.deltaEntropy = this.formerEntropy - this.laterEntropy;

			if (this.deltaEntropy < pInfo.NodeDeltaEntropy) {
				this.leftChild = this.rightChild = null; // if deltaEntropy<0.05
															// or deep>5 no
															// longer continue
				int temp = decideType(this.datalist);
				if ((temp == 0) || (temp == 1))
					this.nodeType = temp;
				else
					System.out.println("ERROR:函数decideType输出值不合法");
			} else {
				// System.out.println("tag1") ; //used for debug
				double[][] leftList = Divide(input, this.divideType, this.valveValue, 0, pInfo);
				double[][] rightList = Divide(input, this.divideType, this.valveValue, 1, pInfo);

				// if (tag=="001") System.out.println(leftChild==null) ;//used
				// for debug
				// if (leftList==null) System.out.println(tag+"节点的左子树为空") ; else
				// System.out.println(tag+"节点的左子树长"+leftList.length) ; //used
				// for debug
				// if (rightList==null) System.out.println(tag+"节点的右子树为空") ;
				// else System.out.println(tag+"节点的右子树长"+rightList.length) ;
				// //used for debug

				// if ((leftList==null)||(rightList==null))
				// this.leftChild=this.rightChild=null ;
				if ((leftList.length == 0) || (rightList.length == 0)) {
					this.leftChild = this.rightChild = null;
					int temp = decideType(this.datalist);
					if ((temp == 0) || (temp == 1))
						this.nodeType = temp;
					else
						System.out.println("ERROR:函数decideType输出值不合法");
				} else {
					this.leftChild = new RuleNode(leftList, deep + 1, tag + '0', pInfo);
					this.rightChild = new RuleNode(rightList, deep + 1, tag + '1', pInfo);
				}
			}
		}
	}

	public static double[][] Divide(double[][] input, int attribute, double valve, int methodtype, ProjectInfo pInfo) {
		double[][] rs = null;
		// 通过attribute value type来将input分成两部分
		if (methodtype == 0) { // 此处为methodtype=1时的情况，也就是attr value<valve的情况
			int num = 0;
			for (int i = 0; i < input.length; i++) {
				if ((attribute >= 0) && (attribute < pInfo.AttrNum)) {
					if (input[i][attribute + 3] <= valve)
						num++;
				} else
					System.out.println("ERROR:The value of attribute value illegal");
			}
			rs = new double[num][pInfo.AttrNum + 3];
			int index = 0;
			for (int i = 0; i < input.length; i++) {
				if ((attribute >= 0) && (attribute < pInfo.AttrNum)) {
					if (input[i][attribute + 3] <= valve)
						rs[index++] = input[i];
				} else
					System.out.println("ERROR:The value of attribute value illegal");
			}
			return rs;
		} else if (methodtype == 1) {
			int num = 0;
			for (int i = 0; i < input.length; i++) {
				if ((attribute >= 0) && (attribute < pInfo.AttrNum)) {
					if (input[i][attribute + 3] > valve)
						num++;
				} else
					System.out.println("ERROR:The value of attribute value illegal");
			}
			rs = new double[num][pInfo.AttrNum + 3];
			int index = 0;
			for (int i = 0; i < input.length; i++) {
				if ((attribute >= 0) && (attribute < pInfo.AttrNum)) {
					if (input[i][attribute + 3] > valve)
						rs[index++] = input[i];
				} else
					System.out.println("ERROR:The value of attribute value illegal");
			}
			return rs;
		} else
			System.out.println("ERROR:RuleNode_Divide_methodtype value illegal");
		return rs;
	}

	public int getNodeNum(RuleNode node) {
		if (node.nodeType == -1) {
			int num1 = getNodeNum(node.leftChild);
			int num2 = getNodeNum(node.rightChild);
			return num1 + num2 + 1;
		} else {
			return 1;
		}
	}

	public void PrintRule(String filename, RuleNode node, ProjectInfo pInfo) {
		String out = "";
		for (int i = 0; i < node.deep; i++)
			out += "| ";
		if (node.leftChild.nodeType == -1) {
			FileIO.PrintTxtln(filename, out + pInfo.AttrDescription[node.divideType] + "<=" + node.valveValue);
			PrintRule(filename, node.leftChild, pInfo);
		} else {
			FileIO.PrintTxtln(filename, out + pInfo.AttrDescription[node.divideType] + "<=" + node.valveValue
					+ ": Class" + node.leftChild.nodeType);
		}
		if (node.rightChild.nodeType == -1) {
			FileIO.PrintTxtln(filename, out + pInfo.AttrDescription[node.divideType] + ">" + node.valveValue);
			PrintRule(filename, node.rightChild, pInfo);
		} else {
			FileIO.PrintTxtln(filename, out + pInfo.AttrDescription[node.divideType] + ">" + node.valveValue + ": Class"
					+ node.rightChild.nodeType);
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

	private static int decideType(double[][] input) {
		int rs = -1;
		int num0 = 0, num1 = 0;
		for (int i = 0; i < input.length; i++) {
			if (input[i][2] == 0)
				num0++;
			if (input[i][2] == 1)
				num1++;
		}
		if (num0 < num1)
			rs = 1; // 有条件的话可以吧num0=num1时node的归属用随机数来实现
		else
			rs = 0;

		return rs;
	}
}
