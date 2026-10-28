# Spring AOP 实验

项目实现了讲义中的两种 Spring AOP 配置方式：

- `com.Spring.Aop.xml`：XML Bean、切点与通知配置
- `com.Spring.Aop.Annotation`：`@Aspect`、组件扫描与自动代理

两套实现都包含 `@Before`、`@AfterReturning`、`@AfterThrowing`、`@After`、`@Around` 五种通知，环绕通知会演示参数替换、耗时统计和异常继续抛出。

## 运行

```text
mvn test
```

可运行入口为 `com.Spring.Aop.AopDemo`。注解配置使用 Spring 4.3 的 lite configuration，JDK 21 下可直接从 IntelliJ 或命令行启动。
