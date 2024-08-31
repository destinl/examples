package JVM;

/**
 * @Description: -verbose:gc简要的查看程序的 GC 情况
 * @Author: ls
 * @Date: 2024/8/31 12:59
 */
/**
 * 虚拟机参数：-verbose:gc
 */
public class GCObjectTest {
    public Object instance = null;
    /**
     * 设置成员属性占用一点内存，2M
     */
    public byte[] bigSize = new byte[2 * 1024 * 1024];
    public static void main(String[] args) {
        GCObjectTest objectA = new GCObjectTest();
        GCObjectTest objectB = new GCObjectTest();
        // 设置两个对象互相引用
        objectA.instance = objectB;
        objectB.instance = objectA;
        objectB = null;
        objectA = null;
        // 触发 GC,不一定实时生效
        System.gc();
    }
}