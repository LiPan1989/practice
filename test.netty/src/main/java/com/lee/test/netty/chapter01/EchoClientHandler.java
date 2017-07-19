package com.lee.test.netty.chapter01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.CharsetUtil;

@Sharable //��Ǹ����ʵ�����Ա����Channel����
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		//��¼�ѽ�����Ϣ��ת��
		System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//����֪ͨChannel�ǻ�Ծ��ʱ��,����һ����Ϣ
		ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		//�����쳣��ӡ�쳣���ر�channel
		cause.printStackTrace();
		ctx.close();
	}

}
