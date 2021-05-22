# RegionServer架构


RegionServer:{
    WAL:预写日志
    Block Cache:读缓存
    Region:{
        Store:{
            MemStore:写缓存
            StoreFile:实际存储在hdfs上的文件
        }
        Store:{
            MemStore:写缓存
            StoreFile:实际存储在hdfs上的文件
        }
    }
    Region:{
        Store:{
            MemStore:写缓存
            StoreFile:实际存储在hdfs上的文件
        }
        Store:{
            MemStore:写缓存
            StoreFile:实际存储在hdfs上的文件
        }
    }
}