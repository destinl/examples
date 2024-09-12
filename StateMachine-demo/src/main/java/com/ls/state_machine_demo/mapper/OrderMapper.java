package com.ls.state_machine_demo.mapper;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ls.state_machine_demo.domain.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.executor.BatchResult;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/11 23:17
 */
//@Mapper
public interface OrderMapper{
//     extends BaseMapper<Order>
    @Select("SELECT * FROM tb_order WHERE id = #{id}")
    Order selectById(Long id);

    @Insert("INSERT INTO tb_order (order_code, status, name, price, delete_flag, create_time, update_time, create_user_code, update_user_code, version, remark) " +
            "VALUES (#{orderCode}, #{status}, #{name}, #{price}, #{deleteFlag}, #{createTime}, #{updateTime}, #{createUserCode}, #{updateUserCode}, #{version}, #{remark})")
    void insert(Order order);

    @Update("UPDATE tb_order SET order_code = #{orderCode}, status = #{status}, name = #{name}, price = #{price}, delete_flag = #{deleteFlag}, create_time = #{createTime}, update_time = #{updateTime}, create_user_code = #{createUserCode}, update_user_code = #{updateUserCode}, version = #{version}, remark = #{remark} WHERE id = #{id}")
    void updateById(Order order);

//    @Override
//    default List<BatchResult> insert(Collection<Order> entityList) {
//        return BaseMapper.super.insert(entityList);
//    }
//
//    @Override
//    default List<BatchResult> updateById(Collection<Order> entityList) {
//        return BaseMapper.super.updateById(entityList);
//    }
}
//@Mapper
//public interface OrderMapper extends BaseMapper<Order> {
//
//    // 根据订单编码查询订单
//    @Select("SELECT * FROM tb_order WHERE order_code = #{orderCode}")
//    Order selectByOrderCode(@Param("orderCode") String orderCode);
//
//    // 根据状态查询订单列表
//    @Select("SELECT * FROM tb_order WHERE status = #{status}")
//    List<Order> selectByStatus(@Param("status") Integer status);
//
//    // 删除订单（逻辑删除，假设通过修改删除标记字段实现）
//    @Update("UPDATE tb_order SET delete_flag = 1 WHERE id = #{id}")
//    int deleteById(@Param("id") Long id);
//
//    // 更新订单状态
//    @Update("UPDATE tb_order SET status = #{newStatus} WHERE id = #{id}")
//    int updateStatusById(@Param("id") Long id, @Param("newStatus") Integer newStatus);
//}