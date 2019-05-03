package es.datastructur.synthesizer;
import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//Make sure to that this class and all of its methods are public
//Make sure to add the override tag for all overridden methods
//Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // Create new array with capacity elements.
        // first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        } else if (isEmpty()) {
            rb[last] = x;
            fillCount++;
        } else {
            last = (last + 1) % capacity();
            rb[last] = x;
            fillCount++;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        } else {
            T foo = rb[first];
            rb[first] = null;
            first = (first + 1) % capacity();
            fillCount--;
            return foo;
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // Return the first item. None of your instance variables should
        //       change.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return rb[first];
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }


    @Override
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        private ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < fillCount;
        }

        public T next() {
            T returnItem = rb[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (this == o) {
            return true;
        } else if (o.getClass() != this.getClass()) {
            return false;
        }

        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;

        if (this.fillCount() != other.fillCount()) {
            return false;
        }

        Set<T> S = new HashSet<>();
        for (T i : this) {
            if (!S.contains(i)) {
                S.add(i);
            }
        }

        for (T i : other) {
            if (!S.contains(i)) {
                return false;
            }
        }
        return true;
    }
}

