package com.alibaba.otter.canalsample.listener;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.net.InetSocketAddress;
import java.util.List;
@Component
public class CanalListener{
    @Value("${canal.server.host}")
    private String canalHost;
    @Value("${canal.server.port}")
    private int canalPort;
    @Value("${canal.server.destination}")
    private String canalDestination;
    @Value("${canal.server.username}")
    private String canalUsername;
    @Value("${canal.server.password}")
    private String canalPassword;
    @PostConstruct
    public void init(){
        // 创建 Canal 连接器
        CanalConnector connector=CanalConnectors.newSingleConnector(
                new InetSocketAddress(canalHost, canalPort),
                canalDestination,
                canalUsername,
                canalPassword
        );
        int batchSize=1000;
        int emptyCount=0;
        try{
            // 连接到 Canal 服务端
            connector.connect();
            // 订阅所有数据库和表
            connector.subscribe(".*\\..*");
            // 回滚确认，撤销对所有数据的确认，以重新获取数据
            connector.rollback();
            int totalEmptyCount=120;
            while(emptyCount < totalEmptyCount){
            // 获取指定数量的数据
                Message message= connector.getWithoutAck(batchSize);
                long batchId= message.getId();
                int size= message.getEntries().size();
                if(batchId ==-1| size ==0){
                    // 空数据处理
                    emptyCount++;
                    System.out.println("empty count : "+ emptyCount);
                    try{
                        // 休眠1秒，避免过于频繁的空数据查询
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        // 异常处理
                    }
                }else{
                    // 重置空数据计数
                    emptyCount =0;
                    // 处理获取的数据
                    printEntry(message.getEntries());
                }
                // 提交确认，表示已经处理了这批数据
                connector.ack(batchId);
                // 处理失败时，可以调用 rollback 方法回滚数据
                // connector.rollback(batchId);
            }
            System.out.println("empty too many times, exit");
        }finally{
            // 断开连接
            connector.disconnect();
        }
    }

    // 处理数据变更事件
    private void printEntry(List<Entry> entries){
        for(Entry entry : entries){
            if(entry.getEntryType()==EntryType.TRANSACTIONBEGIN
                    | entry.getEntryType()==EntryType.TRANSACTIONEND){
                // 事务相关事件不处理
                continue;
            }
            RowChange rowChange=null;
            try{
                // 解析获取的数据
                rowChange =RowChange.parseFrom(entry.getStoreValue());
            }catch(Exception e){
                // 异常处理
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:"
                        + entry.toString(), e);
            }
            EventType eventType= rowChange.getEventType();
            System.out.println(
                    String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                            entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                            entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                            eventType));
            for(RowData rowData : rowChange.getRowDatasList()){
                if(eventType ==EventType.DELETE){
                    // 处理删除事件
                    printColumn(rowData.getBeforeColumnsList());
                }else if(eventType ==EventType.INSERT){
                    // 处理插入事件
                    printColumn(rowData.getAfterColumnsList());
                }else{
                    // 处理更新事件
                    System.out.println("-------> before");
                    printColumn(rowData.getBeforeColumnsList());
                    System.out.println("-------> after");
                    printColumn(rowData.getAfterColumnsList());
                }
            }
        }
    }

    // 打印列信息
    private void printColumn(List<Column> columns){
        for(Column column : columns){
            System.out.println(column.getName()+" : "+ column.getValue()
                    +"    update="+ column.getUpdated());
        }
    }
}
