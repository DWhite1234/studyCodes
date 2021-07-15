
<!-- 创建表,只需要声明列族,如果不声明VERSIONS,VERSIONS默认为1 -->
create 'namespace名字:表名' ,{NAME=>'列族名',VERSIONS=>'版本数'}
<!-- 创建多列族的表 -->
create 'namespace名字:表名' ,{NAME=>'列族名'},{NAME=>'列族名'},{NAME=>'列族名'}   
<!-- 展示所有的表 -->
list
<!-- 修改单个列族属性 -->
alter '表名',NAME=>'列族名',VERSIONS=>'5'
<!-- 修改多个列族属性-->
alter 'student2',{NAME=>'info',VERSIONS=>'10'},{NAME=>'msg',VERSIONS=>'10'}
<!-- 增加列族 -->
alter '表名',NAME=>'要添加的列族名'
<!-- 删除列族 -->
alter '表名','delete'=>'要删除的列族名'
<!-- 删除表 -->
    <!-- 删除表必须先让表处于disable状态 -->
    ERROR: Table bigdata:student is enabled. Disable it first.

    disable '要删除的表名'
    drop '要删除的表名'
<!-- 为指定列族添加数据 -->
     表名     rowkey  列族名:字段名  value
put 'student2','1001','info:age','10'

<!-- 获取指定列族名数据,默认展示最新版本的一个数据-->
     表名     rowkey  列族名:字段名
get 'student2','1001','info:name'
<!-- 获取指定列族名数据,指定版本数 -->
get 'student2','1001',{COLUMN=>'info:age',VERSIONS=>5}
<!-- 删除一个最新的版本数据 -->
delete 'student2','1001','info:name'
<!-- 删除所有数据 -->
deleteall 'student2','1001','info:name'
<!-- 读取一行数据startrow-stoprow -->
scan '表名','rowkey',{STARTROW=>'',STOPROW=>''}
<!-- 扫描全表 -->
scan '表名'

注意:
     为了防止phoenix创建的表在hbase shell中列名显示异常,在创建表的时候加上COLUMN_ENCODED_BYTES= 0