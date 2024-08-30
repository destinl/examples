package FunctionalInterface_demo.dto;

import lombok.Data;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/30 23:46
 */
@Data
public class AddOrderDTO {
    private Long userId;
    private Long deptId;
    private Long customerId;
    private Long supplieId;
    // 可以根据实际需求添加其他属性
}

