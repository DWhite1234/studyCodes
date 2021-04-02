package day21.codes;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Test3 {
    public static void main(String[] args) throws IOException {
        System.out.println("客户端启动=-----------");
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        //向服务端写消息,采用最简单的打印流,别的流需要调用别的方法完成在不关闭流的情况下刷新
        OutputStream outputStream = socket.getOutputStream();

        PrintStream ps = new PrintStream(outputStream);
        ps.println("你好啊");

        //读取服务端返回消息
        InputStream inputStream = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        System.out.println("服务端返回消息:"+s);

        br.close();
        ps.close();

    }
}
