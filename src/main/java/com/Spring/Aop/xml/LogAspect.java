package com.Spring.Aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

//自定义的切面类 包含通知的方法
public class LogAspect { //采用Spring的两种Aop的模式之一 必须配合Spring的配置文件 进行处理

    //自定义一个前置的通知方法 用于在方法执行前进行日志记录等操作
    //JoinPoint 用于获取切点的参数信息 等信息
    public void beforeLog(JoinPoint jp) { //JoinPoint 用于获取切点的参数信息 等信息
        String method = jp.getSignature().getName(); //方法执行的名称
        Object[] args = jp.getArgs(); //方法执行的参数
        System.out.println("  ⏰ @Before 前置通知 — [" + method + "] 参数: " + Arrays.toString(args));
    }

    public void afterReturningLog(JoinPoint jp, Object result) {
        String method = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        System.out.println("  ✅ @AfterReturning — [" + method + "] 参数: " + Arrays.toString(args) + " → 返回: " + result);
    }

    public void afterThrowingLog(JoinPoint jp, Throwable ex) {
        String method = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        System.out.println("  ❌ @AfterThrowing — [" + method + "] 参数: " + Arrays.toString(args) + " 异常: " + ex.getMessage());
    }

    public void afterLog(JoinPoint jp) {
        String method = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        System.out.println("  🔔 @After 最终通知 — [" + method + "] 参数: " + Arrays.toString(args));
    }

    public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
        String method = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        System.out.println("  🌯 @Around 环绕 — 进入 [" + method + "] 参数: " + Arrays.toString(args));

        Object[] newArgs = args.clone();
        if (newArgs.length > 0 && newArgs[0] instanceof String) {
            newArgs[0] = "[已加密]" + newArgs[0];
            System.out.println("  🌯 @Around 环绕 — 修改参数后: " + Arrays.toString(newArgs));
        }

        long start = System.currentTimeMillis();
        try {
            Object ret = pjp.proceed(newArgs);
            long cost = System.currentTimeMillis() - start;
            System.out.println("  🌯 @Around 环绕 — 退出 [" + method + "] 耗时: " + cost + "ms 返回: " + ret);
            return ret;
        } catch (Throwable t) {
            long cost = System.currentTimeMillis() - start;
            System.out.println("  🌯 @Around 环绕 — 异常 [" + method + "] 耗时: " + cost + "ms");
            throw t;
        }
    }
}