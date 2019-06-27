package cn.lcools.nio.client;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Created by liushuai3 on 2019/6/27.
 */
public class NioClient {

    public void start(String nickname) throws Exception{
        /**
        * 连接服务器端
        * */
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8888));

        /**
         * 接收服务器发来的消息
         * 新开线程，专门负责接收服务器的响应数据
         * */
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new NioClientHandler(selector)).start();


        /**
         * 向服务器发送消息
         * */
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String request = scanner.nextLine();
            if(request!=null&&request.length()>0){
                socketChannel.write(Charset.forName("UTF-8").encode(nickname+":"+request));
            }
        }
    }

    public static void main(String[] args) throws Exception{
        //NioClient nioClient = new NioClient();
        //nioClient.start();
    }
}
