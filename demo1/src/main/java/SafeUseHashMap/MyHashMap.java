package SafeUseHashMap;

/**
 * @Description: 手写hashmap
 * @Author: ls
 * @Date: 2024/8/19 21:20
 */
public class MyHashMap<K, V>{

    private static final int INITAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] table;
    private int size;

    public MyHashMap(){
        table = new Entry[INITAL_CAPACITY];
    }

    static class Entry<K, V>{
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    public V put(K key, V value){
        if(size >= table.length * LOAD_FACTOR){
            resize();
        }
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        Entry<K, V> entry = table[index];
        if(entry == null){
            table[index] = new Entry<>(key, value);
            size++;
            return null;
        }else {
            while(entry != null){
                if(entry.key.equals(key)){
                    V oldValue = entry.value;
                    entry.value = value;
                    return oldValue;
                }
                if(entry.next == null){
                    break;
                }
                entry = entry.next;
            }
            entry.next = new Entry<>(key, value);
            size++;
            return null;
        }
    }
    public V get(K key){
        int hash =hash(key);
        int index =indexFor(hash, table.length);
        Entry<K, V> entry = table[index];
        while(entry != null){
            if(entry.key.equals(key)){
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    private int hash(K key){
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int indexFor(int hash, int length){
        return hash & (length-1);
    }

    private void resize(){
        Entry<K, V>[] oldTable = table;
        table = new Entry[oldTable.length * 2];
        size = 0;
        for(Entry<K, V> entry : oldTable){
            while(entry != null){
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();
        myHashMap.put("apple", 5);
        myHashMap.put("banana", 3);
        myHashMap.put("cherry", 7);

        Integer value = myHashMap.get("banana");
        if (value!= null) {
            System.out.println("Value for 'banana': " + value);
        }
    }
}
