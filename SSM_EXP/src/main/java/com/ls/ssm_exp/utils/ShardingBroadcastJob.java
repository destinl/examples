//package com.ls.ssm_exp.utils;
//
//import com.xxl.job.core.context.XxlJobHelper;
//import com.xxl.job.core.log.XxlJobLogger;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * @Description:
// * @Author: ls
// * @Date: 2024/7/2420:47
// */
//@Component
//public class ShardingBroadcastJob {
//
//    @XxlJob("shardingBroadcastTask")
//    public void execute(String param) {
//        // 获取分片参数：分片总数和分片序列号
//        int shardIndex = XxlJobHelper.getShardIndex();
//        int shardTotal = XxlJobHelper.getShardTotal();
//
//        XxlJobLogger.log("当前节点的 index={}, 总结点数={}, 参数={}", shardIndex, shardTotal, param);
//
//        // 模拟获取数据列表
//        List<String> dataList = getDataList();
//
//        // 执行分片逻辑
//        shardingExecute(dataList, shardIndex, shardTotal);
//    }
//
//    public List<String> getDataList() {
//        // 这里可以根据实际情况从数据库或其他数据源获取数据列表
//        // 为了示例简单，直接返回一个固定的列表
//        return List.of("data1", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "data9", "data10");
//    }
//
//    public void shardingExecute(List<String> dataList, int shardIndex, int shardTotal) {
//        XxlJobLogger.log("开始执行分片任务，当前分片={}, 总分片数={}", shardIndex, shardTotal);
//
//        // 计算当前分片应处理的数据范围
//        int start = (shardIndex * dataList.size()) / shardTotal;
//        int end = ((shardIndex + 1) * dataList.size()) / shardTotal;
//
//        // 处理当前分片的数据
//        for (int i = start; i < end; i++) {
//            String data = dataList.get(i);
//            XxlJobLogger.log("处理数据: {}", data);
//            // 在此处添加具体的数据处理逻辑
//        }
//
//        XxlJobLogger.log("分片任务执行完成");
//    }
//}