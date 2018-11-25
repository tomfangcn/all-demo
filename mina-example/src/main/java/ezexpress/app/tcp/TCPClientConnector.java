package ezexpress.app.tcp;

import ezexpress.app.ClientConnector;
import ezexpress.app.codec.ExpressCodecFactory;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class TCPClientConnector extends ClientConnector {

	private NioSocketConnector connector;
	private IoSession session;

	public TCPClientConnector(IoHandler handler) {
		initConnector(handler);
	}

	public void initConnector(IoHandler handler) {

		if (connector == null) {
			this.connector = new NioSocketConnector();
			this.connector.getSessionConfig().setTcpNoDelay(true);
			this.connector.getFilterChain().addLast("codec",
					new ProtocolCodecFilter(new ExpressCodecFactory(Charset.forName("GBK"))));

			// TODO null filter ��ʵ��
			this.connector.getFilterChain().addLast("logger", null);
			this.connector.getFilterChain().addLast("loginFilter", null);
			this.connector.getFilterChain().addLast("logoutFilter", null);
			this.connector.getFilterChain().addLast("backFlowFilter", null);

			this.connector.setHandler(handler);

		}

	}

	public void connect(String host, int port, String localIP, int timeout) throws Exception {

		timeout = timeout < 0 ? 40 : timeout;

		this.connector.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE, timeout);

		this.connector.setConnectTimeoutMillis(40L);

		ConnectFuture future = null;

		if ((null == localIP) || ("".equals(localIP.trim()))) {

			future = this.connector.connect(new InetSocketAddress(host, port));

		} else {
			future = this.connector.connect(new InetSocketAddress(host, port), new InetSocketAddress(localIP, 0));
		}

		future.awaitUninterruptibly();

		this.session = future.getSession();

		// TODO
		this.session.setAttribute("lock", null);
	}

	public boolean isLockOk() {
		return false;
	}

	public void doLock() throws InterruptedException {

	}

	public boolean sendLoginMessage(String userName, String password) {

		// TODO ����login ��Ϣ
		this.session.write(null);

		return true;
	}

	public boolean sendLogoutMessage() {

		// TODO ����logout ��Ϣ
		this.session.write(null);

		return true;
	}

	public void sendMarketMessage(String type) {

		// TODO ����ʵ����Ϣ
		this.session.write(null);
	}

	public void dispose() {

		try {
			if (this.session != null) {
				this.session.close(true);
			}

			if (this.connector != null) {
				this.connector.dispose(true);
			}

			int i = 0;
			while ((!this.connector.isDisposed()) && (i++ < 15)) {
				try {
					Thread.sleep(2000L);
				} catch (InterruptedException e) {

				}
			}

			if (this.connector.isDisposed()) {
				// TODO
			} else {
				// TODO
			}

		} catch (Exception e) {
			// TODO: server.handle exception
		}
	}

}
