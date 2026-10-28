package com.Spring.Aop;

import com.Spring.Aop.Annotation.AopAnnotationConfig;
import com.Spring.Aop.Annotation.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 可直接运行的 AOP 验证入口：依次演示 XML 与注解两种配置。
 */
public final class AopDemo {
    private AopDemo() {
    }

    public static void main(String[] args) {
        runXml();
        runAnnotation();
    }

    private static void runXml() {
        System.out.println("===== Spring AOP：XML 配置 =====");
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("springaop/aop-xml.xml")) {
            com.Spring.Aop.xml.UserService service =
                    context.getBean(com.Spring.Aop.xml.UserService.class);
            service.saveUser("张三");
            System.out.println("查询结果: " + service.findUser("李四"));
            System.out.println("删除结果: " + service.deleteUser("王五"));
            invokeRisky(service::riskyOperation);
        }
    }

    private static void runAnnotation() {
        System.out.println("\n===== Spring AOP：注解配置 =====");
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AopAnnotationConfig.class)) {
            UserService service = context.getBean(UserService.class);
            service.saveUser("张三");
            System.out.println("查询结果: " + service.findUser("李四"));
            System.out.println("删除结果: " + service.deleteUser("王五"));
            invokeRisky(service::riskyOperation);
        }
    }

    private static void invokeRisky(Runnable action) {
        try {
            action.run();
        } catch (RuntimeException ex) {
            System.out.println("调用方捕获异常: " + ex.getMessage());
        }
    }
}
