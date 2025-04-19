# bank-transaction-management
## 依赖概括如下
1. **Spring Boot 相关依赖**：
    - `spring-boot-starter-web`：提供 Web 功能支持。
    - `spring-boot-starter-cache`：支持缓存功能。
    - `spring-boot-starter-validation`：提供校验功能。
    - `spring-boot-starter-aop`：支持 AOP（面向切面编程）。
    - `spring-boot-starter-test`：测试相关依赖。

2. **MyBatis-Plus**：
    - `mybatis-plus-boot-starter`：增强版的 MyBatis 框架。

3. **MapStruct**：
    - `mapstruct` 和 `mapstruct-processor`：用于对象映射。

4. **H2 数据库**：
    - `h2`：内存数据库，通常用于开发和测试环境。

5. **Caffeine 缓存**：
    - `caffeine`：高性能的本地缓存库。

6. **Lombok**：
    - `lombok`：简化 Java 开发，减少样板代码。

7. **其他**：
    - 使用了阿里云和 Maven 中央仓库作为依赖源。
## 访问说明
    - 接口地址：http://localhost:8080/
    - 前端简单使用html+jquery实现，在src/main/resources/templates/index.html
    - 前端访问接口地址配置在变量baseUrl上，如果更新端口，或者部署在其他地方，需要更新baseUrl的值


## 压测结果
    - 查询的结果见stress-test-query-transaction的index.html，压测100个线程持续60个循环，在本人19款macbookPro 设置4g内存 实现 5946 qps
    - 交易的结果见stress-test-create-transaction的index.html，压测100个线程持续60个循环，在本人19款macbookPro 设置4g内存 实现 1381 tps
    - 压测工具：Jmeter
## 其他说明
    - 注意跨域配置，由于是本地测试，所以没有做跨域配置，如果部署到其他地方，需要做跨域配置，配置在WebConfig中
    - master分支是基于jdk8的，自测与压测都是基于这个版本，jdk-21-version已经调整了jar包合部分写法并且启动成功，但是确实没时间基于这个版本做测试，建议直接使用master分支做测试和演示
    - 因时间仓促 ，资源锁和幂等都是用内存mock的，没有具体引入redission和redis
    - 因时间仓促 ，审计日志与事件分发都是简易写在本地，通过spring事件监听，异步执行的
    - 单测大部分是用idea生成的，时间允许的话，会自己再写一遍。

   