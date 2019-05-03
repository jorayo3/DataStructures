import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int size;
    private K mKey;
    private V mValue;
    private BSTMap<K, V> left;
    private BSTMap<K, V> right;
    public BSTMap() {
        size = 0;
    }

    public BSTMap(K key, V value) {
        size = 1;
        mValue = value;
        left = null;
        right = null;
        mKey = key;
    }

    public void clear() {
        this.left = null;
        this.right = null;
        mKey = null;
        mValue = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (mKey == null) {
            return false;
        }
        if (mKey.equals(key)) {
            return true;
        } else if (mKey.compareTo(key) > 0) {
            if (this.left == null) {
                return false;
            } else {
                return this.left.containsKey(key);
            }
        } else {
            if (this.right == null) {
                return false;
            } else {
                return this.right.containsKey(key);
            }
        }
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (size == 0) {
            return null;
        }
        BSTMap temp = this;
        while (temp != null) {
            if (temp.mKey.equals(key)) {
                return (V) temp.value();
            } else if (temp.mKey.compareTo(key) > 0) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        return null;
    }

    private V value() {
        return mValue;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (mKey == null) {
            mKey = key;
            mValue = value;
            size++;
            return;
        }
        BSTMap parent;
        BSTMap temp = this;
        while (true) {
            if (this.mKey == key) {
                this.mValue = value;
                return;
            } else if (this.mKey.compareTo(key) > 0) {
                parent = temp;
                temp = temp.left;
                if (temp == null) {
                    parent.left = new BSTMap(key, value);
                    size++;
                    return;
                }
            } else {
                parent = temp;
                temp = temp.right;
                if (temp == null) {
                    parent.right = new BSTMap(key, value);
                    size++;
                    return;
                }
            }
        }

    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }
}
