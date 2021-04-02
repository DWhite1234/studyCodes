package day21.codes;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("-------------------Client-------------------");
        //1.创建客户端对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        //2.写出数据 到服务端 输出流
        OutputStream outputStream = socket.getOutputStream();
        PrintStream ps = new PrintStream(outputStream);

//        Scanner in = new Scanner(System.in);
//        System.out.println("请问您要发送什么数据");
//
//        String s1 = in.next();

        ps.println("666666");
        //3.接收服务端的反馈 输入流
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String s = reader.readLine();
        System.out.println("服务端反馈的数据是：\t"+s);
        //4.关闭资源
        reader.close();
        ps.close();
    }
}