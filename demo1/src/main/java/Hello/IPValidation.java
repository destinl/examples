package Hello;

/**
 * @Description: IP 审查  假设要判断一个字符串是否是合法的 IP 地址
 * @Author: ls
 * @Date: 2024/8/19 21:40
 */
public class IPValidation {
    public static boolean isValidIP(String ip) {
        String[] parts = ip.split("\\.");
        if (parts.length!= 4) {
            return false;
        }
        for (String part : parts) {
            try {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255) {
                    return false;
                }
                if (part.startsWith("0") && part.length() > 1) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String ip1 = "192.168.1.1";
        String ip2 = "256.0.0.1";
        String ip3 = "192.168.01.1";

        System.out.println(ip1 + " is valid? " + isValidIP(ip1));
        System.out.println(ip2 + " is valid? " + isValidIP(ip2));
        System.out.println(ip3 + " is valid? " + isValidIP(ip3));
    }
}
