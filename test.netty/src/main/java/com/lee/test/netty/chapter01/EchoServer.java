package com.lee.test.netty.chapter01;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
	private final int port;

	public EchoServer(int port) {
		super();
		this.port = port;
	}
	
	public static void main(String[] args) throws Exception {
		if(args.length != 1){
			System.err.println("Usage: " + EchoServer.class.getSimpleName() + "<port>");
			return;
		}
		//设置端口值(如果端口参数格式不正确,将抛出一个NumberFormatException)
		int port = Integer.parseInt(args[0]);
		new EchoServer(port).start(); //调用服务器的start方法
	}
	
	public void start() throws Exception{
		final EchoServerHandler serverHandler = new EchoServerHandler();
		//创建EventLoopGroup
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			//创建ServerBootstrap
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
				.channel(NioServerSocketChannel.class) //指定所使用的NIO传输Channel
				.localAddress(new InetSocketAddress(port)) //使用指定的端口设置套接字地址
				.childHandler(new ChannelInitializer<SocketChannel>(){
					//添加一个EchoServerHandler到子Channel的ChannelPipeline
					@Override
					protected void initChannel(SocketChannel channel) throws Exception {
						//EchoServerHandler被标注为@Shareable,所以我们可以总是使用同样的实例
						channel.pipeline().addLast(serverHandler);
					}
					
				});
			//异步绑定服务器;调用sync方法阻塞等待直到绑定完成
			ChannelFuture f = b.bind().sync();
			//获取channel的CloseFuture,并阻塞当前线程直到它完成.
			f.channel().closeFuture().sync();
			
		} finally {
			//关闭EventLoopGroup.释放所有资源
			group.shutdownGracefully().sync();
		}
	}

}
