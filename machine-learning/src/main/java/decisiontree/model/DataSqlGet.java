package decisiontree.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSqlGet {
	public double[][] dataset;

	public DataSqlGet(String DatabaseName, ProjectInfo pInfo) {
		ResultSet rs = getResultSet(DatabaseName);
		this.dataset = ResultDeal(rs, pInfo);
	}

	private static ResultSet getResultSet(String DatabaseName) {
		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
		String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=multiangle";// 数据源

		try {
			Class.forName(JDriver);// 加载数据库引擎，返回给定字符串名的类
			System.out.println("数据库驱动成功");
		} catch (ClassNotFoundException e) { // e.printStackTrace();
			System.out.println("加载数据库引擎失败");
			System.out.println(e);
		}

		ResultSet rs;
		try {
			String user = "sa";
			String password = "admin";
			Connection con = DriverManager.getConnection(connectDB, user, password);
			System.out.println("数据库连接成功");
			Statement stmt = con.createStatement();
			// String query="select ROW_NUMBER()over(order by class)as row,*
			// from dbo.[bezdekIris.data]" ;
			String query = "select ROW_NUMBER()over(order by class)as row,* from " + DatabaseName;
			rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("数据库内容读取失败");
			return null;
		}
	}

	public static double[][] ResultDeal(ResultSet rs, ProjectInfo pInfo) {
		System.out.println("SqlColName.length" + pInfo.dataNodeNum);
		try {
			int len = pInfo.dataNodeNum;
			System.out.println("len: " + len);
			double[][] dataset = new double[pInfo.dataNodeNum][pInfo.AttrNum + 3];
			int num = 0;
			while ((num < len) && (rs.next())) {
				dataset[num][0] = Integer.parseInt(rs.getString("row"));

				String name = rs.getString("Class"); // 这个在录入数据的时候要特别注意
				int type = -1;
				int namelen = pInfo.classNum.length;
				for (int i = 0; i < namelen; i++) {
					if (name.equals(pInfo.classNum[i]))
						type = i;
				}
				dataset[num][1] = type; // Type
				dataset[num][2] = -1; // tempType
				// 0-> SetNum ;1->Type;2->tempType;3-(3+attr)->attrvalue
				for (int i = 0; i < pInfo.SqlColName.length; i++) {
					dataset[num][i + 3] = Double.parseDouble(rs.getString(pInfo.SqlColName[i]));
				}

				num++;
				// System.out.println(setnum+" "+SL+" "+SW+" "+PL+" "+PW+"
				// "+type) ;
			}
			System.out.println("ResultSet 解析完毕");
			return dataset;
		} catch (SQLException e) {
			System.out.println("ResultSet 解析出错");
			System.out.println(e);
			return null;
		}
	}
}
