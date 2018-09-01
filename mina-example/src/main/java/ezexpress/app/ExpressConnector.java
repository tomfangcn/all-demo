package ezexpress.app;

import org.apache.mina.core.service.IoHandler;

public interface ExpressConnector {

	public abstract void initConnector(IoHandler paramIoHandler);

	public abstract void connect(String paramString, int paramInt1, int paramInt2) throws Exception;

	public abstract void connect(String paramString1, int paramInt1, String paramString2, int paramInt2)
			throws Exception;

	public abstract void dispose();

}
