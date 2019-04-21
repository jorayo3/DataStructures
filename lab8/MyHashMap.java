import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private static final int DEFAULTSIZE = 16;
    private static final double DEFAULTLOADFACTOR = 0.75;
    private HashSet set;
    private LinkedList<Entry>[] linkArray;
    private int capacity;
    private double loadFactor;

    public MyHashMap() {
        set = new HashSet();
        capacity = DEFAULTSIZE;
        linkArray = new LinkedList[capacity];
        loadFactor = DEFAULTLOADFACTOR;
    }
    public MyHashMap(int initialSize) {
        set = new HashSet();
        capacity = initialSize;
        linkArray = new LinkedList[capacity];
        loadFactor = DEFAULTLOADFACTOR;
    }
    public MyHashMap(int initialSize, double loadFactor) {
        set = new HashSet();
        capacity = initialSize;
        linkArray = new LinkedList[capacity];
        this.loadFactor = loadFactor;
    }

    private class Entry {
        private K key;
        private V val;

        Entry(K k, V v) {
            key = k;
            val = v;
        }

        public K key() {
            return key;
        }

        public V value() {
            return val;
        }
    }

    @Override
    public void put(K key, V value) {
        int k = Math.floorMod(key.hashCode(), capacity);
        if (linkArray[k] == null) {
            linkArray[k] = new LinkedList<>();
        }
        LinkedList<Entry> node = linkArray[k];
        if (containsKey(key)) {
            for (int i = 0; i < node.size(); i++) {
                Entry currEntry = node.pop();
                if (currEntry.key() == key) {
                    node.addLast(new Entry(key, value));
                    return;
                }
                node.addLast(currEntry);
            }
        } else {
            if (size() + 1 >= capacity * loadFactor) {
                resize();
                int code = Math.floorMod(key.hashCode(), capacity);
                if (linkArray[code] == null) {
                    linkArray[code] = new LinkedList<Entry>();
                }
                linkArray[code].addLast(new Entry(key, value));
            } else {
                node.addLast(new Entry(key, value));
            }
            set.add(key);
        }
    }

    private void resize() {
        capacity *= 2;
        LinkedList<Entry>[] newLinkArray = new LinkedList[capacity];
        for (int i = 0; i < capacity / 2; i++) {
            LinkedList<Entry> node = linkArray[i];
            for (int j = 0; j < node.size(); j++) {
                Entry foo = node.pop();
                int code = Math.floorMod(foo.key().hashCode(), capacity);
                newLinkArray[code].addLast(new Entry(foo.key(), foo.value()));
            }
        }
        linkArray = newLinkArray;
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean containsKey(K key) {
        return set.contains(key);
    }

    @Override
    public V get(K key) {
        if (containsKey(key)) {
            int k = Math.floorMod(key.hashCode(), capacity);
            LinkedList<Entry> node = linkArray[k];
            for (int i = 0; i < node.size(); i++) {
                if (node.size() == 1){
                    return node.peek().value();
                }
                Entry currEntry = node.pop();
                System.out.println(currEntry.key());
                if (currEntry.key() == key) {
                    return currEntry.value();
                }
                node.addLast(currEntry);
            }
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        return set;
    }

    @Override
    public void clear() {
        set = null;
        linkArray = new LinkedList[DEFAULTSIZE];
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<K> {
        private int wizPos;
        private K[] keyArray;

        private KeyIterator() {
            wizPos = 0;
            keyArray = (K[]) new Object[set.size()];
        }

        public boolean hasNext() {
            return wizPos < set.size();
        }

        public K next() {
            K returnItem = keyArray[wizPos];
            wizPos++;
            return returnItem;
        }
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

}
