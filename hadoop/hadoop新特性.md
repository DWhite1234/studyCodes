# 1.hadoop 2.x的新特性
1.集群间的数据拷贝
distcp 
hdfs://hadoop102:8020/opt/module/hadoop 
hdfs://hadoop103:8020/opt/module/

2.小文档归档
har归档
 hadoop archive -archiveName input.har -p 需要归档的文件路径 归档到哪里去
查看
 hadoop fs -ls har:///归档目录
还原
 hadoop fs -cp 归档目录 目标目录  

3.回收站
默认core-site.xml中fs.trash.interval=0,表示不开启回收站
开启回收站 
    fs.trash.interval=10表示开启回收站,并且每隔10分清空一次
检测间隔
    fs.trash.checkpoint.interval=10,检测间隔一般等于清空时间
回收站目录
    在hdfs集群中/user/atguigu/.Trash/
    注意:
        1.在网页上删除的数据不经过回收站
        2.在代码中删除的数据不经过回收站
        3.只有用命令行删除的数据经过回收站
        4.还原数据的方法mv/cp

# 2.hadoop 3.x新特性
1.纠删码
    查看集群支持的纠删码策略hdfs ec -listPolicies                

