package decisiontree.model;

public class RuleNode {
	public int deep;
	public double formerEntropy;
	public double[][] datalist;
	public String tag;

	public int nodeType = -1;
	// public int minSetNum=1 ; //һ����Ч֦�ڵ������������ ��������������ٷ���

	public int divideType = -1;
	public double valveValue = -1;
	public double deltaEntropy = 1;

	public RuleNode leftChild = null;
	public RuleNode rightChild = null;
	public double laterEntropy = 2;

	public RuleNode(double[][] input, int deep, String tag, ProjectInfo pInfo) {
		// System.out.println("���ڽ����� "+tag+" �Žڵ�"); //used for debug
		// System.out.println("�� "+tag+" �Žڵ�������Ŀ��"+input.length);

		this.deep = deep;
		this.tag = tag;
		this.datalist = input;
		this.formerEntropy = getDataListEntropy(input); // undefined
		this.nodeType = -1;

		if ((this.deep > pInfo.RuleDeep) || (this.datalist.length <= pInfo.NodeLimitNum)
				|| (this.formerEntropy <= pInfo.NodeLimitEntropy)) {
			// ��ȹ�����ߵ�����С�����߷����������㹻���������ٷ���
			this.leftChild = this.rightChild = null;
			int temp = decideType(this.datalist); // undefined
			if ((temp == 0) || (temp == 1))
				this.nodeType = temp;
			else
				System.out.println("ERROR:����decideType���ֵ���Ϸ�");
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
					System.out.println("ERROR:����decideType���ֵ���Ϸ�");
			} else {
				// System.out.println("tag1") ; //used for debug
				double[][] leftList = Divide(input, this.divideType, this.valveValue, 0, pInfo);
				double[][] rightList = Divide(input, this.divideType, this.valveValue, 1, pInfo);

				// if (tag=="001") System.out.println(leftChild==null) ;//used
				// for debug
				// if (leftList==null) System.out.println(tag+"�ڵ��������Ϊ��") ; else
				// System.out.println(tag+"�ڵ����������"+leftList.length) ; //used
				// for debug
				// if (rightList==null) System.out.println(tag+"�ڵ��������Ϊ��") ;
				// else System.out.println(tag+"�ڵ����������"+rightList.length) ;
				// //used for debug

				// if ((leftList==null)||(rightList==null))
				// this.leftChild=this.rightChild=null ;
				if ((leftList.length == 0) || (rightList.length == 0)) {
					this.leftChild = this.rightChild = null;
					int temp = decideType(this.datalist);
					if ((temp == 0) || (temp == 1))
						this.nodeType = temp;
					else
						System.out.println("ERROR:����decideType���ֵ���Ϸ�");
				} else {
					this.leftChild = new RuleNode(leftList, deep + 1, tag + '0', pInfo);
					this.rightChild = new RuleNode(rightList, deep + 1, tag + '1', pInfo);
				}
			}
		}
	}

	public static double[][] Divide(double[][] input, int attribute, double valve, int methodtype, ProjectInfo pInfo) {
		double[][] rs = null;
		// ͨ��attribute value type����input�ֳ�������
		if (methodtype == 0) { // �˴�Ϊmethodtype=1ʱ�������Ҳ����attr value<valve�����
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

	private static double getDataListEntropy(double[][] input) { // ��������Ķ�ά����ȷ��datalist����
		DataProperty dp = new DataProperty();
		double rs_entropy = -1;
		// ͨ��tempType��ֵ������irisdata�������
		// tempTypeֻ��3��ֵ��0��ʾ��1��1��ʾ��2��-1��ʾ������ һ�����ڱ�ʾ�쳣
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
			rs = 1; // �������Ļ����԰�num0=num1ʱnode�Ĺ������������ʵ��
		else
			rs = 0;

		return rs;
	}
}
