SpringBoot学习  
- **第2章** [开发第一个应用程序]()  
chapter02 书中提供的示例  
chapter02_movie 补充：测试读取视频流  
chapter02_test1 测试@ConditionOnClass注解，根据条件筛选不同的配置  
chapter02_test2 测试@ConditionOnBean注解，根据条件筛选不同的配置  
（注意@ConditionalOnXX是和@Configuration一起用的）  
chapter02_test_mybatis_starter 补充：测试mybatis-starter中全局TypeHandler的使用  
chapter02_test_mybatis_starter2 补充：测试spring+mybatis多数据源（添加多套配置）  
- **第3章** [自定义配置]()  
chapter03 书籍配套代码（涉及知识点：自定义错误页面及常用属性、外部属性注入、自定义security配置）  
chapter03_test1 测试Profile的使用  
chapter03_test2 测试自定义错误属性的使用  
- **第4章** [测试]()  
chapter04 对未添加安全验证的应用进行测试  
ReadingListRepositoryTest 测试数据库访问层  
MockMvcWebTests 使用MockMvc测试Controller接口  
ServerWebTest、SimpleWebTest 使用随机端口测试Controller接口  
ServerWebTests 使用selenium做自动化测试，进行页面表单提交  
TestUtilitiesTest 通过加载spring提供的初始化器，测试属性是否加载到Environment中  
chapter04_1 对已添加安全验证（使用spring security）的应用进行测试（测的是ch03的代码）  
MockMvcWebTests 测试未经授权和已授权的用户访问/时是否有预期的效果  
- **第7章** [深入Actuator]()  
chapter07 主要是熟悉常用的端点  
远程shell查看返回结果不太友好，2.x也废弃了  
ActuatorConfig 定制Actuator（添加自定义度量、自定义跟踪仓库、自定义健康指示器）  
- **第8章** [部署Spring Boot应用程序]()  
chapter08 springboot整合liquibase  
chapter08_1 maven下如何使用liquibase（单独使用liquibase，不熟悉liquibase可以先看这个）  
