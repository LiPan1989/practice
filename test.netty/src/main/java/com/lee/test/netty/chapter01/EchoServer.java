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
		//���ö˿�ֵ(����˿ڲ�����ʽ����ȷ,���׳�һ��NumberFormatException)
		int port = Integer.parseInt(args[0]);
		new EchoServer(port).start(); //���÷�������start����
	}
	
	public void start() throws Exception{
		final EchoServerHandler serverHandler = new EchoServerHandler();
		//����EventLoopGroup
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			//����ServerBootstrap
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
				.channel(NioServerSocketChannel.class) //ָ����ʹ�õ�NIO����Channel
				.localAddress(new InetSocketAddress(port)) //ʹ��ָ���Ķ˿������׽��ֵ�ַ
				.childHandler(new ChannelInitializer<SocketChannel>(){
					//���һ��EchoServerHandler����Channel��ChannelPipeline
					@Override
					protected void initChannel(SocketChannel channel) throws Exception {
						//EchoServerHandler����עΪ@Shareable,�������ǿ�������ʹ��ͬ����ʵ��
						channel.pipeline().addLast(serverHandler);
					}
					
				});
			//�첽�󶨷�����;����sync���������ȴ�ֱ�������
			ChannelFuture f = b.bind().sync();
			//��ȡchannel��CloseFuture,��������ǰ�߳�ֱ�������.
			f.channel().closeFuture().sync();
			
		} finally {
			//�ر�EventLoopGroup.�ͷ�������Դ
			group.shutdownGracefully().sync();
		}
	}

}
