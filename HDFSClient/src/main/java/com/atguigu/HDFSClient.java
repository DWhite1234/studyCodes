package com.atguigu;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;


public class HDFSClient {

    private FileSystem fs;

    @Before
    public void hdfsInit() throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication", "2");
        fs = FileSystem.get(new URI("hdfs://hadoop101:8020"),configuration , "zt");
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
     * 移动和重命名
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
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        System.out.println("Arrays.toString(fileStatuses) = " + Arrays.toString(fileStatuses));
    }

    @After
    public void hdfsClose() throws IOException {
        fs.close();
    }

}
