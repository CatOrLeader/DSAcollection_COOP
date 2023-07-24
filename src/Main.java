import algorithms.sortings.BubbleSort;
import datastructures.arrays.IntArray;
import datastructures.heaps.BinaryHeap;
import datastructures.maps.Dictionary;
import datastructures.trees.AVLTree;
import datastructures.trees.RedBlackTree;

public class Main {
    public static void main(String[] args) {
        IntArray array = new IntArray();
        array.generateRandom(20, 1, 100);
        array.sort(new BubbleSort());
        array.print();

        BinaryHeap<Integer> binHeap = new BinaryHeap<>(
                new IntArray()
                .generateRandom(20, 1, 100)
                .shuffle()
                .sort(new BubbleSort<Integer>())
        );
        System.out.println(binHeap.findMin());

        System.out.println("------");

        Dictionary<String, Integer> dictInt = new Dictionary<>();
        dictInt.put("a", 2);
        dictInt.put("b", 1);
        dictInt.entrySet().forEach(i -> System.out.println(i.key + " " + i.value));

        System.out.println("-----------");

        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(2);
        tree.remove(3);
        System.out.println(tree.find(2));
        RedBlackTree<Integer> rb = new RedBlackTree<>();
        rb.insert(5);
        rb.insert(3);
        rb.insert(2);
        rb.remove(3);
        System.out.println(rb.find(2));
    }
}
