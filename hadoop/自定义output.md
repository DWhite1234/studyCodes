# 1.位置
1.自定义的output处于reduce数据处理完毕,开始调用write写出
# 2.怎么自定义
```java
//1.自定义输出流,继承FileOutputFormat
    public class MyOutput extends FileOutputFormat<Text, IntWritable> {
        //重写getRecordWriter()方法
        @Override
        public RecordWriter<Text, IntWritable> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
            //需要使用job获取内容,一并传下去,返回值需要RecordWriter类型
            CustRecordWriter cw = new CustRecordWriter(job);
            return cw;
        }
    }
//3.创建一个自定义CustRecordWriter继承这个RecordWriter类,
   public class CustRecordWriter extends RecordWriter<Text, IntWritable> {
    //构造传参,使用文件系统开启多个输出流,对应多个不同的文件名
    public CustRecordWriter(TaskAttemptContext job) throws IOException {
        conf = job.getConfiguration();
        fs = FileSystem.get(conf);
        fdos1 = fs.create(new Path("d:/output/customOutput1"));
        fdos2 = fs.create(new Path("d:/output/customOutput2"));
    }
    //重写写出的write方法
    @Override
    public void write(Text key, IntWritable value) throws IOException, InterruptedException {
        System.out.println(key.toString());
        System.out.println(value.toString());
        //输出不同的文件
        fdos1.writeBytes(key.toString());
        fdos2.writeBytes(key.toString());
    }

    //必须关流,否则内存中的数据写不出去,就被销毁,使用提供好的IOUtils关流
    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStreams(fdos2,fdos1);
    }
   } 
```