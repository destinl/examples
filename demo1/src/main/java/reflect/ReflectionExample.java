package reflect;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/23 21:33
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

class SampleClass{
    private  String  message;

    public  SampleClass(String  message)  {
        this.message  =  message;
    }

    public  void  displayMessage()  {
        System.out.println("Message:  "  +  message);
    }
}

public  class  ReflectionExample  {
    public  static  void  main(String[]  args)  {
        try  {
            //  获取类的  Class  对象
            Class<?>  clazz  =  Class.forName("reflect.SampleClass");

            //  获取构造函数
            Constructor<?>  constructor  =  clazz.getConstructor(String.class);
            //  创建对象实例
            Object  instance  =  constructor.newInstance("Hello,  Reflection!");

            //  获取方法
            Method method  =  clazz.getMethod("displayMessage");
            //  调用方法
            method.invoke(instance);
        }  catch  (Exception  e)  {
            e.printStackTrace();
        }
    }
}
