package com.atguigu.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HBaseDDLUtils {
    public static Connection conn;

    static {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop101,hadoop102,hadoop103");
        try {
            conn = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入数据
     * @param namespace
     * @param tableName
     * @param rowkey
     * @param columnFamily
     * @param column
     * @param value
     * @return
     * @throws IOException
     */
    public static boolean insert(String namespace, String tableName, String rowkey,String columnFamily, String column, String value) throws IOException {
        //获取表
        Table table = conn.getTable(TableName.valueOf(namespace, tableName));
        //创建put对象
        Put put = new Put(Bytes.toBytes(rowkey));
        //添加属性
        put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value));
        table.put(put);
        table.close();
        return true;
    }

    /**
     * 删除数据
     * @param namespace
     * @param tableName
     * @param rowkey
     * @param columnFamily
     * @param column
     * @return
     * @throws IOException
     */
    public static boolean delete(String namespace, String tableName, String rowkey,String columnFamily, String column) throws IOException {
        Table table = conn.getTable(TableName.valueOf(namespace, tableName));
        Delete delete = new Delete(Bytes.toBytes(rowkey));
        //删除单个版本
        delete.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
        //删所有版本
        //delete.addColumns(Bytes.toBytes(columnFamily), Bytes.toBytes(column);
        //删除列族
        //delete.addFamily(Bytes.toBytes(columnFamily));
        table.delete(delete);
        table.close();
        return true;
    }

    /**
     * 查询数据
     * @param namespace
     * @param tableName
     * @param rowkey
     * @param columnFamily
     * @param column
     * @return
     * @throws IOException
     */
    public static boolean query(String namespace, String tableName, String rowkey,String columnFamily, String column) throws IOException {
        Table table = conn.getTable(TableName.valueOf(namespace, tableName));
        Get get = new Get(Bytes.toBytes(rowkey));
        get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
        Result result = table.get(get);
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            String row = new String(CellUtil.cloneRow(cell));
            String family = new String(CellUtil.cloneFamily(cell));
            String colu = new String(CellUtil.cloneQualifier(cell));
            String val = new String(CellUtil.cloneValue(cell));
            System.out.println("row:"+row+"\t"+"family:"+family+"\t"+"column:"+colu+"\t"+"val:"+val);
        }
        return true;
    }

    /**
     * scan数据
     * @param namespace
     * @param tableName
     * @param start
     * @param stop
     * @return
     * @throws IOException
     */
    public static boolean scan(String namespace,String tableName,String start,String stop) throws IOException {
        Table table = conn.getTable(TableName.valueOf(namespace, tableName));
        Scan scan = new Scan().withStartRow(Bytes.toBytes(start), true).withStopRow(Bytes.toBytes(stop), true);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                String row = new String(CellUtil.cloneRow(cell));
                String family = new String(CellUtil.cloneFamily(cell));
                String colu = new String(CellUtil.cloneQualifier(cell));
                String val = new String(CellUtil.cloneValue(cell));
                System.out.println("row:"+row+"\t"+"family:"+family+"\t"+"column:"+colu+"\t"+"val:"+val);
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        //测试插入数据
//        for (int i = 0; i < 3; i++) {
//            insert("bigdata","stuInfo", "1004", "info", "age", "18"+i);
//        }
        //测试删除数据
//        System.out.println("delete(\"bigdata\", \"sutInfo\", \"1001\", \"info\", \"name\") = " + delete("bigdata", "stuInfo", "1001", "info", "name"));
        //测试查询数据
//        query("bigdata","stuInfo", "1004", "info", "age");
        scan("bigdata","stuInfo","1001","1003");
    }
}
