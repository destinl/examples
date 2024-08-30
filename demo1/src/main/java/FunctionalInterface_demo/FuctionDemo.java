package FunctionalInterface_demo;

import FunctionalInterface_demo.entity.User;
import FunctionalInterface_demo.entity.Dept;
import FunctionalInterface_demo.entity.Supplie;
import FunctionalInterface_demo.entity.User;

import FunctionalInterface_demo.dto.AddOrderDTO;
import FunctionalInterface_demo.entity.Customer;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/30 23:39
 */
public class FuctionDemo {

    /**
     * 确认数据库字段值有效（通用）
     *
     * @param <V> 待验证值的类型
     * @param valueToCheck 待验证的值
     * @param columnExtractor 实体类属性提取函数
     * @param queryExecutor 单条数据查询执行器
     * @param errorMessage 异常提示信息模板
     */
    public static <T, R, V> void ensureColumnValueValid(V valueToCheck, SFunction<T, R> columnExtractor, SFunction<LambdaQueryWrapper<T>, T> queryExecutor, String errorMessage) {
        if (valueToCheck == null) return;

        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(columnExtractor);
        wrapper.eq(columnExtractor, valueToCheck);
        wrapper.last("LIMIT 1");
        T entity = queryExecutor.apply(wrapper);
        R columnValue = columnExtractor.apply(entity);
        if (entity == null || columnValue == null)
//            throw new DataValidationException(String.format(errorMessage, valueToCheck));
            throw new RuntimeException(String.format(errorMessage, valueToCheck));
    }
    /**
     * 应用场景：实现了对任意实体类指定列值的有效性断言
     * @param dto
     */
    public void assignTaskToUser(AddOrderDTO dto) {
//        需要Dao层：userDao、customerDao、deptDao、supplierDao
//        ensureColumnValueValid(dto.getUserId(), User::getId, userDao::getOne, "用户ID无效");
//        ensureColumnValueValid(dto.getDeptId(), Dept::getId, deptDao::getOne, "部门ID无效");
//        ensureColumnValueValid(dto.getCustomerId(), Customer::getId, customerDao::getOne, "客户ID无效");
//        ensureColumnValueValid(dto.getDeptId(), Dept::getId, deptDao::getOne, "部门ID无效");
//        ensureColumnValueValid(dto.getSupplieId(), Supplie::getId, supplierDao::getOne, "供应商ID无效");
//        // 现在可以确信客户存在
//        Customer cus = customerDao.findById(dto.getCustomerId());

        // 创建订单的逻辑...
    }




    /**
     * 验证查询结果中指定列的值是否与预期值匹配
     *
     * @param <T>             实体类型
     * @param <R>             目标列值的类型
     * @param <C>             查询条件列值的类型
     * @param targetColumn    目标列的提取函数，用于获取想要验证的列值
     * @param expectedValue   期望的列值
     * @param conditionColumn 条件列的提取函数，用于设置查询条件
     * @param conditionValue  条件列对应的值
     * @param queryMethod     执行查询的方法引用，返回单个实体对象
     * @param errorMessage    验证失败时抛出异常的错误信息模板
     * @throws RuntimeException 当查询结果中目标列的值与预期值不匹配时抛出异常
     */
    public static <T, R, C> void validateColumnValueMatchesExpected(
            SFunction<T, R> targetColumn, R expectedValue,
            SFunction<T, C> conditionColumn, C conditionValue,
            SFunction<LambdaQueryWrapper<T>, T> queryMethod,
            String errorMessage) {
        // 创建查询包装器，选择目标列并设置查询条件
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(targetColumn);
        wrapper.eq(conditionColumn, conditionValue);
        // 执行查询方法
        T one = queryMethod.apply(wrapper);
        // 如果查询结果为空，则直接返回，视为验证通过（或忽略）
        if (one == null) return;
        // 获取查询结果中目标列的实际值
        R actualValue = targetColumn.apply(one);
        // 比较实际值与预期值是否匹配，这里假设notMatch是一个自定义方法用于比较不匹配情况
        boolean doesNotMatch = notMatch(actualValue, expectedValue);
        if (doesNotMatch) {
            // 若不匹配，则根据错误信息模板抛出异常
            throw new RuntimeException(String.format(errorMessage, expectedValue, actualValue));
        }
    }
    // 假设的辅助方法，用于比较值是否不匹配，根据实际需要实现
    private static <R> boolean notMatch(R actual, R expected) {
        // 示例简单实现为不相等判断，实际情况可能更复杂
        return !Objects.equals(actual, expected);
    }
    /**
     * 应用场景： 例如在一个权限管理系统中，当需要更新用户角色时，系统需要确保当前用户的角色在更新前是 “普通用户”，才能将其升级为 “管理员”。
     * @param userId
     */
    public void validateColumnValueMatchesExpectedDemo(long userId){
        // 当用户角色不是 “普通用户” 时抛异常
//        validateColumnValueMatchesExpected(User::getRoleType,"普通用户", User::getId, userId, userMapper::getOne,
//                "用户角色不是普通用户，无法升级为管理员！");
    }









    /**
     * 验证查询结果中指定列的值是否位于预期值列表内
     *
     * @param <T>             实体类型
     * @param <R>             目标列值的类型
     * @param <C>             查询条件列值的类型
     * @param targetColumn    目标列的提取函数，用于获取想要验证的列值
     * @param expectedValueList 期望值的列表
     * @param conditionColumn 条件列的提取函数，用于设置查询条件
     * @param conditionValue  条件列对应的值
     * @param queryMethod     执行查询的方法引用，返回单个实体对象
     * @param errorMessage    验证失败时抛出异常的错误信息模板
     * @throws RuntimeException 当查询结果中目标列的值不在预期值列表内时抛出异常
     */
    public static <T, R, C> void validateColumnValueInExpectedList(
            SFunction<T, R> targetColumn, List<R> expectedValueList,
            SFunction<T, C> conditionColumn, C conditionValue,
            SFunction<LambdaQueryWrapper<T>, T> queryMethod,
            String errorMessage) {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(targetColumn);
        wrapper.eq(conditionColumn, conditionValue);
        T one = queryMethod.apply(wrapper);
        if (one == null) return;
        R actualValue = targetColumn.apply(one);
        if (actualValue == null) throw new RuntimeException("列查询结果为空");
        if (!expectedValueList.contains(actualValue)) {
            throw new RuntimeException(errorMessage);
        }
    }
    /**
     *  应用场景：在一个电商平台的订单处理流程中，系统需要验证订单状态是否处于可取消的状态列表里（如 “待支付”、“待发货”）才允许用户取消订单。
     */
    public void validateColumnValueInExpectedListDemo(){
        // 假设 OrderStatusEnum 枚举了所有可能的订单状态，cancelableStatuses 包含可取消的状态
//        List<String> cancelableStatuses = Arrays.asList(OrderStatusEnum.WAITING_PAYMENT.getValue(), OrderStatusEnum.WAITING_DELIVERY.getValue());
//        // 验证订单状态是否在可取消状态列表内
//        validateColumnValueInExpectedList(Order::getStatus, cancelableStatuses, Order::getOrderId, orderId, orderMapper::selectOne, "订单当前状态不允许取消！");
    }
}
