# 1.session-cluster
    特点:需要提前启动flink集群,提前向yarn申请一定的资源,并且常驻,以后的任务都向这里提交

    优点:适用于需要频繁提交的小job,并且执行时间都不长,

    缺点:如果提交的作用有需要长时间作业的大job,则会占用该flink的所有资源,后续的job则无法继续提交


    使用方式:
    1.bin/yarn-session.sh -d   (提前启动一个flink集群)
    2.bin/flink run -c 全类名 jar(如果不指定yarn-session集群,会自动寻找也可以使用Dyarn.application.id=application_XXXX_YY 指定启动的flink集群)

# 2.per-job-cluster
    特点:每次提交job,都会重新向yarn申请资源,创建一个新的flink集群,并且任务结束后释放资源,不会影响别的job的执行

    优点:适用于需要长时间作业的大job
    缺点:每次提交job都会申请和释放资源,会占用一定的时间

    生产环境中使用:per-job-cluster

    使用方式:
    bin/flink run -d -t yarn-per-job -c 全类名 jar (旧版提交)

    提交任务到其他队列
    bin/flink run -d -m yarn-cluster -yqu hive -c 全类名 jar(旧版提交)

    bin/flink run -d -t yarn-per-job -Dyarn.application.queue=hive -c 全类名 jar(新版提交)
# 3.Application-mode-cluster
    原理与per-job-cluster一样
    区别:Application-mode-cluster的main函数执行在集群上

    使用方式:
    bin/flink run-application -t yarn-application -c 全类名 jar

    提交任务到其他队列
    bin/flink run-application -t yarn-application -Dyarn.application.queue=hive -c 全类名 jar


# 4.yarn模式高可用和standalone高可用的区别
区别:
yarn模式:只启动一个,当他挂了,yarn会在别的节点上再次启动一个
standalone模式:同时启动多个JobManager,一个为leader,其他的为standby,leader挂掉,其他的中一个会成为leader

yarn模式高可用的原理:
在yarn-site.xml设置的是尝试次数(上限),flink-conf.xml中的值应该小于他,只有在一定的时间内尝试次数大于设置的上限时,才会真正挂掉   