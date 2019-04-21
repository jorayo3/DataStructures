public class LinkedListDeque<T> {
    private ItemNode sentinel;
    private int size;

    public class ItemNode {
        private T item; //generic type item
        private ItemNode prev; //pointer to previous node
        private ItemNode next; //pointer to next node

        public ItemNode(T item0, ItemNode prev0, ItemNode next0) {
            item = item0;
            prev = prev0;
            next = next0;
        }
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new ItemNode(null, null, null);
    }

    public LinkedListDeque(LinkedListDeque other) {
        size = 0;
        sentinel = new ItemNode(null, null, null);

        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    private void addEmpty(T item) {
        ItemNode temp = new ItemNode(item, sentinel, sentinel);
        sentinel.prev = temp;
        sentinel.next = temp;
        size += 1;
    }

    public void addFirst(T item) {
        if (this.isEmpty()) {
            addEmpty(item);
        } else {
            sentinel.next = new ItemNode(item, sentinel, sentinel.next);
            sentinel.next.next.prev = sentinel.next;
            size += 1;
        }
    }

    public void addLast(T item) {
        if (this.isEmpty()) {
            addEmpty(item);
        } else {
            sentinel.prev = new ItemNode(item, sentinel.prev, sentinel);
            sentinel.prev.prev.next = sentinel.prev;
            size += 1;
        }
    }

    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        String strValue;
        ItemNode p = sentinel.next;
        while (p.next != sentinel) {
            System.out.print(p.item + " "); //Do I have to change to String?
            p = p.next;
        }
        System.out.println(p.item);
    }

    public T removeFirst() {
        if (size() == 0) {
            return null;
        }

        ItemNode first = sentinel.next;
        first.next.prev = sentinel;
        sentinel.next = first.next;
        size -= 1;
        return first.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        ItemNode last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        size -= 1;
        return last.item;
    }

    public T get(int index) {
        if (this.isEmpty()) {
            return null;
        }
        ItemNode p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
            if (p == sentinel) {
                return null;
            }
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }

        ItemNode p = sentinel.next;
        return getRecursiveHelper((index), p).item;
    }

    private ItemNode getRecursiveHelper(int index, ItemNode node) {
        if (index > 0) {
            return getRecursiveHelper(index - 1, node.next);
        }
        return node;
    }
}
