# javaPractice


###  amino 打包进本地仓库

```
mvn install:install-file -DgroupId=org.amino  -DartifactId=amino  -Dversion=0.2.0 -Dpackaging=jar -Dfile=cbbs-0.2.0.jar 

```

###  添加本地依赖

```
<dependency>
    <groupId>org.amino</groupId>
    <artifactId>amino</artifactId>
    <version>0.2.0</version>
</dependency>
        
 ```
 
 
 * amino 给我们提供了无锁的并发数据结构
   
  1. LockFreeList
  2. LockFreeOrderedList (提供了有序的共享集合序列 List)