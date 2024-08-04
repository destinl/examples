package MQ;

import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/3 16:15
 */
public class TransactionProducer {
    public static void main(String[] args) throws Exception {
        // 创建事务消息生产者
        TransactionMQProducer producer = new TransactionMQProducer("TransactionProducerGroup");
        producer.setNamesrvAddr("localhost:9876");

        // 设置事务状态回查监听器
        producer.setTransactionCheckListener(new TransactionCheckListener() {
            @Override
            public LocalTransactionState checkLocalTransactionState(MessageExt msg) {
                // 处理事务状态回查逻辑
                System.out.println("Checking transaction state for message: " + new String(msg.getBody()));
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

        // 启动生产者
        producer.start();

        // 发送事务消息
        Message msg = new Message("TransactionTopic", "TagA", "Transaction Message".getBytes());
        SendResult sendResult = producer.sendMessageInTransaction(msg, new LocalTransactionExecuter() {
            @Override
            public LocalTransactionState executeLocalTransactionBranch(Message msg, Object arg) {
                // 执行本地事务逻辑
                System.out.println("Executing local transaction for message: " + new String(msg.getBody()));
                // 假设本地事务执行成功，返回 COMMIT_MESSAGE
                // 如果本地事务失败，返回 ROLLBACK_MESSAGE
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        }, null);

        System.out.println("Send result: " + sendResult);

        // 阻塞主线程，防止退出
        System.in.read();

        // 关闭生产者
        producer.shutdown();
    }
}
