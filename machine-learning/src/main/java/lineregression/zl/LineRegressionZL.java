package lineregression.zl;

import java.io.BufferedReader;
import java.io.IOException;

public class LineRegressionZL {

	public static void main(String args[]) throws IOException {
		BufferedReader br = IOUtil.getReader("E:\\pself\\machine-learning\\src\\main\\test\\test.txt");
		String s = "";
		Double ct[] = new Double[3];// ������Ҫ���ٸ�����,���ݶ����о����ö��ٸ�����

		for (int i = 0; i < ct.length; i++) {
			ct[i] = 0.0;// ��ʼ�����в���Ϊ0
		}

		// ѵ�����ݣ�3,1,5
		while ((s = br.readLine()) != null) {

			ct = line_regression(s, 0.001, ct);
			// System.out.println("ct1"+ct[1]);

		}

		// ���ѵ�������Ĳ���
		for (int i = 0; i < ct.length; i++) {
			System.out.println(ct[i]);
		}
	}

	public static Double[] line_regression(String s, double afa, Double ct[]) {
		String st[] = s.split(",");
		Double x[] = new Double[st.length];
		// ��Ҫѧϰ�Ĳ���
		// System.out.println("x.length="+x.length);
		double a = afa;// ѧϰ����
		// x[0]=1.0;
		for (int c = 0; c < x.length; c++) {
			x[c] = Double.parseDouble(st[c]);
			// System.out.println(x[c]);
		}
		Double y = Double.parseDouble(st[st.length - 1]);
		// System.out.println(y);

		// ���ݲ������Ԥ���Y
		// Y=ct0+ct1*x[0]+ct2*x[1]
		Double Y = 0.0;
		for (int i = 0; i < ct.length; i++) {
			Y += ct[i] * x[i];
		}
		// Y=Y+ct[0];
		// �ݶ��½�������ÿ������
		for (int j = 0; j < ct.length; j++) {
			ct[j] = ct[j] + a * (y - Y) * x[j];
		}

		return ct;
	}
}
