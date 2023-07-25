package datastructures.heaps;

public final class HeapTestAlgorithm<T extends Comparable<T>> implements HeapAlgorithm<T>{
    @Override
    public void implement(Heap<T> heap) {
        System.out.println();
        System.out.println("----------------HEAP PROPERTIES-----------------");
        System.out.println("Is heap empty? --> " + heap.isEmpty());
        System.out.println("Heap current size: " + heap.size());
        heap.print();
        System.out.println("---------------------------------");

        System.out.println();
        System.out.println("---------------Cycle #1------------------");
        System.out.println("Current min in Heap: " + heap.findMin());
        heap.deleteMin();
        System.out.println("Min element is deleted");
        System.out.println("---------------------------------");
        System.out.println();

        System.out.println("---------------Cycle #2------------------");
        System.out.println("Current min in Heap: " + heap.findMin());
        heap.deleteMin();
        System.out.println("Min element is deleted");
        System.out.println("---------------------------------");
        System.out.println();

        System.out.println("---------------Cycle #3------------------");
        System.out.println("Current min in Heap: " + heap.findMin());
        heap.deleteMin();
        System.out.println("Min element is deleted");
        System.out.println("---------------------------------");
        System.out.println();

        System.out.println("---------------Cycle #4------------------");
        System.out.println("Current min in Heap: " + heap.findMin());
        heap.deleteMin();
        System.out.println("Min element is deleted");
        System.out.println("---------------------------------");
        System.out.println();

        System.out.println("---------------Cycle #5------------------");
        System.out.println("Current min in Heap: " + heap.findMin());
        heap.deleteMin();
        System.out.println("Min element is deleted");
        System.out.println("---------------------------------");
        System.out.println();

        System.out.println("----------------HEAP PROPERTIES-----------------");
        System.out.println("Is heap empty? --> " + heap.isEmpty());
        System.out.println("Heap current size: " + heap.size());
        heap.print();
        System.out.println("---------------------------------");
    }
}
