public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int zeroDex;
    private static final int RFACTOR = 2;

    private static final int Start_Size = 8; //make 8 more official

    public ArrayDeque() {
        items = (T[]) new Object[Start_Size];
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[8];
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0, j = zeroDex; i < size; i++) {
            a[i] = items[(j % items.length)];
            j += 1;
        }
        items = a;
        zeroDex = 0;
    }

    private int minusOne(int index) {
        int i = index - 1;
        if (i < 0) {
            return i + items.length;
        }
        return i;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * RFACTOR);
        }
        zeroDex = minusOne(zeroDex);
        items[zeroDex] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * RFACTOR);
            items[size] = item;
        } else {
            int lastDex = (zeroDex + size) % items.length;
            items[lastDex] = item;
        }
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int j = zeroDex;
        for (int i = 0; i < size; i++) {
            System.out.print(items[(j % items.length)] + " ");
            j += 1;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T foo = items[zeroDex];
        items[zeroDex] = null;
        zeroDex += 1;
        if (zeroDex == items.length) {
            zeroDex = 0;
        }
        size -= 1;

        if (items.length > 15 && items.length / size >= 4) {
            resize(items.length / RFACTOR);
        }
        return foo;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int lastDex = (zeroDex + size - 1) % items.length;
        T foo = items[lastDex];
        items [lastDex] = null;
        size -= 1;

        if (items.length > 15 && items.length / size >= 4) {
            resize(items.length / RFACTOR);
        }
        return foo;
    }

    public T get(int index) {
        int i = (zeroDex + index) % items.length;
        return items[i];
    }
}
