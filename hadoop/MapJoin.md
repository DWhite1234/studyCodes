# 1.什么叫reduceJoin
和sql中join类似,在map阶段将多表中的指定字段替换或者其他操作
所以不需要启动reduce,设置job.setNumReduceTasks(1)

原理:先提前将小文件缓存起来,需要的时候以流的形式读取并存储,然后按照逻辑操作对应的多表数据
适用场景:一张表十分小、一张表很大的场景

# 2.注意点
1.在steup方法中读取缓存文件并存储
```java
    URI[] cacheFiles = context.getCacheFiles();
    URI cacheFile = cacheFiles[0];
    FileSystem fs = FileSystem.get(context.getConfiguration());
    //文件系统创建输入流
    fdis = fs.open(new Path(cacheFile));
    //文件系统创建的FSDataInputStream流,不能一次读取一行,需要做转换
    reader = new BufferedReader(new ReaderUTF8(fdis));
    String line ;
    while ((line= reader.readLine())!=null){
        String[] s = line.split("\t");
        map.put(s[0],s[1]);
```

2.根据对应的逻辑操作
3.关流
4.main方法中添加缓存文件并声明reduce的数量为1
```java
job.setNumReduceTasks(1);
//uri类型必须显示声明文件协议
job.addCacheFile(new URI("file:///D:/input/inputtablecache/pd.txt"));
```