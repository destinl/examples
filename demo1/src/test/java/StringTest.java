import org.junit.Test;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/10 18:12
 */
public class StringTest {
    /**
     * 使用+拼接字符串
     */
    public String concatenationStringByPlus(String prefix, int i) {
        return prefix + "-" + i;
    }

    /**
     * 使用StringBuilder拼接字符串
     */
    public String concatenationStringByStringBuilder(String prefix, int i) {
        return new StringBuilder().append(prefix).append("-").append(i).toString();
    }

    /**
     * 测试使用+拼接字符串耗时
     */
    @Test
    public void testStringConcatenation01ByPlus() {
        long startTime = System.currentTimeMillis();
        int count = 100000;
        for (int i = 0; i < count; i++) {
            String str = concatenationStringByPlus("testStringConcatenation01ByStringBuilder:", i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testStringConcatenation01ByPlus，拼接字符串" + count + "次，花费" + (endTime - startTime) + "秒");
    }


    /**
     * 测试使用StringBuilder拼接字符串耗时
     */
    @Test
    public void testStringConcatenation02ByStringBuilder() {
        long startTime = System.currentTimeMillis();
        int count = 100000;
        for (int i = 0; i < count; i++) {
            String str = concatenationStringByStringBuilder("testStringConcatenation02ByStringBuilder:", i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testStringConcatenation02ByStringBuilder，拼接字符串" + count + "次，花费" + (endTime - startTime) + "秒");
    }

    /**
     * 循环使用+拼接字符串
     */
    @Test
    public void testLoopStringConcatenation03ByPlus() {
        long startTime = System.currentTimeMillis();
        int count = 10000;
        String str = "testLoopStringConcatenation03ByPlus:";
        for (int i = 0; i < count; i++) {
            str = str + "-" + i;
        }
        System.out.println(str);
        long endTime = System.currentTimeMillis();
        System.out.println("testLoopStringConcatenation03ByPlus，拼接字符串" + count + "次，花费" + (endTime - startTime) + "秒");
    }

    /**
     * 测试循环使用StringBuilder拼接字符串耗时
     */
    @Test
    public void testLoopStringConcatenation04ByStringBuilder() {
        long startTime = System.currentTimeMillis();
        int count = 100000;
        StringBuilder stringBuilder = new StringBuilder("testLoopStringConcatenation04ByStringBuilder:");
        for (int i = 0; i < count; i++) {
            stringBuilder.append("-");
            stringBuilder.append(i);
        }
        String str = stringBuilder.toString();
        System.out.println(str);
        long endTime = System.currentTimeMillis();
        System.out.println("testLoopStringConcatenation04ByStringBuilder，拼接字符串" + count + "次，花费" + (endTime - startTime) + "秒");
    }
}
