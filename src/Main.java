import algorithms.sortings.BubbleSort;
import datastructures.arrays.IntArray;
import datastructures.heaps.BinaryHeap;
import datastructures.heaps.BinomialHeap;
import datastructures.maps.Dictionary;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        IntArray array = new IntArray();
        array.generateRandom(20, 1, 100);
        array.sort(new BubbleSort());
        array.print();
        BinomialHeap<Integer> binHeap = new BinomialHeap<>();
        int[] a = {5, 2, 3, 1, 4};
        Arrays.stream(a).forEach(binHeap::insert);
        System.out.println(binHeap.findMin());

        System.out.println("------");

        Dictionary<String, Integer> dictInt = new Dictionary<>();
        dictInt.put("a", 2);
        dictInt.put("b", 1);
        dictInt.entrySet().forEach(i -> System.out.println(i.key + " " + i.value));

        System.out.println("-----------");

    }
}
