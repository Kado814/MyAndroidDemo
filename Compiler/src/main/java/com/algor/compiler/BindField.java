package com.algor.compiler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ${KZJ} on 2019/1/3.
 */
//表示注解可以保存的时间
//1、SOURCE：注解不会被编译到 .class 文件中，如： @SuppressWarnings
//2、CLASS：注解会被编译到 .class文件中，但不会被JVM加载，缺省 默认这个
//3、RUNTIME：JVM会把注解加载到内存里，运行期间可见，所以可以通过反射读取注解的信息。
@Retention(RetentionPolicy.SOURCE)

//注解作用的范围
//@Target(ElementType.TYPE) //类，接口（包括注解），枚举
//@Target(ElementType.FIELD) //字段（包括枚举常量）
//@Target(ElementType.METHOD) //方法
//@Target(ElementType.PARAMETER) //参数
//@Target(ElementType.CONSTRUCTOR) //构造函数
//@Target(ElementType.LOCAL_VARIABLE)//局部变量
//@Target(ElementType.ANNOTATION_TYPE)//注解
//@Target(ElementType.PACKAGE) //包

//@Target(ElementType.TYPE_USE) //类型
//@Target(ElementType.TYPE_PARAMETER) //类型
@Target(ElementType.FIELD)
//@Constraint自定义的校验类
//@Documented说明该注解将被包含在javadoc中
//@Inherited 注解：允许子类继承父类的注解。并不是说允许子注解类继承父注解类。
public @interface BindField {
    int value() default  -1;
}
