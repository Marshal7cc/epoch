package com.marshal.epoch.learn.io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author Marshal
 * @date 2019/12/24
 * @desc
 */
public class IoClient {

    public static void main(String[] args) {
        // TODO 创建多个线程，模拟多个客户端连接服务端
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 3333);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();

    }

}
