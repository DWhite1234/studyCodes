# 1.MapReducer编码规范
	一个MapReducer由三个类组成,驱动类,mapper类,reducer类
	WordCountDriver:wordcount案例的主程序
	WordCountMapper:wordcount的map阶段,继承Mapper<>类
	WordCountReducer:wordcount的reducer阶段,继承Reducer<>类

# 2.WordCountMapper
```java	
/*
根据需求自定义map阶段输入,输出的(k,v)的数据类型

输入(k,v)
LongWritable
Text

输出(k,v)
Text
IntWritable
*/

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private Text keyIn;
    private IntWritable keyOut;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        keyIn=new Text();
        keyOut=new IntWritable(1);
    }

	//重写map方法
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		//map逻辑
        String[] words = value.toString().split(" ");
        for (String word : words) {
            keyIn.set(word);
            context.write(keyIn, keyOut);
        }
    }
}
```	

# 3.WordCountReducer
```java
/*
根据需求自定义reduce阶段输入,输出的(k,v)的数据类型
reduce阶段输入和map阶段的输出必然相同

输入(k,v)
LongWritable
Text

输出(k,v)
Text
IntWritable
*/
public class WordCountReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    private final IntWritable keyOut = new IntWritable();

    //重写reduce方法
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
    	//reducer阶段逻辑
        int sum=0;
        for (IntWritable value : values) {
            sum+=value.get();
        }
        keyOut.set(sum);
        context.write(key, keyOut);
    }
}
```


# 4.WordCountDriver
```java
public class WordCount {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        
        //1.创建configuration配置对象
        Configuration conf = new Configuration();
        //2.创建job对象
        Job job = Job.getInstance(conf);
        //3.指定主程序
        job.setJarBycLss(WordCount.Class);
        //4.指定mapper,reducer运行的类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        //5.指定map的输出参数类型
        job.setMapOuputKeyClass(LongWritable.class);
        job.setMapOuputValueClass(Text.class);
        //6.指定最终输出参数类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //7.指定输入输出路径
        FileInputFormat.setInputPaths(job,new Path("...."));
        FileOutputFormat.setOutputPaths(job,new Path("...."))
        //8.提交任务,等待完成,系统退出
        Boolean b = job.waitForComletion(true);
        System.exit(b?0:1);
    }
}
```