package datastructures.trees;

public interface TreeVisitor<T extends Comparable<T>> {
    void visit(AVLTree<T> tree);
    void visit(RedBlackTree<T> tree);
}
