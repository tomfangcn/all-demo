package ezexpress.app.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class StepProtocalEncoder extends ProtocolEncoderAdapter {

	private Charset charset;

	public StepProtocalEncoder(Charset charset) {
		super();
		this.charset = charset;
	}

	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {

		// TODO ±àÂë

		out.write(message);

	}

}
