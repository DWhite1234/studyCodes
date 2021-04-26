package com.atguigu.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * 所有客户端都是一个套路
 * 1.创建客户端对象
 * 2.使用客户端进行一些操作
 * 3.关闭客户端对象
 * String connectString  连接字符串
 * sessionTimeout 超时时长
 */
public class ZkClient {

    private ZooKeeper zkCli;
    @Before
    public void init() throws IOException, InterruptedException, KeeperException {
        String connectString="hadoop102:2181,hadoop103:2181,hadoop103:2181";
        int sessionTimeout=5000;
        zkCli = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
            }
        });

    }
    @After
   public void close() throws InterruptedException {
       zkCli.close();
   }
   @Test
   //获取子节点 但是不监听
   public void ls() throws KeeperException, InterruptedException {
       List<String> children = zkCli.getChildren("/", false);
       System.out.println(children);
   }
   @Test
   //获取子节点并且监听
    public void lsAndWatch() throws KeeperException, InterruptedException {
       List<String> children = zkCli.getChildren("/xiyouji", new Watcher() {
           @Override
           public void process(WatchedEvent event) {
               System.out.println(event);
               System.out.println("0225 is best of atguigu");
           }
       });
       System.out.println(children);
      Thread.sleep(Long.MAX_VALUE);
   }


   public void lsAndWatch(String path) throws KeeperException, InterruptedException {
       List<String> children = zkCli.getChildren(path, new Watcher() {
           @Override
           public void process(WatchedEvent event) {
               System.out.println(event);
               System.out.println("0225 is best of atguigu");
               try {
                   lsAndWatch(path);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       });
       System.out.println(children);
   }
   @Test
   public void testLsAndWatch() throws KeeperException, InterruptedException {
        lsAndWatch("/xiyouji");
       Thread.sleep(Long.MAX_VALUE);
   }
   @Test
   public void create() throws KeeperException, InterruptedException {
        //zkCli.create("/xiyouji/xiawujing","jldj".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        //zkCli.create("/xiyouji/xiawujing","jldj".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL);
        //zkCli.create("/xiyouji/liumowang","tieshan".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        zkCli.create("/xiyouji/liumowang","tieshan".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
        Thread.sleep(Long.MAX_VALUE);
   }
   @Test
   //获取节点的值并且不监听
   public void get() throws KeeperException, InterruptedException {
        //如果写的糙一点
       //byte[] data = zkCli.getData("/xiyouji/xiawujing", false, null);
       //System.out.println(new String(data));
       //如果细一点
       String path="/xiyouji";
       Stat stat = zkCli.exists(path, false);
       if (stat==null){
           System.out.println("你所查询的节点不存在，检查后输入");
           return;
       }
       byte[] data = zkCli.getData(path, false, stat);
       System.out.println(new String(data));
   }
   //获取节点的值并且监听
    @Test
    public void getAndWatch() throws KeeperException, InterruptedException {
        String path="/xiyouji";
        Stat stat = zkCli.exists(path, false);
        if (stat==null){
            System.out.println("你所查询的节点不存在，检查后输入");
            return;
        }
        byte[] data = zkCli.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event);
            }
        }, stat);
        System.out.println(new String(data));
        Thread.sleep(Long.MAX_VALUE);
    }
    @Test
    //设置节点的值
    public void set() throws KeeperException, InterruptedException {
        //错误的
        //zkCli.setData("/xiyouji","ycnrg".getBytes(),5);
        //如果糙一点
        //zkCli.setData("/xiyouji","ycnrggg".getBytes(),-1);
        //如果细一点
        Stat stat = zkCli.exists("/xiyouji", false);
        if (stat==null){
            System.out.println("你所查询的节点不存在，检查后输入");
            return;
        }
        zkCli.setData("/xiyouji","ycnrttt".getBytes(),stat.getVersion());
    }
    @Test
    public void delete() throws KeeperException, InterruptedException {
        String path="/xiyouji";
        Stat stat = zkCli.exists(path, false);
        if (stat==null){
            System.out.println("你所查询的节点不存在，检查后输入");
            return;
        }
        zkCli.delete(path,stat.getVersion());

    }

    public void delteAll(String path) throws KeeperException, InterruptedException {
        Stat stat = zkCli.exists(path, false);
        if (stat==null){
            System.out.println("你所查询的节点不存在，检查后输入");
            return;
        }
        List<String> children = zkCli.getChildren(path, false);
        for (String child : children) {
            delteAll(path+"/"+child);
        }
        zkCli.delete(path,stat.getVersion());
    }
    @Test
    public void testDeleteAll() throws KeeperException, InterruptedException {
        delteAll("/xiyouji");
    }
}
