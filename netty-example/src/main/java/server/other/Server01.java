package server.other;

import server.handle.other.DiscardServerHandler;
import server.handle.other.DiscardServerHandlerAnother;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server01 {

	private int port;

	public Server01(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup(2); // (1)
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap(); // (2)
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) // (3)
					.handler(new SimpleServerHandler()) //自定义
					.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
						@Override
						public void initChannel(SocketChannel ch) throws Exception {

							ch.pipeline().addLast(new DiscardServerHandler());
							ch.pipeline().addLast(new DiscardServerHandlerAnother());
							ch.pipeline().addFirst(new Output1());
							ch.pipeline().addFirst(new Output2());
						}
					}).option(ChannelOption.SO_BACKLOG, 128) // (5)
					.childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

			// Bind and start to accept incoming connections.
			ChannelFuture f = b.bind(8083).sync(); // (7)
			ChannelFuture f1 = b.bind(8082).sync(); // (7)
			// Wait until the server socket is closed.
			// In this example, this does not happen, but you can do that to
			// gracefully
			// shut down your server.
			f.channel().closeFuture().sync();
			f1.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	@ChannelHandler.Sharable
	private static class SimpleServerHandler extends ChannelInboundHandlerAdapter {
		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			System.out.println("channelActive");
		}

		@Override
		public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
			System.out.println("channelRegistered");
		}

		@Override
		public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
			System.out.println("handlerAdded");
		}
	}
	@ChannelHandler.Sharable
	public static class Output1 extends ChannelOutboundHandlerAdapter{
		@Override
		public void flush(ChannelHandlerContext ctx) throws Exception {

			System.out.println("output1 flush");
			ctx.flush();
		}
	}
	@ChannelHandler.Sharable
	public static class Output2 extends ChannelOutboundHandlerAdapter{
		@Override
		public void flush(ChannelHandlerContext ctx) throws Exception {

			System.out.println("output2 flush");
			super.flush(ctx);
		}
	}
	public static void main(String[] args) throws Exception {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 8080;
		}
		new Server01(port).run();
	}

}
