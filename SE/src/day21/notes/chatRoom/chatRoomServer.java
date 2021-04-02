package day21.notes.chatRoom;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class chatRoomServer {

    public static void main(String[] args) throws IOException {
        System.out.println("聊天室服务器启动---------------->");
        //创建服务器对象
        ServerSocket ss = new ServerSocket(9999);
        //创建客户端链接对象多对多
        while (true) {
            Socket accept = ss.accept();
            new ThreadRead(accept).start();
            if (2 > 3) {
                break;
            }
        }
        ss.close();
    }

}
