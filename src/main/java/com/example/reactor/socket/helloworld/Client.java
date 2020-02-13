package com.example.reactor.socket.helloworld;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

@Slf4j
public class Client {
    public static void main(String[] args) {

        final String QUIT = "quit";
        final String DEFAULT_SERVER_HOST = "127.0.0.1";
        final int DEFAULT_SERVER_PORT = 8888;

        Socket socket = null;

        BufferedWriter bufferedWriter = null;

        try {
            //创建socket
            socket = new Socket(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);

            //创建 I/O, 读取服务端发来的消息, 拿客户端自己的输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //回消息
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //等待用户输入信息
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String input = consoleReader.readLine();
                //发送消息给服务器
                bufferedWriter.write(input + "\n");
                bufferedWriter.flush();

                //读取服务器返回的消息
                String msg = bufferedReader.readLine();
                log.info("读取服务器消息: {}", msg);
                //用户退出
                if (QUIT.equals(input)){
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //直接关闭外层的writer, 内部会自动执行flush, 也就是自己会关闭对应的socket
                assert bufferedWriter != null;
                bufferedWriter.close();
                log.info("关闭客户端 writer/socket");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
