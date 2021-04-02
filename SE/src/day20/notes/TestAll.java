package day20.notes;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TestAll {
    /**
     * 字节流(音视频文件等,没有缓冲区)
     * inputStream
     * outputStream
     */
    @Test
    public void test01() throws FileNotFoundException {

        try (
           OutputStream os = new FileOutputStream("D:/b.txt");
           InputStream is = new FileInputStream("D:/a.txt");
        ) {
            File f = new File("D:/b.txt");
            //读取数据
            byte[] by = new byte[10];
            int len;
            while ((len = is.read(by)) != -1) {
                //写数据
                os.write(by, 0, len);
                System.out.println("f.length() = " + f.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符流(大多用于纯文本,不带格式,有缓冲区)
     *  Reader
     *  Writer
     */
    @Test
    public void test02(){
        try(
                Reader r=new FileReader("D:/a.txt");
                Writer w =new FileWriter("D:/b.txt")
        ){
            File f = new File("D:/b.txt");
            char[] ch = new char[10];
            int len;
            while ((len = r.read(ch)) != -1) {
                w.write(ch, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 缓冲字节流(有默认缓冲区,8192,可以自定义缓冲区大小)
     * BufferedInputSteam(file,size)
     * BufferedOutputStream(file,size)
     */
    @Test
    public void test03() {
        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:/a.txt"));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:/b.txt"))
        ) {
            byte[] by = new byte[10];
            int len;
            while ((len = bis.read(by)) != -1) {
                bos.write(by, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 缓冲字符流(有默认缓冲区,8192,可以自定义缓冲区大小)
     * BufferedReader(file,size)
     * BufferedWriter(file,size)
     */
    @Test
    public void test04(){
        try (
                BufferedReader br = new BufferedReader(new FileReader("D:/a.txt"));
                BufferedWriter bw = new BufferedWriter(new FileWriter("D:/c.txt"))
        ) {
            char[] ch = new char[10];
            int len;
            while ((len = br.read(ch)) != -1) {
                bw.write(ch, 0, len);
                bw.newLine();//换行
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转换流(只能将字节转换为字符,不能字符转换为字节,有缓冲区)
     *  字节-->>字符流
     *
     */
    @Test
    public void test05() {
        try(
                InputStreamReader isr = new InputStreamReader(new FileInputStream("D:/z.txt"), "gbk");
                OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("D:/x.txt"), StandardCharsets.UTF_8)
        ){
            File f = new File("D:/x.txt");
            char[] ch = new char[10];
            int len;
            while ((len = isr.read(ch)) != -1) {
                System.out.println("f.length() = " + f.length());
                osw.write(ch, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据流(二进制的形式 存储在磁盘上
     *    只有字节流 没有字符流
     *     常人无法识别内容,
     *     数据流存的文件,只有数据流能读,
     *     没有缓冲区)
     * DataInputStream
     * DataOutputStream
     */
    @Test
    public void test06() {
        try(
                DataInputStream dis = new DataInputStream(new FileInputStream("D:/a.txt"));
                DataOutputStream dos = new DataOutputStream(new FileOutputStream("D:/e.txt"))
        ){
            File f = new File("D:/e.txt");
            byte[] by = new byte[10];
            int len;
            while ((len = dis.read(by)) != -1) {
                System.out.println("f.length() = " + f.length());
                dos.write(by, 0,len );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读指定的数据类型的时候,必须读写顺序一致
     */
    @Test
    public void test07() {
        try(
//                DataOutputStream dos = new DataOutputStream(new FileOutputStream("D:/f.txt"));
                DataInputStream dis = new DataInputStream(new FileInputStream("D:/f.txt"));
        ){
//            dos.writeChar('比');
//            dos.writeBoolean(false);
            char c = dis.readChar();
            boolean b = dis.readBoolean();
            System.out.println("b = " + b);
            System.out.println("c = " + c);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
