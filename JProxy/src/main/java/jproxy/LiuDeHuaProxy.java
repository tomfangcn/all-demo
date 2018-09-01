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
							System.out.println("我是他的经纪人，要找他唱歌得先给十万块钱！！");
							return method.invoke(obj, args);
						}

						if (method.getName().equals("dance")) {
							System.out.println("我是他的经纪人，要找他跳舞得先给二十万块钱！！");
							return method.invoke(obj, args);
						}

						return null;
					}
				});
	}
}
