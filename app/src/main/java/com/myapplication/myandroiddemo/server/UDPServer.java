package com.myapplication.myandroiddemo.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by ${KZJ} on 2018/12/25.
 */
public class UDPServer {
    public static void main(String[] args) throws IOException {

        byte[] buf = new byte[1024];
        // 一：接受数据
        // 1.创建接受数据的数据包
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        // 2.创建UPD 的 socket
        DatagramSocket socket = new DatagramSocket(12307);
        // 3.接收数据
        System.out.println("服务端开始监听！~~~~");
        socket.receive(packet);
        // 4.处理数据
        System.out.println("服务端：" + new String(buf, 0, buf.length));

        // 二：返回数据
        DatagramPacket p = new DatagramPacket(buf, buf.length, packet.getAddress(), packet.getPort());
        socket.send(p);
        socket.close();
    }
}
