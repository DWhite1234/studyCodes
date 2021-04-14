package com.atguigu;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;


public class HDFSClient {

    private FileSystem fs;
    private Configuration configuration;

    @Before
    public void hdfsInit() throws URISyntaxException, IOException, InterruptedException {
        configuration = new Configuration();
        configuration.set("dfs.replication", "2");
        fs = FileSystem.get(new URI("hdfs://hadoop101:8020"), configuration, "zt");
    }


    /**
     * 创建文件夹
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void hdfsClient() throws URISyntaxException, IOException, InterruptedException {
        fs.mkdirs(new Path("/xiyouji/huaguoshan"));
    }

    /**
     * 1.上传文件
     * 2.参数优先级
     * configuration.set("dfs.replication", "2")
     * >idea配置文件hdfs-site.xml
     * >hadoop hdfs-site.xml
     * >hadoop hdfs-default.xml
     * @throws IOException
     */
    @Test
    public void testPut() throws IOException {
        fs.copyFromLocalFile(false, true, new Path("E:\\联想电脑管家壁纸.txt"), new Path("/xiyouji/huaguoshan/"));
    }

    /**
     * 下载文件
     * @throws IOException
     */
    @Test
    public void testGet() throws IOException {
        fs.copyToLocalFile(false, new Path("/xiyouji/huaguoshan/联想电脑管家壁纸.txt"), new Path("D:\\"),false);
    }

    /**
     * 效果与linux rm一样
     * 删除
     * @throws IOException
     */
    @Test
    public void testRm() throws IOException {
        //可以删除文件,或空目录,或非空目录,第二个参数代表是否递归删除
        fs.delete(new Path("/xiyouji"), true);
    }

    /**
     * 移动和重命名刷卡机服务费不可无还款了解到FBALKDFJAKSDFJBASKFSADFASDFASDF
     * 效果与linux mv一样
     * @throws IOException
     */
    @Test
    public void testMV() throws IOException {
        fs.rename(new Path("/sanguo/zhangfei2.txt"), new Path("/sanguo/zhangfei.txt"));
    }

    /**
     * 获取文件信息
     * @throws IOException
     */
    @Test
    public void testProperties() throws IOException {
        RemoteIterator<LocatedFileStatus> iterator = fs.listFiles(new Path("/"), true);
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getPath());
        }
    }

    /**
     * 判断是文件还是目录
     * @throws IOException
     */
    @Test
    public void testFileOrDir() throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        System.out.println("Arrays.toString(fileStatuses) = " + Arrays.toString(fileStatuses));
        for (FileStatus status : fileStatuses) {

            System.out.println(status.isDirectory()+"_______"+(status.isDirectory() ? "是目录" : "是文件"));
        }
    }

    /**
     * 上传
     */
    @Test
    public void testIO() throws IOException {
        //开启本地输入流
        FileInputStream fis = new FileInputStream("E:\\联想电脑管家壁纸.txt");
        //开启hdfs输出流
        FSDataOutputStream fdos = fs.create(new Path("/联想电脑管家壁纸.txt"));
        //流的对拷
        IOUtils.copyBytes(fis, fdos, configuration);
        //流的关闭
        IOUtils.closeStreams(fdos,fis);
    }

    /**
     * 下载
     *
     * @throws IOException
     */
    @Test
    public void testIODownload() throws IOException {
        //开启hdfs输入流
        FSDataInputStream fdis = fs.open(new Path("/联想电脑管家壁纸.txt"));
        //开启本地输出流
        FileOutputStream fos = new FileOutputStream("d:/联想电脑管家壁纸.txt");
        //流的对拷
        IOUtils.copyBytes(fdis, fos, configuration);
        //
        IOUtils.closeStreams(fos,fdis);
    }

    @After
    public void hdfsClose() throws IOException {
        fs.close();
    }

}
