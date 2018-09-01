package ezexpress.app.heart;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

	/** 心跳包内容 */
	private static final String HEARTBEATREQUEST = "0x11";
	private static final String HEARTBEATRESPONSE = "0x12";

	public boolean isRequest(IoSession session, Object message) {
		// 请求心跳包信息:
		if (message.equals(HEARTBEATREQUEST))
			return true;
		return false;
	}

	public boolean isResponse(IoSession session, Object message) {
		// 响应心跳包信息:
		return false;
	}

	public Object getRequest(IoSession session) {
		// 请求预设信息:
		System.out.println("发送心跳消息：");
		return HEARTBEATREQUEST;
	}

	public Object getResponse(IoSession session, Object request) {
		// 响应预设信息
		return request;
	}

}
