package com.cxl.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author cxl
 * @Date 6/6/2023 09:17
 * @ClassReference: com.cxl.bio.BIOServer
 * @Description: 使用cmd的telnet命令来访问，win11没有就网上找一下
 *
 * telnet 127.0.0.1 6666
 * 之后按`ctrl+]`
 * 输入 send xxx 服务器即可收到传来的数据
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {

        // 线程池机制

        // 思路
        // 1.创建一个线程池
        // 2.如果有客户端连接，就创建一个线程，与之通讯（单独写一个方法）

        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        // 创建一个ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了");

        while (true) {
            System.out.println("线程信息 id=" + Thread.currentThread().getId() + " 名字" +
                    Thread.currentThread().getName());
            // 监听，等待客户端连接
            System.out.println("等待连接");
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            // 创建线程
            newCachedThreadPool.execute(() -> {
                // 可以和客户端通讯
                handler(socket);
            });
        }
    }

    // 编写一个handler方法，和客户端通讯
    public static void handler(Socket socket) {
        try {
            System.out.println("线程信息 id=" + Thread.currentThread().getId() + " 名字" +
                    Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            // 通过socket获取输入流
            InputStream inputStream = socket.getInputStream();

            // 循环的读取客户端发送的数据
            while (true) {
                System.out.println("线程信息 id=" + Thread.currentThread().getId() + " 名字" +
                        Thread.currentThread().getName());

                System.out.println("read...");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    // 输出客户端发送的数据
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
