package com.ls.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/27 22:13
 */
public class EasyExceGeneralDatalListener extends AnalysisEventListener<Map<Integer, String>> {

//    /**
//     * 处理业务逻辑的 Service,也可以是 Mapper
//     */
//    private ActResultLogService2 actResultLogService2;
//
//    /**
//     * 用于存储读取的数据
//     */
//    private List<Map<Integer, String>> dataList = new ArrayList<>();
//
//    public EasyExceGeneralDatalListener() {
//    }
//
//    public EasyExceGeneralDatalListener(ActResultLogService2 actResultLogService2) {
//        this.actResultLogService2 = actResultLogService2;
//    }
//
    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
//        // 数据 add 进入集合
//        dataList.add(data);
//        // size 是否为 100000 条:这里其实就是分批.当数据等于 10w 的时候执行一次插入
//        if (dataList.size() >= ExcelConstants.GENERAL_ONCE_SAVE_TO_DB_ROWS) {
//            // 存入数据库:数据小于 1w 条使用 Mybatis 的批量插入即可;
//            saveData();
//            // 清理集合便于 GC 回收
//            dataList.clear();
//        }
    }
//
//    /**
//     * 保存数据到 DB
//     *
//     * @param
//     * @MethodName: saveData
//     * @return: void
//     */
//    private void saveData() {
//        actResultLogService2.import2DBFromExcel10w(dataList);
//        dataList.clear();
//    }
//
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
//        saveData();
//        dataList.clear();
    }
}
