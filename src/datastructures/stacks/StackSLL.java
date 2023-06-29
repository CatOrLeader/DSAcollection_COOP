package datastructures.stacks;

/**
 * Stack through singly linked list implementation.
 * @param <T> - data type which will be stored in the stack.
 */
public class StackSLL<T> implements StackADT<T> {
    private int size;
    private ObjRefPair stackHead;

    // Class for the simulate implementation of singly linked list
    private class ObjRefPair {
        private final T obj;
        private final ObjRefPair ref;

        ObjRefPair (T obj, ObjRefPair ref) {
            this.obj = obj;
            this.ref = ref;
        }
    }

    public StackSLL() {
        size = 0;
    }

    /**
     * Get the size of the stack.
     * Time complexity: O(1).
     * @return int size - current depth of the stack.
     */
    public int size() {
        return size;
    }

    /**
     * Check if the stack is empty.
     * Time complexity: O(1).
     * @return boolean - true if stack is empty; Otherwise, false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Push object to the top of the stack.
     * Time complexity: O(1).
     * @param obj - received object. Will be the head of the stack.
     */
    @Override
    public void push(T obj) {
        if (isEmpty()) {
            stackHead = new ObjRefPair(obj, null);
            size++;
            return;
        }

        stackHead = new ObjRefPair(obj, stackHead);
        size++;
    }

    /**
     * Delete the object from the top of the stack.
     * Current stackHead will point to the predecessor (if exist).
     * Time complexity: O(1).
     */
    @Override
    public void pop() {
        stackHead = stackHead.ref;
        size--;
    }

    /**
     * Gain the object from the top of the stack.
     * Time complexity: O(1).
     * @return T obj - object from the top of the stack without removing.
     */
    @Override
    public T peek() {
        return stackHead.obj;
    }
}
