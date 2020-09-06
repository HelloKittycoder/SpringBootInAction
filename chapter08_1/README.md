### liquibase使用教程
- liquibase版本：3.6.3（安装解压版的liquibase，并添加到环境变量中，另外将mysql驱动放到liquibase下的lib目录中）
- 相关环境要求（我的配置）：jdk1.8，maven3.5.0
- 数据库：mysql（工作中经常用到）

---
- #### **1.[使用liquibase操作mysql数据库](https://docs.liquibase.com/workflows/database-setup-tutorials/mysql.html)**
- （1）随便找个盘，如D盘，新建文件夹LiquibaseMySQL，在里面编写一个配置文件liquibase.properties
```properties
changeLogFile:  D:\\LiquibaseMySQL\\dbchangelog.xml
url:  jdbc:mysql://localhost:3306/my_schema
username:  root
password:  password
# driver可以不写，能自动推断出来
driver:  org.gjt.mm.mysql.Driver
```
- （2）编写changeLogFile（文件名是dbchangelog.xml）  
xml形式的创建语句（这样写的好处就是跨数据库，缺点就是我不太习惯使用xml写东西）
```xml
<?xml version="1.0" encoding="UTF-8"?>  

<databaseChangeLog  
  xmlns="http://www.liquibase.org/xml/ns/dbchangelog"  
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
  xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">  

    <changeSet  id="1"  author="bob">  
        <createTable  tableName="department">  
            <column  name="id"  type="int">  
                <constraints  primaryKey="true"  nullable="false"/>  
            </column>  
            <column  name="name"  type="varchar(50)">  
                <constraints  nullable="false"/>  
            </column>  
            <column  name="active"  type="boolean"  
              defaultValueBoolean="true"/>  
        </createTable>  
   </changeSet>  
</databaseChangeLog>
```
我比较习惯写sql，把上面稍微改改，引入另外一个sql文件（sql所在位置是D:\LiquibaseMySQL\sql\createTble.sql）就行了
```xml
<?xml version="1.0" encoding="UTF-8"?>  

<databaseChangeLog  
  xmlns="http://www.liquibase.org/xml/ns/dbchangelog"  
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
  xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">  

    <include file="sql/createTble.sql" relativeToChangelogFile="true"/>
</databaseChangeLog>
```
```sql
CREATE  TABLE  "department"  
  ("id"  number(*,0),  
   "name"  VARCHAR2(50  BYTE),  
   "active"  NUMBER(1,0)  DEFAULT  1  
  );
```
- （3）在D:\\LiquibaseMySQL下执行命令liquibase update即可创建department表  

---
- #### **2.[在maven中使用liquibase（当前示例就是）]()**
- 在当前项目的根目录下运行mvn liquibase:update就能执行数据库变更了
- 在maven中的常用命令(https://docs.liquibase.com/tools-integrations/maven/commands/home.html)

---
##### 其他链接：
[Create formatted SQL changelogs](https://www.liquibase.org/blog/plain-sql)  
[liquibase sql format](https://docs.liquibase.com/concepts/sql-format.html)  
[maven generateChangeLog](https://docs.liquibase.com/tools-integrations/maven/commands/maven-generatechangelog.html)
[spring-boot-liquibase-simple-example](https://www.baeldung.com/liquibase-rollback)也可以看chapter08  
[spring-boot-liquibase-example](https://javadeveloperzone.com/spring-boot/spring-boot-liquibase-example/)  
[liquibase中文学习指南](https://blog.csdn.net/u012934325/article/details/100652805)
