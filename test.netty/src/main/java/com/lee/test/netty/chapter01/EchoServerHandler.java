package com.lee.test.netty.chapter01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable //��ʾһ��ChannelHandler���Ա����Channel��ȫ�Ĺ���
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		//����Ϣ��¼������̨
		System.out.println("Server receivved: " + in.toString(CharsetUtil.UTF_8));
		//�����յ�����Ϣд��������,������ˢ��վ��Ϣ
		ctx.write(in);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		//��δ����Ϣ��ˢ��Զ�̽ڵ�,���ҹرո�Channel
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace(); //��ӡ���쳣
		ctx.close(); //�ر�Channel
	}

}
