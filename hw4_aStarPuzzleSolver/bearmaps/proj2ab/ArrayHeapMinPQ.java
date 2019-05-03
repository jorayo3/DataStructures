package bearmaps.proj2ab;

import java.util.ArrayList;
import java.util.HashMap;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<PriorityNode> items;
    private HashMap keys;

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        items.add(new PriorityNode(null, 0.0));
        keys = new HashMap<T, Integer>();
    }

    @Override
    public T getSmallest() {
        return (T) items.get(1).getItem();
    }

    @Override
    public boolean contains(T item) {
        return keys.containsKey(item);
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    //TODO
    public void changePriority(T item, double priority) {
        if (contains(item)) {
            int ind = (Integer)keys.get(item);
            double p = items.get(ind).changePriority(priority);
            if (priority < p) {
                swim(ind);
            } else if (priority > p) {
                sink(ind);
            }
        }
    }

    @Override
    public T removeSmallest() {
        if (keys.size() == 0) {
            return null;
        }

        PriorityNode<T> endNode = items.remove(keys.size());

        if (keys.size() == 1) {
            T item = endNode.getItem();
            keys.remove(item);
            return item;
        }

        int fInd = 1;
        PriorityNode<T> temp = items.remove(fInd);
        T item = temp.getItem();
        items.add(fInd, endNode);
        keys.remove(item);
        keys.replace(endNode.getItem(), 1);
        sink(fInd);
        return item;
    }

    private void sink(int k) {
        if (k == 0) {
            throw new IllegalArgumentException();
        }
        double p = items.get(k).getPriority();
        double cp1;
        double cp2;
        int[] children = child(k);
        if (children[0] <= keys.size()) {
            cp1 = items.get(children[0]).getPriority();
        } else {
            return;
        }

        if (children[1] <= keys.size()) {
            cp2 = items.get(child(k)[1]).getPriority();
            if (p > cp1 || p > cp2) {
                if (cp1 < cp2) {
                    swap(k, children[0]);
                    sink(children[0]);
                } else if (cp2 < cp1) {
                    swap(k, children[1]);
                    sink(children[1]);
                } else {
                    int rand = (int) Math.floor(Math.random() * 2);
                    swap(k, children[rand]);
                    sink(children[rand]);
                }
            }
        } else {
            if (p > cp1) {
                swap(k, children[0]);
                sink(children[0]);
            }
        }
    }

    private int[] child(int k) {
        int[] children = {k * 2, k * 2 + 1};
        return children;
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        int k = items.size();
        items.add(new PriorityNode<>(item, priority));
        keys.put(item, k);
        swim(k);
    }

    private void swim(int k) {
        if (k < 2) {
            return;
        }
        double p1 = items.get(k).getPriority();
        double p2 = items.get(parent(k)).getPriority();
        if (p1 < p2) {
            swap(k, parent(k));
            swim(parent(k));
        }
    }

    private void swap(int i, int j) {
        PriorityNode bar = items.get(j);
        PriorityNode foo = items.set(i, bar);
        items.set(j, foo);
        keys.replace(foo.getItem(), j);
        keys.replace(bar.getItem(), i);
    }

    private int parent(int index) {
        return index / 2;
    }

    //supp method for testing
    private Integer[] getArrayList() {
        PriorityNode[] array = new PriorityNode[items.size()];
        items.toArray(array);
        Integer[] kek = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            kek[i] = (int) array[i].getItem();
        }
        return kek;
    }

    //supp method for testing
    private int getMap(T item) {
        return (int) keys.get(item);
    }

    private class PriorityNode<T> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        double changePriority(double newPriority) {
            double temp = priority;
            priority = newPriority;
            return temp;
        }
    }
}
