package day21.codes;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/*
客户端向服务端发送一条信息
你好  123
服务端将客户端发来的消息 反转后返回
好你  321
 */
public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("-------------------Server-------------------");
        //1.创建服务端对象
        ServerSocket serverSocket = new ServerSocket(9999);

        //2.获取来连接的客户端socket
        Socket socket = serverSocket.accept();
        //3.接收客户端发来的数据 输入流
        InputStream inputStream = socket.getInputStream();
         //3.1使用字符缓冲流来接收数据
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        //读取数据
        String s = br.readLine();
        //4.对数据进行反转
        //创建StringBuilder对象
        StringBuilder sbl = new StringBuilder(s);
        StringBuilder reverse = sbl.reverse();

        String result = reverse.toString();

        System.out.println("客户端发来的数据反转后的结果"+result);
        //5.将数据发给客户端 输出流
        OutputStream outputStream = socket.getOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        ps.println(result);
        //6.关闭资源

        ps.close();
        br.close();
        serverSocket.close();

    }
}