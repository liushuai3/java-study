package cn.lcools.nio.client;

/**
 * Created by liushuai3 on 2019/6/27.
 */
public class BClient {

    public static void main(String[] args) throws Exception{
        NioClient nioClient = new NioClient();
        nioClient.start("BClient");
    }
}
