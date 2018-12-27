package com.myapplication.myandroiddemo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ${KZJ} on 2018/12/24.
 */
public class SocketServer {
    public static void main(String[] args) {
        try {
            //新建ServerSocket对象，创建指定端口的连接
            ServerSocket serverSocket = new ServerSocket(12306);
            //进行监听
            Socket socket = serverSocket.accept();
            //拿到输入流
            InputStream inputStream = socket.getInputStream();
            //解析数据
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String s = null;
            StringBuffer sb = new StringBuffer();
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s);
            }
            System.out.println("服务器：" + sb.toString());

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(("我是服务端，客户端给我的数据是" + sb.toString()).getBytes());
            outputStream.flush();
            //关闭输出流
            socket.shutdownOutput();
            outputStream.close();

            //关闭io资源
            //注意：
            //在使用TCP编程的时候，最后需要释放资源，关闭socket(socket.close())；
            // 关闭socket输入输出流（socket.shutdownInput()以及socket.shutdownOutput()）；
            // 关闭IO流(is.close() os.close())。
            // 需要注意的是：关闭socket的输入输出流需要放在关闭io流之前。因为，
            // <u>**关闭IO流会同时关闭socket，一旦关闭了socket的，就不能再进行socket的相关操作了。
            // 而，只关闭socket输入输出流（socket.shutdownInput()以及socket.shutdownOutput()）不会完全关闭socket，
            // 此时任然可以进行socket方面的操作。  **</u>所以要先调用socket.shutdownXXX，然后再调用io.close();
            bufferedReader.close();
            reader.close();
            inputStream.close();

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
