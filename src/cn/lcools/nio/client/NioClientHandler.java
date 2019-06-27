package cn.lcools.nio.client;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by liushuai3 on 2019/6/27.
 * 客服端线程类，专门来接收服务器的响应数据
 */
public class NioClientHandler implements Runnable{
    private Selector selector;

    public NioClientHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
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
                     * 如果是 可读事件
                     * */
                    if(selectionKey.isReadable()){
                        readHandler(selectionKey,selector);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
         * 循环读取服务端的响应数据
         * */
        String response = "";
        while (socketChannel.read(byteBuffer)>0){
            /**
             * 切换buffer为读模式
             * */
            byteBuffer.flip();
            /**
             * 读取buffer的内容
             * */
            response += Charset.forName("UTF-8").decode(byteBuffer);
        }

        /**
         *将channel再次注册到selector上，监听他的可读事件
         * */
        socketChannel.register(selector,SelectionKey.OP_READ);

        /**
         * 消息打印到本地
         * */
        if(response.length()>0){
            System.out.println(response);
        }
    }
}
