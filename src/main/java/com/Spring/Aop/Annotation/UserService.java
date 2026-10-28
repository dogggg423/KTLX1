package com.Spring.Aop.Annotation;

public interface UserService {

    void saveUser(String name);

    int deleteUser(String name);

    String findUser(String name);

    void riskyOperation();
}