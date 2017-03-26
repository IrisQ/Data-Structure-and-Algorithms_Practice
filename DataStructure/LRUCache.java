/**
 * 146. LRU Cache
 * Design and implement a data structure for, support the following operations: get and put
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, 
 * it should invalidate the least recently used item before inserting a new item.
 */
public class LRUCache {
    HashMap<Integer, DLinkedList> cache;    // need map to get O(1)
    DLinkedList head;                       // node after head: Most recent used, before head: least recent used
    int capacity;
    
    public int get(int key) {
        DLinkedList node = cache.get(key);
        if (node != null) {    // update node, to Most recent position
            head.updateNode(node);
            return node.value;
        } else return -1;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {   // update node, to Most recent position
        
        } else {  
            if (cache.size() == capacity) { // remove LRU
                
            }                               // add to cache
            
        }
    }
}

