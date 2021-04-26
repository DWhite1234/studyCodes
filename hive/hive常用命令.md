# hadoop -e "sql"
可以不进入客户端执行sql命令

# hadoop -f "sql.txt"
可以执行sql的文件

# 查看历史命令
1.hive的历史命令
    cd ~/bin 
    ls -la
    cat .history
2.jdbc的历史命令
    cd ~/bin/.beeline
    cat history
