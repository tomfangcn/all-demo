package decisiontree.model;

public class ProjectInfo {

	public int AttrNum; // 属性值的数目，如有2种属性，则AttrNum=2
	public int[] AttrType; // Attributes[i]表示该值的属性，double为0,boolean为1
	public String[] SqlColName; // SQL数据库中列名
	public String[] AttrDescription; // 对Arrtibute的描述
	public int dataNodeNum; // 数据集的数目
	public String[] classNum; // 数据分属的各个类的名字
	// 正常情况下，double[i][0]存储SetNum,double[i][1]存储Type,double[i][1]存储tempType
	// double[i][2-(2+AttrNum-1)]存储属性值
	// public int addAttrNum ; //自定义属性数目 ，默认为0 若为>0则启动自定义属性函数
	public int RuleDeep;
	public int NodeLimitNum; // 若节点内数据数小于等于该值，则不再分裂
	public double NodeLimitEntropy; // 若节点内数据熵小于等于该值，则不再分裂
	public double NodeDeltaEntropy; // 若节点内数据熵小于等于该值，则不再分裂

	public ProjectInfo() { // 空对象
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
			System.out.println("ERROR:ProjectInfo_AttrType输入值数目错误");
		this.AttrType = AttrType;
		if (SqlColName.length != this.AttrNum)
			System.out.println("ERROR:ProjectInfo_SqlColName输入值数目错误");
		this.SqlColName = SqlColName;
		if (AttrDescription.length != this.AttrNum)
			System.out.println("ERROR:ProjectInfo_AttrDescription输入值数目错误");
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
		int width = 3; // excel中的列数
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
		System.out.println("规则数的节点数为 " + rule.getNodeNum(root));

		Estimate es = new Estimate(rule, trainset, pInfo);
		System.out.println("训练集的错误率为:" + es.ErrRatio);
		es = new Estimate(rule, examset, pInfo);
		System.out.println("检验集的错误率为:" + es.ErrRatio);

		// es=new Estimate(rule,trainset,pInfo) ;
		// System.out.println("训练集的错误率为:"+es.ErrRatio);

	}

}
