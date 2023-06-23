package datastructures.stacks;

/**
 * All the functions which can be used in the stack implementation.
 * @param <T> - data type which will be stored in the stack.
 */
interface StackADT<T> {
    int size();

    boolean isEmpty();

    void push(T obj);

    void pop();

    T peek();
}
