package com.atguigu.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;

public class HBaseDMLUtils {
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
     * 创建namespace
     * @param nameSpace
     * @return
     * @throws IOException
     */
    public static Boolean createNameSpace(String nameSpace) throws IOException {
        Admin admin = conn.getAdmin();
        NamespaceDescriptor.Builder builder = NamespaceDescriptor.create(nameSpace);
        NamespaceDescriptor descriptor = builder.build();
        boolean flag = false;
        try {
            admin.createNamespace(descriptor);
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 判断表是否存在
     * @param nameSpace
     * @param tableName
     * @return
     */
    public static boolean tableIsExists(String nameSpace, String tableName) throws IOException {
        Admin admin = conn.getAdmin();
        return admin.tableExists(TableName.valueOf(nameSpace,tableName));
    }

    /**
     * 创建表
     * @param namespace
     * @param tableName
     * @param columnFamilies
     * @throws IOException
     */
    public static boolean createTable(String namespace,String tableName,String...columnFamilies) throws IOException {
        Admin admin = conn.getAdmin();
        //2.判断列族是否存在
        if (columnFamilies.length<1) {
            System.out.println("至少声明一个列族");
            return false;
        }
        //3.表格是否存在
        if (admin.tableExists(TableName.valueOf(namespace,tableName))) {
            System.out.println("表已存在");
            return false;
        }
        //先获取表的建造者
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(namespace,tableName));
        ArrayList<ColumnFamilyDescriptor> families = new ArrayList<>();
        for (String columnFamily : columnFamilies) {
            //获取列族的建造者
            ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(columnFamily));
            ColumnFamilyDescriptor columnBuild = columnFamilyDescriptorBuilder.build();
            families.add(columnBuild);
        }
        TableDescriptor build = tableDescriptorBuilder.setColumnFamilies(families).build();
        admin.createTable(build);
        admin.close();
        return true;
    }

    /**
     * 删除表
     * @param namespace
     * @param tableName
     * @param columnFamily
     * @param versions
     * @return
     * @throws IOException
     */
    public static boolean upDateTable(String namespace,String tableName,String columnFamily,int versions) throws IOException {
        //获取指定表的descriptor
        Admin admin = conn.getAdmin();
        //根据namespace和tableName获取原来的表的描述
        TableDescriptor tableDescriptor = admin.getDescriptor(TableName.valueOf(namespace, tableName));
        //根据指定的列族获取列族的描述
        ColumnFamilyDescriptor tableDescriptorColumnFamily = tableDescriptor.getColumnFamily(Bytes.toBytes(columnFamily));
        //因为获取来的描述无法直接set属性,获取根据列族描述获取新的列族描述建造者
        ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder(tableDescriptorColumnFamily);
        //通过建造者来修改该列族描述的属性
        ColumnFamilyDescriptorBuilder setMaxVersions = columnFamilyDescriptorBuilder.setMaxVersions(versions);
        //完成属性修改
        ColumnFamilyDescriptor build = setMaxVersions.build();
        admin.modifyColumnFamily( TableName.valueOf(namespace, tableName),build );
        return true;
    }

    /**
     * 删除表
     * @param namespace
     * @param tableName
     * @return
     * @throws IOException
     */
    public static boolean deleteTable(String namespace,String tableName) throws IOException {
        Admin admin = conn.getAdmin();
        admin.disableTable(TableName.valueOf(namespace, tableName));
        admin.deleteTable(TableName.valueOf(namespace, tableName));
        return true;
    }
    public static void main(String[] args) throws IOException {
        //测试创建连接
//        System.out.println(conn);
        //测试创建namespace
//        System.out.println("HBaseUtils.createNameSpace(\"ddddd\") = " + HBaseUtils.createNameSpace("ddddd"));
        //测试判断表是否存在
//        System.out.println(HBaseUtils.tableIsExists("bigdata2", "student2"));
        //测试创建表
//        System.out.println("createTable(\"bigdata\",\"stuInfo\",\"info\",\"age\",\"msg\") = " + createTable("bigdata", "stuInfo", "info", "age", "msg"));
        //测试修改表
//        System.out.println("upDateTable(\"bigdata\",\"stuInfo\",\"msg\",5) = " + upDateTable("bigdata", "stuInfo", "msg", 5));
        //测试删除表
        System.out.println("deleteTable(\"bigdata\", \"stu\") = " + deleteTable("bigdata", "stu"));
    }

    public void connectionClose() throws IOException {
        if (conn != null) {
            conn.close();
        }
    }
}
