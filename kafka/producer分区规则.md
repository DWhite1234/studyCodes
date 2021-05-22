# producer分区规则
1.指定partition
2.指定key,使用key的hash值与分区数取模
3.既不指定key也不指定partition,使用粘性分区