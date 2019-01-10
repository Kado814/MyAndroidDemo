package com.algor.compiler;

/**
 * Created by ${KZJ} on 2019/1/3.
 */

/**
 * description 基于四个元注解，创建的自定义注解，测试。 @HaHaTargetPackage 单独讲解. （description）
 *
 * @author w
 * @version v1.0
 * @date 2018年6月17日下午7:01:39
 */
@BindType
public class AnnotationDemo {
    @BindField
    private String userName;

    /**
     * @param userName
     * @description 这是一个构造方法！
     * @see this constructor
     */
    @BindConstructor
    public AnnotationDemo(@BindParamerer String userName) {
        this.userName = userName;
    }

    /**
     * @return 没有返回值
     * @description 普通方法测试。
     * @version v1.0
     * @author w
     * @date 2018年6月17日 20:16:43
     */
    @BindMethod
    public void sayHello() {
        @BindLocalVariable
        String info = "userName : " + userName;
        System.out.println(info);
    }

    public static void main(String[] args) {
        AnnotationDemo demo = new AnnotationDemo("xiaoMing");
        demo.sayHello();
    }
}

