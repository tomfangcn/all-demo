package ezexpress.app.heart;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

public class KeepAliveRequestTimeoutHandlerImpl implements KeepAliveRequestTimeoutHandler {

	public void keepAliveRequestTimedOut(KeepAliveFilter filter, IoSession session) throws Exception {

		System.out.println("ÐÄÌø³¬Ê±£¡");
	}

}
