import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {
    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    private Node<K,V> root;
    private int size;
    private class Node<K,V> {
        private K key;
        private Node<K,V> left;
        private V value;
        private Node<K,V>right;
        public Node(K key,V value) {
            this.key = key;
            this.value=value;
            this.left = null;
            this.right = null;
        }
    }
    public BSTMap() {
        root = null;
        size = 0;
    }
    public  BSTMap(K key, V value) {
        root = new Node<K,V>(key,value);
        size = 1;
    }
    @Override
    public void put(K key, V value) {
        root=put(root,key,value);
    }
    private  Node<K,V> put(Node<K,V> node,K key,V value) {
        if (node==null) {
            size+=1;
            return new Node<K,V> (key,value);
        }
        int cmp=key.compareTo(node.key);
        if(cmp<0){
            node.left=put(node.left,key,value);
        }
        else if (cmp>0){
            node.right=put(node.right,key,value);
        }
        else {
            node.value=value;
        }
        return node;
    }
    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        return get(root,key);
    }
    private V get(Node<K,V> node,K key){
        if(node==null){return null;}
        int cmp=node.key.compareTo(key);
        if(cmp==0){
            return node.value;
        }
        else if (cmp>0){
            return get(node.left,key);
        }
        else   {
            return get(node.right,key);
        }
    }
    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return containsKey(root,key);
    }
    public boolean containsKey(Node<K,V> node,K key) {
        if (node == null){
            return false;
        }
        else if (node.key.compareTo(key) > 0){
            return containsKey(node.left,key);
        }
        else if (node.key.compareTo(key) < 0){
            return containsKey(node.right,key);
        }
        return true;
    }



    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        root=null;
        size=0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */


    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        V temp = get(root,key);
        if (temp != null){
            root = remove(root,key);
            return temp;
        }
        else
            return null;
    }
    private Node<K,V> remove(Node<K,V> node,K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        }
        if (node.left == null) {
            size-=1;
            return node.right;
        } else if (node.right == null) {
            size-=1;
            return node.left;
        } else {
            node.right = swapRightMin(node, node.right);
            return node;
        }

    }
    private Node<K,V> swapRightMin(Node<K,V> R,Node<K,V> T) {
        if (T.left == null){
            root.key = T.key;
            root.value = T.value;
            size-=1;
            return T.right;
        }
        else {
            T.left = swapRightMin(R,T.left);
            return T.left;
        }
    }
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return null;
    }
}
