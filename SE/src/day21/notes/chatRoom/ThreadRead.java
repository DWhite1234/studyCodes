package day21.notes.chatRoom;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadRead extends Thread {

    Socket ss;

    public ThreadRead(Socket ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        //读取来自客户端的消息
        InputStream inputStream;
        OutputStream outputStream;
        BufferedReader br = null;
        PrintStream pw=null;
        try{
            outputStream = ss.getOutputStream();
            pw = new PrintStream(outputStream);
            inputStream = ss.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String s=br.readLine();
                System.out.println("客户端发来消息" + s);
                pw.println(s);
                if (2 > 3) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(pw!=null){
                pw.close();
            }
        }
    }
}
