package cn.lcools.annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Copyright: Copyright (c) 2020
 *
 * @ClassName: AnnotationTest
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: liushuai
 * @date: 2020/7/22 9:39
 * *****
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 * 2020/7/22     liushuai           v1.0.0               修改原因
 */
public class AnnotationTest {

    @Test()
    public void test() throws Exception{

        Class<User> userClass = User.class;
        MyAnnotation myAnnotation = userClass.getAnnotation(MyAnnotation.class);
        String value = myAnnotation.value();
        System.out.println(value);

        User user = userClass.newInstance();
        System.out.println(user.toString());
        Method[] methods = userClass.getMethods();
        for (Method m : methods) {
            if(m.getName().startsWith("set")){
                MyAnnotation myAnnotation1 = m.getAnnotation(MyAnnotation.class);
                if(myAnnotation1!=null){
                    String value1 = myAnnotation1.value();
                    Class[] classes = m.getParameterTypes();
                    if(classes!=null && classes.length>0){
                        if(classes[0].getSimpleName().equals("int")){
                            m.invoke(user,Integer.valueOf(value1));
                        }else {
                            m.invoke(user,value1);
                        }
                    }
                }
            }
        }
        System.out.println(user.toString());
    }

}
