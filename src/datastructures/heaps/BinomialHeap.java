package datastructures.heaps;

import datastructures.arrays.IArray;

import java.util.ArrayList;
import java.util.List;

public final class BinomialHeap<T extends Comparable<T>> implements Heap<T> {

    private ArrayList<BinomialTree<T>> trees;
    private final T minValue;
    private final BinomialHeap.BinomialTree<T> missingTree;

    public BinomialHeap() {
        trees = new ArrayList<>();
        minValue = null;
        missingTree = new BinomialTree<>(null);
    }

    public BinomialHeap(IArray<T> array) {
        trees = new ArrayList<>();
        minValue = null;
        missingTree = new BinomialTree<>(null);
        for (T i: array) {
            insert(i);
        }
    }

    public void insert(T element) {
        BinomialTree<T> tree = new BinomialTree<>(element);
        mergeTree(tree);
    }

    public T findMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty Heap!");
        }
        T min = minValue;
        for (BinomialTree<T> tree : trees) {
            if (tree != missingTree) {
                if (min == minValue || tree.root().compareTo(min) < 0) {
                    min = tree.root();
                }
            }
        }
        return min;
    }

    public T deleteMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty Heap!");
        }
        int minIndex = minIndex();
        BinomialTree<T> minTree = trees.get(minIndex);
        T min = minTree.root();
        trees.set(minIndex, missingTree);
        BinomialHeap<T> newHeap = new BinomialHeap<>();
        newHeap.trees.ensureCapacity(minTree.degree());
        for (BinomialTree<T> child : minTree.children()) {
            newHeap.mergeTree(child);
        }
        merge(newHeap);
        return min;
    }

    public boolean isEmpty() {
        return trees.isEmpty();
    }

    public int size() {
        int count = 0;
        for (BinomialTree<T> tree : trees) {
            if (tree != missingTree) {
                count += (int) Math.pow(2, tree.degree());
            }
        }
        return count;
    }

    public void clear() {
        trees.clear();
    }

    private void mergeTree(BinomialTree<T> tree) {
        int index = tree.degree();
        while (index < trees.size() && trees.get(index) != missingTree) {
            tree = tree.merge(trees.get(index));
            trees.set(index, missingTree);
            index++;
        }
        if (index == trees.size()) {
            trees.add(tree);
        } else {
            trees.set(index, tree);
        }
    }

    private void merge(BinomialHeap<T> otherHeap) {
        ArrayList<BinomialTree<T>> mergedTrees = new ArrayList<>(trees.size() + otherHeap.trees.size());
        int i = 0;
        int j = 0;
        while (i < trees.size() || j < otherHeap.trees.size()) {
            BinomialTree<T> tree;
            if (i >= trees.size()) {
                tree = otherHeap.trees.get(j++);
            } else if (j >= otherHeap.trees.size()) {
                tree = trees.get(i++);
            } else {
                if (trees.get(i).degree() <= otherHeap.trees.get(j).degree()) {
                    tree = trees.get(i++);
                } else {
                    tree = otherHeap.trees.get(j++);
                }
            }
            mergedTrees.add(tree);
        }
        trees = mergedTrees;
    }

    private int minIndex() {
        int minIndex = -1;
        T min = minValue;
        for (int i = 0; i < trees.size(); i++) {
            BinomialTree<T> tree = trees.get(i);
            if (tree != missingTree) {
                if (min == minValue || tree.root().compareTo(min) < 0) {
                    min = tree.root();
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }

    private static class BinomialTree<T extends Comparable<T>> {
        private final T root;
        private final List<BinomialTree<T>> children;
        public BinomialTree(T root) {
            this.root = root;
            this.children = new ArrayList<>();
        }

        public T root() {
            return root;
        }

        public List<BinomialTree<T>> children() {
            return children;
        }

        public int degree() {
            return children.size();
        }

        public BinomialTree<T> merge(BinomialTree<T> other) {
            if (this.root.compareTo(other.root()) <= 0) {
                this.children.add(other);
                return this;
            } else {
                other.children.add(this);
                return other;
            }
        }
    }

    @Override
    public void print() {
        for (BinomialTree<T> tree : trees) {
            if (tree != missingTree) {
                System.out.print("Degree " + tree.degree() + ": ");
                printTree(tree.root(), tree.children());
                System.out.println();
            }
        }
    }

    @Override
    public void accept(HeapAlgorithm<T> algorithm) {
        algorithm.implement(this);
    }

    private void printTree(T root, List<BinomialTree<T>> children) {
        System.out.print(root + " ");
        for (BinomialTree<T> child : children) {
            printTree(child.root(), child.children());
        }
    }
}
