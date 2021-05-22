# store file 和region split
## store file
    目的:清除过期数据
    Minor Compaction:小合并,较小的HFile合并成一个较大的HFile,只能清理掉部分过期和删除的数据,Hbase自动进行的优化

    Major Compaction:会将一个Store下的所有的HFile合并成一个大HFile，并且会清理掉所有过期和删除的数据,不建议定期执行,机器耗费性能,推荐空闲时间手动执行
    
    执行命令:
        flust "表名"
        marjor_compact "表名"
## region split
    如果当前RegionServer上该表只有一个Region，按照2 * hbase.hregion.memstore.flush.size分裂，否则按照hbase.hregion.max.filesize分裂

    1.当RegionServer上该表只有一个Region,第一次split时,按照2*128M分裂
    2.当RegionServer上该表有多个Region,按照10G分裂