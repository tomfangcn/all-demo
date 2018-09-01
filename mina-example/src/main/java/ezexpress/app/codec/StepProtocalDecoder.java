package ezexpress.app.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class StepProtocalDecoder extends CumulativeProtocolDecoder {

	private Charset charset;
	private int msgPerLength;

	public StepProtocalDecoder(Charset charset) {
		super();
		this.charset = charset;
	}

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {

		// 读取的位置
		int position = 0;

		// 数据足够
		while (in.remaining() > 0) {
			byte current = in.get();

			// 每条消息结束
			if ('\n' == current) {

				FastMessageExpress msg = new FastMessageExpress();

				// TODO 解析数据
				out.write(msg);
				// 返回true，CumulativeProtocolDecoder会继续调用此方法，解析数据
				return true;
			}

		}

		// 如果数据不够，等待Iobuffer累积数据
		return false;
	}

}
