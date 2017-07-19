package com.lee.test.netty.chapter01;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {
	private final String host;
	private final int port;
	
	public EchoClient(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}
	
	public void start() throws InterruptedException{
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			//创建Bootstrap
			Bootstrap b = new Bootstrap();
			//指定EventLoopGroup以处理客户端事件,需要适用于NIO的实现
			b.group(group)
				.channel(NioSocketChannel.class) //适用于NIO传输的Channel类型
				.remoteAddress(new InetSocketAddress(host, port)) //设置服务器的InetSocketAddress
				.handler(new ChannelInitializer<SocketChannel>(){ //创建CHannel时,向ChannelPipeline中添加一个EchoClientHandler实例

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						// TODO Auto-generated method stub
						ch.pipeline().addLast(new EchoClientHandler());
					}
					
				});
			ChannelFuture f= b.connect().sync(); //连接到远程节点,阻塞等待直到连接完成
			f.channel().closeFuture().sync(); //阻塞,知道Channel关闭
		} finally {
			group.shutdownGracefully().sync(); //关闭线程池并且释放所有的资源
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		if(args.length != 2){
			System.err.println("Usage: " + EchoClient.class.getSimpleName() + "<host><port>");
			return;
		}
		String host= args[0];
		int port = Integer.parseInt(args[1]);
		new EchoClient(host, port).start();
	}
}
