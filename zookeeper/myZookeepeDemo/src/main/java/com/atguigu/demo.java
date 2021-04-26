package com.atguigu;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.acl.Acl;
import java.sql.Time;
import java.util.List;

public class demo {

    private ZooKeeper client;

    @Before
    public void client() throws IOException, InterruptedException, KeeperException {
        String connect = "hadoop101:2181,hadoop102:2181,hadoop103:2181";
        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("执行了回调函数"+event.toString());
            }
        };
        client = new ZooKeeper(connect, 1000, watcher);
    }

    /**
     * 递归显示子节点,不带监视
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Test
    public void getChildren() throws InterruptedException, KeeperException {
        getNodes("/");
    }

    public void getNodes(String path) throws InterruptedException, KeeperException {
        List<String> children = client.getChildren("/", false);
        for (String child : children) {
            Stat exists = client.exists(path + child, false);
            if (exists!=null) {
                getNodes(path + child);
            }else{
                System.out.println(child);
            }
        }
    }

    /**
     * 递归显示子节点,带监视
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Test
    public void getChildrenWatcher() throws InterruptedException, KeeperException {
        getNodesWatcher("/");
        Thread.sleep(Long.MAX_VALUE);0
    }

    private void getNodesWatcher(String path) throws InterruptedException, KeeperException {
        List<String> children = client.getChildren(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event);
                try {
                    getNodesWatcher(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(children);
    }


    /**
     * 创建新节点并且赋值
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Test
    public void create() throws InterruptedException, KeeperException {
        client.create("/shuihuchuan/panjinlian", "大朗".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @After
    public void close() throws InterruptedException {
        client.close();
    }

}
