package day21.notes.chatRoom;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class chatRoomClient {

    public static void main(String[] args) throws IOException {
        System.out.println("客户端-------_-----------");
        //创建客户端对象
        Socket so = new Socket(InetAddress.getLocalHost(), 9999);

        //创建输出流
        OutputStream outputStream = so.getOutputStream();
        PrintStream pw = new PrintStream(outputStream);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入聊天内容");

        //创建输入流
        InputStream inputStream = so.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        while (true) {
            String next = scanner.next();
            String s =InetAddress.getLocalHost()+"-->"+next;
            pw.println(s);
            if (next == "stop") {
                break;
            }

            System.out.println("服务端消息:" + br.readLine());
        }
        pw.close();
        br.close();
    }

}
