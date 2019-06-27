package cn.lcools.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by liushuai3 on 2019/6/26.
 * NIO服务器端
 */
public class NioServer {

    public void start() throws Exception{
        /**
        * 1. 创建Selector
        * */
        Selector selector = Selector.open();

        /**
         * 2. 通过ServerSocketChannel创建channel通道
         * */
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        /**
         * 3. 为channel通道绑定监听端口
         * */
        serverSocketChannel.bind(new InetSocketAddress(8888));

        /**
         * 4. 设置channel为非阻塞模式
         * */
        serverSocketChannel.configureBlocking(false);

        /**
         * 5. 将channel注册到Selector上，监听连接事件
         * */
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功");

        /**
         * 6. 循环等待新接入连接
         * */
        while (true){
            int readyChannels = selector.select();
            if(readyChannels==0){
                continue;
            }
            /**
             * 获取selector里可用的channel集合
             * */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = (SelectionKey)iterator.next();
                /**
                 * 移除Set中当前selectionKey
                 * */
                iterator.remove();

                /**
                 * 7. 根据就绪状态，调用对应的方法处理业务逻辑
                 * */
                /**
                 * 如果是 接入事件
                 * */
                if(selectionKey.isAcceptable()){
                    acceptHandler(serverSocketChannel,selector);
                }

                /**
                 * 如果是 可读事件
                 * */
                if(selectionKey.isReadable()){
                    readHandler(selectionKey,selector);
                }
            }
        }
    }

    /**
     * 接入事件处理器
     * */
    private void acceptHandler(ServerSocketChannel serverSocketChannel,Selector selector) throws Exception{
        /**
         * 如果是接入事件，创建socketChannel
         * */
        SocketChannel socketChannel = serverSocketChannel.accept();

        /**
         * 将socketChannel设置为非阻塞模式
         * */
        socketChannel.configureBlocking(false);

        /**
         * 将channel注册到selector上，监听 可读事件
         * */
        socketChannel.register(selector,SelectionKey.OP_READ);

        /**
         * 回复客户端提示信息
         * */
        socketChannel.write(Charset.forName("UTF-8").encode("你以接入聊天室！"));
    }

    private void readHandler(SelectionKey selectionKey, Selector selector) throws Exception{
        /**
         * 从 selectionKey 中获取到已经就绪的channel
         * */
        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();

        /**
         * 创建Buffer
         * */
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        /**
         * 循环读取客户端请求信息
         * */
        String request = "";
        while (socketChannel.read(byteBuffer)>0){
            /**
             * 切换buffer为读模式
             * */
            byteBuffer.flip();
            /**
             * 读取buffer的内容
             * */
            request += Charset.forName("UTF-8").decode(byteBuffer);


        }

        /**
         *将channel再次注册到selector上，监听他的可读事件
         * */
        socketChannel.register(selector,SelectionKey.OP_READ);

        /**
         * 广播消息给其它的channel
         * */
        if(request.length()>0){
            broadCast(selector,socketChannel,request);
        }
    }

    /**
     * 广播
     * */
    private void broadCast(Selector selector, SocketChannel sourceChannel, String request){
        /**
         * 获取所有已经接入的客户端的channel
         * */
        Set<SelectionKey> selectionKeySet = selector.keys();

        /**
         * 循环向所有channel广播消息
         * */
        selectionKeySet.forEach(selectionKey -> {
            Channel targetChannel = selectionKey.channel();
            /**
             * 剔除发消息的客服端
             * */
            if(targetChannel instanceof SocketChannel && targetChannel!= sourceChannel){
                try {
                    //将信息发送到targetChannel
                    ((SocketChannel) targetChannel).write(Charset.forName("UTF-8").encode(request));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public static void main(String[] args) throws Exception{
        NioServer server = new NioServer();
        server.start();
    }
}
