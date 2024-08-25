package thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * @Description: 手撕n线程MapReduce，并通过你实现的MR来进行wordCount，（使用线程池）,优化：并行map+解决数据倾斜
 * @Author: ls
 * @Date: 2024/8/25 10:05
 */

public class MultiThreadedMapReduceWithDataSkewHandling {

    public static void main(String[] args) {
        String[] data = "hello world hello java world java hello python".split(" ");
        Map<String, Integer> result = MultiThreadedMapReduceWithDataSkewHandling.wordCount(data, 3);
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // 改进的分区函数
    public static int customPartition(String word, int numPartitions) {
        return Math.abs(word.hashCode()) % numPartitions;
    }

    /// Map 函数，接收一个字符串数组进行处理
    public static Map<String, Integer> map(String[] data) {
        Map<String, Integer> intermediate = new HashMap<>();
        for (String word : data) {
            intermediate.put(word, intermediate.getOrDefault(word, 0) + 1);
        }
        return intermediate;
    }

    // Reduce 函数
    public static Map<String, Integer> reduce(Map<String, Integer>... maps) {
        Map<String, Integer> result = new HashMap<>();
        for (Map<String, Integer> map : maps) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                result.put(entry.getKey(), result.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
        }
        return result;
    }

    public static Map<String, Integer> wordCount(String[] data, int numThreads) {
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        List<Future<Map<String, Integer>>> futures = new ArrayList<>();
        int numPartitions = numThreads;
        Map<String, Integer>[][] partitions = new Map[numPartitions][];
        for(int i = 0; i < numPartitions; i++){
            partitions[i] = new Map[data.length/numThreads];
        }

        int index = 0;
        for(String word : data){
            int partition = customPartition(word, numPartitions);
            Map<String, Integer> intermediateMap = partitions[partition][index % (data.length / numThreads)];
            if(intermediateMap == null){
                intermediateMap = new HashMap<>();
                partitions[partition][index % (data.length / numThreads)] = intermediateMap;
            }
            intermediateMap.put(word, intermediateMap.getOrDefault(word, 0) + 1);
            index++;
        }
        for (int i = 0; i < numPartitions; i++) {
            // 将 partitions[i] 转换为字符串数组进行 map 操作
            int finalI = i;
            Map<String, Integer>[] partitionArray = partitions[finalI];
            List<String> wordsInPartition = new ArrayList<>();
            for (Map<String, Integer> map : partitionArray) {
                if (map!= null) {
                    for (String word : map.keySet()) {
                        for (int j = 0; j < map.get(word); j++) {
                            wordsInPartition.add(word);
                        }
                    }
                }
            }
            String[] wordsArray = wordsInPartition.toArray(new String[0]);
            futures.add(executorService.submit(() -> map(wordsArray)));
        }
        executorService.shutdown();
        Map<String, Integer>[] intermediateResults = new Map[futures.size()];
        int resultIndex = 0;
        for (Future<Map<String, Integer>> future : futures) {
            try {
                intermediateResults[resultIndex++] = future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return reduce(intermediateResults);
    }
}
