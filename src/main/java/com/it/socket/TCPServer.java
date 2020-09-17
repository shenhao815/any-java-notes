package com.it.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ch
 * @date 2020-9-17
 * @description 文件上传的服务器端
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        File file = new File("d:\\upload");
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file + "\\1.jpg");
        InputStream is = socket.getInputStream();
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = is.read(bytes)) != -1) {
            fos.write(bytes,0,len);
        }
        OutputStream os = socket.getOutputStream();
        os.write("上传成功".getBytes());
        fos.close();
        socket.close();
        serverSocket.close();
    }
}
