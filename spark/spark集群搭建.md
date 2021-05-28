# 1.本地模式
直接解压使用即可,不需要更改配置文件
直接运行Pi案例:
bin/spark-submit \
<!-- 指定运行案例的class类 -->
--class org.apache.spark.examples.SparkPi \
<!-- 指定运行模式是本地,还是cluster
 
    1.--master local[2]:不加这一行参数默认使用本地所有cpu运行
    2.local[2]:指定本地模式使用2个cpu
    3.local[*]:本地模式使用所有cpu
 -->
--master local[2] \
<!-- 指定jar -->
./examples/jars/spark-examples_2.12-3.0.0.jar \
<!-- 要运行程序的输入参数 -->
10

# 2.standalone(自带调度框架)
 1.修改slaves.template配置文件
    mv slaves.template slaves
    添加集群节点
    hadoop101
    hadoop102
    hadoop103
 2.修改spark-env.sh,添加master节点(单点启动)
    vim spark-env.sh
    添加参数
    SPARK_MASTER_HOST=hadoop101
    SPARK_MASTER_PORT=7077
 3.配置历史服务器,修改spark-default.conf.template
    mv spark-defaults.conf.template spark-defaults.conf

    spark.eventLog.enabled          true
    spark.eventLog.dir              hdfs://hadoop102:8020/directory

 4.配置HA,修改spark-env.sh
    vim spark-env.sh
    添加参数
    export SPARK_DAEMON_JAVA_OPTS="
    -Dspark.deploy.recoveryMode=ZOOKEEPER 
    -Dspark.deploy.zookeeper.url=hadoop102,hadoop103,hadoop101 
    -Dspark.deploy.zookeeper.dir=/spark"

    更改冲突端口
    #Zookeeper3.5的AdminServer默认端口是8080，和Spark的WebUI冲突
    export SPARK_MASTER_WEBUI_PORT=8989
    
 3.分发,启动集群
    sbin/start-all.sh
 4.跑任务
    bin/spark-shell \
    --master spark://hadoop101:7077 \
    <!-- 指定运行的内存 -->
    --executor-memory 2g \
    <!-- 指定运行的核心数 -->
    --total-executor-cores 2
    <!-- 指定集群模式,不写默认客户端模式 -->
    --deploy-mode cluster \
    <!-- 指定运行的jar -->
    ./examples/jars/spark-examples_2.12-3.0.0.jar \
    <!-- jar 运行参数 -->
    10


 5.网页查看
    master:hadoop101:8989
    日志服务器:hadoop:18080


# 3.yarn模式(最常用)
 1.修改spark-env.sh
 配置spart任务运行在yarn上
 YARN_CONF_DIR=/opt/module/hadoop-3.1.3/etc/hadoop

 配置日志读取  
 export SPARK_HISTORY_OPTS="
 -Dspark.history.ui.port=18080 
 -Dspark.history.fs.logDirectory=hdfs://hadoop102:8020/directory 
 -Dspark.history.retainedApplications=30" 


 2.修改spark-default.conf
 配置日志存储
 spark.eventLog.enabled          true
 spark.eventLog.dir               hdfs://hadoop102:8020/directory
 
 配置yarn点击history自动跳转到spark 18080页面上   
 spark.yarn.historyServer.address=hadoop102:18080
 spark.history.ui.port=18080

 
 
 3.执行任务
 bin/spark-submit \
 --class org.apache.spark.examples.SparkPi \
 --master yarn \
 ./examples/jars/spark-examples_2.12-3.0.0.jar \
 10


    





