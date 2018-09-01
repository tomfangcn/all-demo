package jproxy;

public class JproxyTest {

	public static void main(String[] args) {
		LiuDeHuaProxy proxy = new LiuDeHuaProxy();

		PersonAct p = proxy.getProxy();
		String retV = p.sing("±ùÓê");
		System.out.println(retV);
		String value = p.dance("½­ÄÏstyle");
		System.out.println(value);
	}
}
