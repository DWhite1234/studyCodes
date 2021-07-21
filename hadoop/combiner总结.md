# 1.combiner和Reducer的区别
```txt
    1.Combiner是MR中Mapper和Reducer之外的一个组件
    2.Combiner的组件的父类是Reducer
    3.Combiner运行与每个MapTask上,而reducer是接受所有的MapTask的运算结果,进行
    聚合
    4.Combiner的作用是对每一个的MapTask的输出提前进行局部汇总,减小网络传输
   *5.最重要的是Combiner虽然有这些优点,但是使用的前提是不能影响最终结果

   比如:算平均数
   MapTask1:
    3 + 5 + 7  15/3=5
   MapTask2:
    2 + 6      8/2=4 

    最终结果:
        5+4 /2 =4.5
        真正的正确结果是(3 + 5 + 7 + 2 + 6) /5=4.6 
        结果不正确,再大的优势也没有用  

```

# 2.重点关注Combiner的泛型
```java
public class PartCombiner extends Reducer<PartBean, Text,PartBean, Text>{
  private PartBean partBean = new PartBean();

  @Override
    protected void reduce(PartBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            context.write(key, value);
        }
    }
}

/*
1.combiner也继承于reducer 也有四个泛型,每两个一对
2.第一对和mapper的输出参数相同
3.第二对和reducer的输出参数相同
4.重写方法的逻辑与reducer相同
5.当reducer的泛型满足条件时,可以不重新定义Combiner类,直接指定reducer为Combiner运行类即可
/*

```