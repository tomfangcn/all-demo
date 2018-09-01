package server.serverhandler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MyIoHandler extends IoHandlerAdapter {

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		super.messageReceived(session, message);

		String str = message.toString();

		System.out.println("msg===" + str);

		if (str.endsWith("quit")) {
			session.close(true);
			return;
		}
	}

}
