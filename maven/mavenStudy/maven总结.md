# 1.maven的配置
```xml
<!-- 配置本地仓库的地址 -->
	 <localRepository>E:\developInstall\localRepository</localRepository>
<!-- 配置阿里镜像 -->
	<mirrors>
		<mirror>
	       <id>alimaven</id>
	       <name>aliyun maven</name>
	       <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
	       <mirrorOf>central</mirrorOf>        
	     </mirror>
	 </mirrors>
```

# 2.maven的使用
## 1.maven依赖的基本使用
```xml
	<dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.10</version>
        </dependency>
    </dependencies>
```
## 2.依赖的范围
```xml
	<dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.10</version>

            <!-- 设置依赖的范围 -->
            <!-- test只有测试类可见,src源代码不可见,测试类不需要部署到服务器上 -->
            <scope>test</scope>
            <!--  provided,代码和测试类都可见,服务器没有该jar,但是servlet提供了该jar,不需要部署上去作为依赖-->
            <scope>provided</scope>
            <!--  默认值 代码和测试类都可见,服务器没有该jar,需要该jar部署作为依赖-->
            <scope>compile</scope>


        </dependency>
    </dependencies>
```

## 3.依赖的传递性
```txt
	有且仅有 compile 范围的(依赖范围默认值),具有传递性
```

## 4.依赖的原则
```txt
	1.路径最短者优先
	2.相同路径先声明者优先(按照声明依赖的顺序)
```

## 5.依赖的统一管理
```xml
	<properties>
		<!-- 自定义标签名,标签中写值 -->
		<!-- <自定义标签>值</自定义标签> -->
		<value>4.1.1</value>
		<!-- 使用时,是用${自定义标签名} -->
		<!-- <version>${value}</version> -->
	</properties>

	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-context</artifactId>
	    <version>${value}</version>
	</dependency>
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-webmvc</artifactId>
	    <version>${value}</version>
	</dependency>
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-jdbc</artifactId>
	    <version>${value}</version>
	</dependency>
	
```

## 5.依赖的排除
```xml
	<dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>RELEASE</version>
        <scope>test</scope>
        <!-- 可以排除多个依赖 -->
        <exclusions>
        	<!-- 排除单个依赖 -->
            <exclusion>
                <groupId>org.apache.logging.log4j</groupId>
                <artifactId>log4j</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
```

# 3.依赖的继承
父工程
```xml
<!-- 父工程的坐标,子工程需要指定 -->
    <groupId>org.example</groupId>
    <artifactId>parent</artifactId>
    <version>1.0-SNAPSHOT</version>

<!--    指定打包方式-->
    <packaging>pom</packaging>
<!-- 统一管理子类依赖,父类不进行代码的编写,仅仅管理依赖-->
<!--    注意点:管理子类依赖时,必须优先声明 打包方式为pom,否则子类报错-->
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.10</version>
        </dependency>
    </dependencies>
```

子工程
```xml
<!-- 在子工程中指定父工程 -->
	<parent>
        <groupId>org.example</groupId>
        <artifactId>parent</artifactId>
        <version>1.0-SNAPSHOT</version>
        <relativePath>../parent/pom.xml</relativePath>
    </parent> 
```

# 4.聚合以及批量打包(包含依赖jar,也有不包含依赖jar),
```xml
<!-- 打包插件 -->
	<build>
        <plugins>
            <plugin>
                <artifactId>maven-assembly-plugin</artifactId>
                <configuration>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
    <!-- 让maven 来处理复杂的依赖关系,不需要指定顺序 -->
    <modules>
        <module>../son1</module>
        <module>../son2</module>
        <module>../son3</module>
    </modules>
```


