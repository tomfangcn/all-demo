package jproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LiuDeHuaProxy {

	private PersonAct obj = new LiuDeHua();

	public PersonAct getProxy() {
		return (PersonAct) Proxy.newProxyInstance(LiuDeHuaProxy.class.getClassLoader(), obj.getClass().getInterfaces(),
				new InvocationHandler() {

					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

						if (method.getName().equals("sing")) {
							System.out.println("�������ľ����ˣ�Ҫ����������ȸ�ʮ���Ǯ����");
							return method.invoke(obj, args);
						}

						if (method.getName().equals("dance")) {
							System.out.println("�������ľ����ˣ�Ҫ����������ȸ���ʮ���Ǯ����");
							return method.invoke(obj, args);
						}

						return null;
					}
				});
	}
}
