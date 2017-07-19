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
			//����Bootstrap
			Bootstrap b = new Bootstrap();
			//ָ��EventLoopGroup�Դ���ͻ����¼�,��Ҫ������NIO��ʵ��
			b.group(group)
				.channel(NioSocketChannel.class) //������NIO�����Channel����
				.remoteAddress(new InetSocketAddress(host, port)) //���÷�������InetSocketAddress
				.handler(new ChannelInitializer<SocketChannel>(){ //����CHannelʱ,��ChannelPipeline�����һ��EchoClientHandlerʵ��

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						// TODO Auto-generated method stub
						ch.pipeline().addLast(new EchoClientHandler());
					}
					
				});
			ChannelFuture f= b.connect().sync(); //���ӵ�Զ�̽ڵ�,�����ȴ�ֱ���������
			f.channel().closeFuture().sync(); //����,֪��Channel�ر�
		} finally {
			group.shutdownGracefully().sync(); //�ر��̳߳ز����ͷ����е���Դ
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
