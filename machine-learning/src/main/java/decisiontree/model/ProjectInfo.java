package decisiontree.model;

public class ProjectInfo {

	public int AttrNum; // ����ֵ����Ŀ������2�����ԣ���AttrNum=2
	public int[] AttrType; // Attributes[i]��ʾ��ֵ�����ԣ�doubleΪ0,booleanΪ1
	public String[] SqlColName; // SQL���ݿ�������
	public String[] AttrDescription; // ��Arrtibute������
	public int dataNodeNum; // ���ݼ�����Ŀ
	public String[] classNum; // ���ݷ����ĸ����������
	// ��������£�double[i][0]�洢SetNum,double[i][1]�洢Type,double[i][1]�洢tempType
	// double[i][2-(2+AttrNum-1)]�洢����ֵ
	// public int addAttrNum ; //�Զ���������Ŀ ��Ĭ��Ϊ0 ��Ϊ>0�������Զ������Ժ���
	public int RuleDeep;
	public int NodeLimitNum; // ���ڵ���������С�ڵ��ڸ�ֵ�����ٷ���
	public double NodeLimitEntropy; // ���ڵ���������С�ڵ��ڸ�ֵ�����ٷ���
	public double NodeDeltaEntropy; // ���ڵ���������С�ڵ��ڸ�ֵ�����ٷ���

	public ProjectInfo() { // �ն���
		this.AttrNum = 0;
		this.AttrType = null;
		this.SqlColName = null;
		this.AttrDescription = null;
		this.dataNodeNum = 0;
		this.classNum = null;
		// this.addAttrNum=0 ;
		this.RuleDeep = 100;
		this.NodeLimitNum = 1;
		this.NodeLimitEntropy = 0;
		this.NodeDeltaEntropy = 1;
	}

	public ProjectInfo(int AttrNum, int[] AttrType, String[] SqlColName, String[] AttrDescription, int dataNodeNum,
			String[] classNum, int RuleDeep, int NodeLimitNum, double NodeLimitEntropy, double NodeDeltaEntropy) {

		this.AttrNum = AttrNum;
		if (AttrType.length != this.AttrNum)
			System.out.println("ERROR:ProjectInfo_AttrType����ֵ��Ŀ����");
		this.AttrType = AttrType;
		if (SqlColName.length != this.AttrNum)
			System.out.println("ERROR:ProjectInfo_SqlColName����ֵ��Ŀ����");
		this.SqlColName = SqlColName;
		if (AttrDescription.length != this.AttrNum)
			System.out.println("ERROR:ProjectInfo_AttrDescription����ֵ��Ŀ����");
		this.AttrDescription = AttrDescription;
		this.dataNodeNum = dataNodeNum;
		this.classNum = classNum;
		// this.addAttrNum=addAttrNum ;
		this.RuleDeep = RuleDeep;
		this.NodeLimitNum = NodeLimitNum;
		this.NodeLimitEntropy = NodeLimitEntropy;
		this.NodeDeltaEntropy = NodeDeltaEntropy;
	}

	public static void main(String[] args) {

		int AttrNum = 4;
		int[] AttrType = { 0, 0, 0, 0 };
		String[] SqlColName = { "x", "y", "x+y", "x-y" };
		String[] AttrDescription = { "x", "y", "x+y", "x-y" };
		int dataNodeNum = 2000;
		String[] classNum = { "Average", "Normal" };
		// int addAttrNum=0 ;
		int RuleDeep = 8;
		int NodeLimitNum = 10;
		double NodeLimitEntropy = 0;
		double NodeDeltaEntropy = 0;

		ProjectInfo pInfo = new ProjectInfo(AttrNum, AttrType, SqlColName, AttrDescription, dataNodeNum, classNum,
				RuleDeep, NodeLimitNum, NodeLimitEntropy, NodeDeltaEntropy);

		FileIO ep = new FileIO();
		int len = 11;
		int width = 3; // excel�е�����
		int[] nodea = { 0, 0 };
		int[] nodeb = { width - 1, len - 1 };
		double[][] get1 = ep.getArray("origin", 0, nodea, nodeb);
		double[][] trainset = new double[len][pInfo.AttrNum + 3];
		for (int i = 0; i < len; i++) {
			trainset[i][0] = i + 1;
			trainset[i][1] = get1[i][2];
			trainset[i][2] = get1[i][2];
			trainset[i][3] = get1[i][0];
			trainset[i][4] = get1[i][1];
			trainset[i][5] = get1[i][0] + get1[i][1];
			trainset[i][6] = get1[i][0] - get1[i][1];
		}
		len = 3;
		width = 3;
		nodeb[0] = width - 1;
		nodeb[1] = len - 1;
		double[][] get2 = ep.getArray("origin", 1, nodea, nodeb);
		double[][] examset = new double[len][pInfo.AttrNum + 3];
		for (int i = 0; i < len; i++) {
			examset[i][0] = i + 1;
			examset[i][1] = get2[i][2];
			examset[i][2] = get2[i][2];
			examset[i][3] = get2[i][0];
			examset[i][4] = get2[i][1];
			examset[i][5] = get2[i][0] + get2[i][1];
			examset[i][6] = get2[i][0] - get2[i][1];
		}

		RuleNode root = new RuleNode(trainset, 0, "0", pInfo);
		root.PrintRule("rule", root, pInfo);
		RuleNode rule = root;
		System.out.println("�������Ľڵ���Ϊ " + rule.getNodeNum(root));

		Estimate es = new Estimate(rule, trainset, pInfo);
		System.out.println("ѵ�����Ĵ�����Ϊ:" + es.ErrRatio);
		es = new Estimate(rule, examset, pInfo);
		System.out.println("���鼯�Ĵ�����Ϊ:" + es.ErrRatio);

		// es=new Estimate(rule,trainset,pInfo) ;
		// System.out.println("ѵ�����Ĵ�����Ϊ:"+es.ErrRatio);

	}

}
