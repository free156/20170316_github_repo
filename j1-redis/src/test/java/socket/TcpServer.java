/*
package socket;

import com.j1.util.NumberUtils;
import com.j1.util.PortUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

*/
/**
 * Created by che2 on 2016/10/31.
 *//*

public class TcpServer {

    private int port;

    private String host;

    private static final EventLoopGroup bossGroup ;// = new NioEventLoopGroup();
    private static final EventLoopGroup workerGroup ;// = new NioEventLoopGroup();

    static {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup(*/
/*NumberUtils.parseIntWithDefault(App.shareInstance().props
                .getProperty("WORK_SOCKET_NUM"), 50)*//*
);
    }

    public TcpServer(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public  void run()throws  Exception{
        if(PortUtils.isPortUsing(port)){
            return;
        }

        ServerBootstrap b=new ServerBootstrap();
        b.group(bossGroup,workerGroup);
        b.option(ChannelOption.SO_TIMEOUT, 6000);

        b.option(ChannelOption.TCP_NODELAY, true);

        b.childOption(ChannelOption.SO_KEEPALIVE, true);

        b.channel(NioServerSocketChannel.class);

       */
/* b.childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                // System.out.println("new connects");
                //超时处理 60秒踢出
                pipeline.addLast("idleStateHandler", new IdleStateHandler(
                        NumberUtils.parseIntWithDefault(App.shareInstance().props
                                .getProperty("READER_IDLE"), 60),
                        NumberUtils.parseIntWithDefault(App.shareInstance().props
                                .getProperty("WRITER_IDLE"), 60),
                        0));
                pipeline.addLast("timeoutHandler", new TimeoutHandler());
                // 解码 将byte[]转换成自定义的消息
                pipeline.addLast("frameDecoder",
                        new LengthFieldBasedFrameDecoder(1024 * 5, 0, 4, -4, 0));
                // pipeline.addLast("bufDecoder", new TcpDecoder());
                // 编码 将自定义的消息转换成byte[]
                // pipeline.addLast("protobufEncoder", new TcpDecoder());
                pipeline.addLast(new TcpServerHander());
            }
        });*//*


        ChannelFuture future = b.bind(host, port).sync();
        future.channel().closeFuture().sync();
    }

}
*/
