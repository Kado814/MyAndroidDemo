package com.myapplication.myandroiddemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.myapplication.myandroiddemo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SocketActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit;
    private Button submit;
    private TextView text_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        edit = findViewById(R.id.edit);
        submit = findViewById(R.id.submit);
        text_show = findViewById(R.id.text_show);

        submit.setOnClickListener(this);

    }

    private void sendMessage() {
        final String msg = edit.getText().toString().trim();
        if (TextUtils.isEmpty(msg)) {
            return;
        } else {
            edit.setText("");
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Socket socket = new Socket("172.18.188.124", 12306);
                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write(msg.getBytes());
                        outputStream.flush();
                        socket.shutdownOutput();

                        InputStream is = socket.getInputStream();
                        InputStreamReader reader = new InputStreamReader(is);
                        BufferedReader bufferedReader = new BufferedReader(reader);
                        String s = null;
                        final StringBuffer stringBuffer = new StringBuffer();
                        while ((s = bufferedReader.readLine()) != null) {
                            stringBuffer.append(s);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                text_show.setText(stringBuffer.toString());
                            }
                        });
                        bufferedReader.close();
                        reader.close();
                        is.close();
                        outputStream.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    private void udp() {
        final String msg = edit.getText().toString().trim();
        final byte[] bytes=msg.getBytes();
        if (TextUtils.isEmpty(msg)) {
            return;
        } else {
            edit.setText("");
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        /*******************发送数据***********************/
                        InetAddress address = InetAddress.getByName("172.18.188.124");
                        //1.构造数据包
                        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, 12307);
                        //2.创建数据报套接字并将其绑定到本地主机上的指定端口。
                        DatagramSocket socket = new DatagramSocket();
                        //3.从此套接字发送数据报包。
                        socket.send(packet);
                        /*******************接收数据***********************/
                        //1.构造 DatagramPacket，用来接收长度为 length 的数据包。
                        final byte[] bytes1 = new byte[1024];
                        DatagramPacket receiverPacket = new DatagramPacket(bytes1, bytes1.length);
                        socket.receive(receiverPacket);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                text_show.setText(new String(bytes1, 0, bytes1.length));
                            }
                        });

                        //            socket.close();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (SocketException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
//                sendMessage();
                udp();
                break;
        }
    }
}
