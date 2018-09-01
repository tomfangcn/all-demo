package ezexpress.app.heart;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

	/** ���������� */
	private static final String HEARTBEATREQUEST = "0x11";
	private static final String HEARTBEATRESPONSE = "0x12";

	public boolean isRequest(IoSession session, Object message) {
		// ������������Ϣ:
		if (message.equals(HEARTBEATREQUEST))
			return true;
		return false;
	}

	public boolean isResponse(IoSession session, Object message) {
		// ��Ӧ��������Ϣ:
		return false;
	}

	public Object getRequest(IoSession session) {
		// ����Ԥ����Ϣ:
		System.out.println("����������Ϣ��");
		return HEARTBEATREQUEST;
	}

	public Object getResponse(IoSession session, Object request) {
		// ��ӦԤ����Ϣ
		return request;
	}

}
