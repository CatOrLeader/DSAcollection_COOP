package datastructures.stacks;

public interface StackVisitor<T> {
    void visit(IStackADT<T> stack);
}
