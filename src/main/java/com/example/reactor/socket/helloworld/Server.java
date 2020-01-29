package com.example.reactor.socket.helloworld;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class Server {
    public static void main(String[] args) {
        final int DEFAULT_PORT = 8888;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
            log.info("服务器启动 监听端口:{}", DEFAULT_PORT);

            //waiting for Client connection
            while (true) {
                // accept()阻塞当前线程，等待客户端链接
                Socket socket = serverSocket.accept();
                log.info("客户端 {} 接入服务", socket.getPort());

                //读取客户端发送来的数据, bufferedReader是装饰器模式，注意其new的方式
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                //写数据给客户端
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                //读取客户端发送的消息
                String msg = bufferedReader.readLine();

                //避免输入流正常或异常关闭
                if (msg != null) {
                    log.info("读取到 客户端 port: {}, 消息内容: {}", socket.getPort(), msg);
                    //回复客户端收到消息
                    bufferedWriter.write("服务器收到消息: [" + msg + "] ");
                    //保证缓冲区发送所有消息
                    bufferedWriter.flush();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                    log.info("关闭服务端socket");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
