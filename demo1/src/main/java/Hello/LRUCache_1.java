package Hello;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 手撕 LRU（Least Recently Used，最近最少使用）算法
 * @Author: ls
 * @Date: 2024/8/19 20:40
 */
public class LRUCache_1 {
    private class Node{
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private Map<Integer, Node> map;
    private Node head;//头指针
    private Node tail;//尾指针

    public LRUCache_1(int capacity){
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        //双向链表，但初始化只弄两条链
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value){
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
        }else{
            if(map.size() == capacity){
                Node last = tail.prev;
                removeNode(last);
                map.remove(last.key);
            }
            Node node = new Node(key, value);
            addToHead(node);
            map.put(key, node);
        }
    }
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {
        LRUCache_1 cache = new LRUCache_1(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 返回 1
        cache.put(3, 3);
        System.out.println(cache.get(2)); // 返回 -1，因为容量为 2，放入 3 时，2 被淘汰
        cache.put(4, 4);
        System.out.println(cache.get(1)); // 返回 -1，因为放入 4 时，1 被淘汰
        System.out.println(cache.get(3)); // 返回 3
        System.out.println(cache.get(4)); // 返回 4
    }
}


