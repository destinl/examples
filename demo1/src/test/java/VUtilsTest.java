import FunctionalInterface_demo.VUtils;
import org.junit.jupiter.api.Test;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/4 14:56
 */
public class VUtilsTest {

    @Test
    void isTrue1(){
        VUtils.isTure(false).throwMessage("抛出异常");
    }

    @Test
    void isTrue(){
        VUtils.isTure(true).throwMessage("抛出异常");
    }

    @Test
    void isTrueOrFalse(){
        VUtils.isTureOrFalse(true)
                .trueOrFalseHandle(()->{
                    System.out.println("true");
                }, ()->{
                    System.out.println("false");
                });
    }

    @Test
    void isTrueOrFalse1(){
        VUtils.isTureOrFalse(false)
                .trueOrFalseHandle(()->{
                    System.out.println("true");
                }, ()->{
                    System.out.println("false");
                });
    }

    @Test
    void isBlankOrNoBlank(){
        VUtils.isBlankOrNoBlank("hello")
                .presentOrElseHandle(System.out::println, ()->{
                    System.out.println("空字符串");
                });
    }

    @Test
    void isBlankOrNoBlank1(){
        VUtils.isBlankOrNoBlank("")
                .presentOrElseHandle(System.out::println, ()->{
                    System.out.println("空字符串");
                });
    }
}
