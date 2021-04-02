package day21.notes.chatRoom;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadWriter extends Thread {
    Socket ss;

    public ThreadWriter(Socket ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        OutputStream outputStream;
        try {
            outputStream = ss.getOutputStream();
            PrintWriter pw = new PrintWriter(outputStream);
            Scanner scanner = new Scanner(System.in);
            String str;
            while (true) {
                System.out.println("这是聊天室--->聊天区域");
                str=scanner.next();
                pw.write(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
