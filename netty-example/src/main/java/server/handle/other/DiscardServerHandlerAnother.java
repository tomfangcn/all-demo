package server.handle.other;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DiscardServerHandlerAnother extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		System.out.println("read 2");
		// ((ByteBuf) msg).release();
//		try {
			// Do something with msg
//			ctx.write(msg);
//			ctx.flush();
//		} finally {
//			ReferenceCountUtil.release(msg);
//		}

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
