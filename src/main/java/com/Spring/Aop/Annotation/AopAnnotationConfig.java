package com.Spring.Aop.Annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 使用 lite configuration，避免 Spring 4.3 在 JDK 21 上为配置类生成 CGLIB
 * 子类时访问 java.lang.ClassLoader 受到模块系统限制。
 */
@Component
@ComponentScan(basePackages = "com.Spring.Aop.Annotation")
@EnableAspectJAutoProxy(proxyTargetClass = false) //开启自动代理 Aop 的注解操作
//proxyTargetClass: 是否开启基于类的代理, false 表示基于接口的代理, true 表示基于类的代理
public class AopAnnotationConfig {
}
