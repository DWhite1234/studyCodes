# 参数解读
<!-- 必须指定的参数 -->
channel     指定channe
type        指定hdfs
hdfs.path   指定hdfs的路径(hdfs://hadoop101:8020/flume/data)
<!-- 文件前后缀 -->
hdfs.filePrefix         文件前缀(默认FlumeData)
hdfs.fileSuffix         文件后缀
hdfs.inUsePrefix        临时文件前缀
hdfs.inUseSuffix        临时文件后缀(默认.tmp)
hdfs.emptyInUseSuffix   临时文件后缀是否为空(默认false)--基本不改
<!-- 文件滚动 -->
hdfs.rollInterval       将临时文件滚动成正式文件(默认30s) 
hdfs.rollSize           滚动文件大小(默认1024,单位字节)--应该改成128M或比128M稍小 
hdfs.rollCount          event个数进行滚动(默认10)--不好用,设置成0表示不根据event的个数进行滚动
<!-- 文件夹滚动,使用占位符 hdfs://hadoop101:8020/flume/%Y-%m-%d-->
hdfs.round              文件夹滚动开启(默认false)
hdfs.roundValue         文件夹滚动间隔(默认1)
hdfs.roundUnit          文件夹滚懂间隔单位(默认秒)
<!-- 使用本地时间戳 -->
hdfs.useLocalTimeStamp  默认false
<!-- 设置文件类型防止乱码 -->
hdfs.fileType = DataStream


注意点:
    hdfs 的文件滚动方式有两种,按文件大小滚动和时间转移序列滚动;
    时间转移序列滚动方式 要求必须设置header中timestamp属性,但是如果使用的是拦截器的时间,就会造成数据的产生时间与进入的分区不一致的文件,所以必须自定义拦截器设置timestamp属性

