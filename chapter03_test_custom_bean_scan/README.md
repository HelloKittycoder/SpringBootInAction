### 自定义bean扫描
（模拟mybatis在项目启动时给Mapper接口添加动态代理，然后把代理的接口加到spring容器中）  

---
1. 现状：我有一些接口的实现类的方法逻辑都一样，只是个别参数不同    
2. 需要实现的功能：  
能否我只写接口声明，另外有些不同的参数通过注解进行标记，然后借助代理类来拦截接口方法的调用，  
获取到必要的参数后，再取调用统一的逻辑去获取某些数据  
3. 思路：  
（1）首先我想到了mybatis中需要创建Mapper接口，编写接口方法和相应的sql语句，这个和我的需求完全一样  
（2）然后我顺着@MapperScan跟踪了mybatis-spring这个jar包中的创建Mapper接口代理的过程，  
发现几个非常重要的类：MapperScannerRegistrar，ClassPathMapperScanner，MapperFactoryBean，MapperProxy  
- MapperScannerRegistrar：主要是将扫描到的Mapper接口添加到spring容器中
- ClassPathMapperScanner：定义了扫描的逻辑（可以简单理解成只扫描某些包下的Mapper接口）
- MapperFactoryBean：用来创建任意Mapper接口（T）代理的工厂，具体如何创建交给getObject方法
- MapperProxy：是一个代理类，定义了在拦截接口调用后，需要额外做的事情（这里其实就是封装参数，执行sql，返回结果集）  
基于以上思路：
我创建了自己的扫描接口@DcScan，另外@DcRequest用来放一些额外的参数（如：调用别人的数据接口的接口编号），  
常规参数直接放在方法参数里，为简单起见，我写的接口方法声明里都没有方法参数  
另外模仿mybatis加了几个类：DcScannerRegistrar，ClassPathDcScanner，DcInterfaceFactoryBean，DcProxy  
因为我这边主要是为了熟悉思路，把整体过程给串起来，中间有些地方做了简化  
4. 测试：  
启动CustomBeanScanSpringBootApplication的main方法，然后访问http://localhost:8080/  
或者运行单元测试DataCenterServiceTest#test