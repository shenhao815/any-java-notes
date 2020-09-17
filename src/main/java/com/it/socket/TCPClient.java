package com.it.socket;

import java.io.*;
import java.net.Socket;

/**
 * @author ch
 * @date 2020-9-17
 * @description  与TCPServer一起模拟一个基于socket的文件上传下载功能
 */

public class TCPClient {

    public static void main(String[] args) throws IOException {
        // 1.创建一个本地字节输入流fileInputStream对象，构造方法中绑定要读取的数据源
        FileInputStream fis = new FileInputStream("c:\\1.jpg");
        // 2.创建一个忘记端Socket对象，构造方法中绑定服务器的IP地址和端口号
        Socket socket = new Socket("127.0.0.1", 8888);
        // 3.使用Socket中的方法getOutputStream，获取网络字节输出流OutputStream对象
        OutputStream os = socket.getOutputStream();
        // 4.使用本地字节输入流FileInputStream对象中的方法read,读取本地文件
        int len = 0;
        byte[] bytes = new byte[1024];
        // 5.使用网络字节输出流OutputStream对象中的方法write，把读取到的文件上传到服务器
        while ((len = fis.read(bytes)) != -1) {
            os.write(bytes,0,len);
        }
        socket.shutdownOutput();
        // 6.使用Socket中的方法getInputStream,获取网络字节输入流InputStream对象
        InputStream is = socket.getInputStream();
        // 7.使用网络字节输入流InputStream对象中的方法read读取服务器回写的数据
        while ((len = is.read(bytes)) != -1) {
            System.out.println(new String(bytes,0,len));
        }
        // 8.释放资源
        fis.close();
        socket.close();
    }
}
