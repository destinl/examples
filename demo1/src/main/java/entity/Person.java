package entity;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/10 20:44
 */
public final class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String name() {
        return name;
    }

    public int age() {
        return age;
    }
}

//// 使用模式匹配进行类型检查
//public static String getPersonInfo(Object obj) {
//    return switch (obj) {
//        case Person p -> "Name: " + p.name() + ", Age: " + p.age();//提示需要JDK21+
//        default -> "Unknown";
//    };
//}
