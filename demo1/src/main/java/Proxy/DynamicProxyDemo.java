package Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/17 19:56
 */
public class DynamicProxyDemo {
    public static void main(String[] args) {
        //JDK 动态代理
        Service target = new ServiceImpl();
        Service proxy = (Service) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new ServiceInvocationHandler(target)
        );

        proxy.perform();

        //CGLIB 动态代理
        //cglib 在尝试访问 java.lang.ClassLoader 的 defineClass 方法时遇到了问题。Java 9 引入了模块系统，增强了安全性和封装性，
        // 默认情况下 不允许非授权的访问(会报错Unable to make protected final java.lang.Class java.lang.ClassLoader.defineClass）
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(Service.class);
//        enhancer.setCallback(new ServiceMethodInterceptor());
//
//        Service proxy = (Service) enhancer.create();
//        proxy.perform();
    }
}
// 接口
interface Service {
    void perform();
}

// 需要被代理的实现类
class ServiceImpl implements Service {
    @Override
    public void perform() {
        System.out.println("mianshiya.com");
    }
}

//JDK 动态代理
class ServiceInvocationHandler implements InvocationHandler {
    private final Object target;

    public ServiceInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before method invoke");
        Object result = method.invoke(target, args);
        System.out.println("After method invoke");
        return result;
    }
}

//CGLIB 动态代理处理类
class ServiceMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Before method invoke");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("After method invoke");
        return result;
    }
}