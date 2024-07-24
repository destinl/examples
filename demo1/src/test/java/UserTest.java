import main.java.entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/2421:44
 */
public class UserTest {
    @Test
    public void demo() {
        List<User> userList = new ArrayList<>();
        // 模拟数据
        userList.add(new User(1, "Alex"));
        userList.add(new User(1, "Beth"));
        userList.add(new User(2, null));

        Map<Integer, String> map = userList.stream()
                .collect(Collectors.toMap(
                        User::getId,
                        it -> Optional.ofNullable(it.getName()).orElse(""),
                        (oldData, newData) -> newData)
                );
        System.out.println(map);
    }
}