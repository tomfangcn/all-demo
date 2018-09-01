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

		// ��ȡ��λ��
		int position = 0;

		// �����㹻
		while (in.remaining() > 0) {
			byte current = in.get();

			// ÿ����Ϣ����
			if ('\n' == current) {

				FastMessageExpress msg = new FastMessageExpress();

				// TODO ��������
				out.write(msg);
				// ����true��CumulativeProtocolDecoder��������ô˷�������������
				return true;
			}

		}

		// ������ݲ������ȴ�Iobuffer�ۻ�����
		return false;
	}

}
