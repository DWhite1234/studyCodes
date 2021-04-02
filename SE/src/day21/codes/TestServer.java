package day21.codes;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestServer {
    public static void main(String[] args) throws IOException {
        System.out.println("服务端启动");
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket accept = serverSocket.accept();
        //读取客户端的信息
        InputStream inputStream = accept.getInputStream();
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        System.out.println("br.readLine() = " + s);

        //返回信息
        OutputStream outputStream = accept.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bw = new BufferedWriter(outputStreamWriter);

        StringBuilder stringBuilder = new StringBuilder(s);
        System.out.println("stringBuilder.toString() = " + stringBuilder.toString());
        bw.write("返回消息:"+stringBuilder.reverse().toString());

        bw.close();
        br.close();
        serverSocket.close();
    }
}
