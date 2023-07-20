package datastructures.arrays;

import algorithms.sortings.ISort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class IntArray implements IArray<Integer>, Iterable<Integer> {
    private ArrayList<Integer> array = new ArrayList<>();

    public IntArray() {

    }

    public IntArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            this.array.add(array[i]);
        }
    }

    @Override
    public int size(){
        return array.size();
    }

    public IntArray generateRandom(int size, int valueFrom, int valueTo) {
        this.clean();

        for (int i = 0; i < size; i++) {
            Random rnd = new Random();
            int randomInt = rnd.nextInt(valueTo - valueFrom) + valueFrom;
            this.array.add(randomInt);
        }

        return this;
    }

    @Override
    public IntArray shuffle() {
        Random random = new Random();
        for (int i = 0; i < array.size(); i++) {
            int j = random.nextInt(i + 1);
            swap(i, j);
        }

        return this;
    }

    @Override
    public IntArray sort(ISort sorting) {
        sorting.sort(this.array);
        return this;
    }

    @Override
    public void print() {
        if (array.size() == 0) {
            System.out.println("Array is empty!");
        }

        for (int i = 0; i < array.size(); i++) {
            System.out.println("[" + i + "] = " + array.get(i));
        }
    }

    @Override
    public void clean() {
        array.clear();
    }

    @Override
    public Integer valueByIndex(int index) {
        if (index > array.size() - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        return array.get(index);
    }

    @Override
    public void inputFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int size = scanner.nextInt();

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element at index " + i + ": ");
            int element = scanner.nextInt();
            array.add(element);
        }
    }

    @Override
    public void insertToIndex(int index, Integer element) {
        if (index > array.size() - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        array.set(index, element);
    }

    private void swap(int i, int j) {
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IntArrayIterator();
    }

    private class IntArrayIterator implements Iterator<Integer> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < array.size();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("No more elements in the array.");
            }
            return array.get(currentIndex++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported.");
        }
    }
}
