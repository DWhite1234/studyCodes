# 1.jdbc初级版
```java
	// 1.加载驱动
	Class.forName("....")
	// 2.数据库链接三要素
	String url=""
	String username=""
	String password=""
	// 3.获取连接
	Connection conn = DriverManager.getConnection(url,username,password);
	// 4.sql语句
	String sql=""
	// 5.创建sql执行对象
	PreparedStatement ps = conn.getPreparedStatement();
	// 6.executeUpdate执行DML sql,返回受影响行数
	Int rows = ps.executeUpdate(sql);
```

# 2.jdbc批处理
```java 
	String sql = "select * from test where id=?";
	for(int i=0;i<10;i++){
		ps.setInt(1,i);
		ps.addBatch();
	}
	int[] rows =  ps.executeBatch();
```

# 3.jdbc结果集
```java
	ResultSet rs = ps.executeQuery(sql);
	while(rs.next()){
		User user = new User();
		user.setId(rs.getInt(1));
		user.setName(rs.getString(2));
	}
```
# 4.jdbc开启事务
```java
	//关闭自动提交
	try{
		conn.setAutoCommit(fasle);	
		....
		conn.commit();
	}catch(Exception e){
		e.printStackTrace();
		conn.rollback();
	}
```

# 5.jdbc优化-->提供conn和配置配置文件
```java	
	private static Properties p = new Properties();
	// 1.读取配置文件(属性集本身的load(),加载当前模块下的配置文件)
	static{
		p.load(new FileInputStream(new File("db.properties")));
	}

	// 2.内存流当前线程加载
	static{
		InputStream stream =Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
		p.load(stream);
	}

	// 3.提供获取连接的方法
	public static Connection getConnection(){
		//加载驱动
		Class.forName(p.getProperty("jdbc.driver"));
		return DriverManager.getConnection(p.getProperty("jdbc.url"),p.getProperty("jdbc.username"),p.getProperty("jdbc.password"));
	}
```

# 6.Druid连接池
```java
	private static DataSource ds =null;
	private static Properties p = new Properties();

	//读取配置文件,创建连接池对象
	static{
		InputStream stream =Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
		p.load(stream);
		ds = DruidDataSourceFactory.createDataSource();
	}

	// 提供获取连接的方法
	public static Connection getConnection(){
		return ds.getConnection();
	}

	// 提供连接池对象
	public static DataSource getDataSource(){
		return ds;
	}
```

# 7.ApatchDBUtils
```java
	核心QueryRunner()对象,三种执行sql方法,返回三种结果集
		如果需要管理事务则不传递数据库连接池作为参数,单独获取连接,用来管理事务
		Connection conn = ApatchDBUtils.getConnection();
		conn.setAutoCommit(false);
		QueryRunner qr = new QueryRunner();
		如果不需要管理事务,则传递数据库连接池作为参数
		QueryRunner qr = new QueryRunner(ds);
	执行方法
		1.执行DML  qr.update(sql,...param)
		2.执行DQL  qr.query(sql,...param)
		3.执行批处理DML qr.batch(sql,param[][])

	返回结果集:
		1.单行单列  ScalarHandler<T>
		2.单行多列  BeanHandler<T>
		3.多行多列  BeanListHandler<T>

	注意点:
		执行DQL语句时,需要先创建结果集对象,执行sql方法需要结果集存放查询结果	

```	
