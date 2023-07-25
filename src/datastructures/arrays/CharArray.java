package datastructures.arrays;

import algorithms.sortings.ISort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public final class CharArray implements IArray<Character>, Iterable<Character> {
    private ArrayList<Character> array;

    public CharArray() {
        array = new ArrayList<>();
    }

    public CharArray(char[] array) {
        this.array = new ArrayList<>();

        for (char c : array) {
            this.array.add(c);
        }
    }

    public CharArray(ArrayList<Character> array) {
        this.array = new ArrayList<>();

        for (char c : array) {
            this.array.add(c);
        }
    }

    public CharArray generateRandom(int size) {
        this.clean();

        Random rnd = new Random();
        for (int i = 0; i < size; i++) {
            char randomChar = (char) (rnd.nextInt(26) + 'a');
            this.array.add(randomChar);
        }

        return this;
    }

    @Override
    public CharArray shuffle() {
        Random random = new Random();
        for (int i = 0; i < array.size(); i++) {
            int j = random.nextInt(i + 1);
            swap(i, j);
        }

        return this;
    }

    @Override
    public CharArray sort(ISort<Character> sorting) {
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
    public Character valueByIndex(int index) {
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
            String input = scanner.next();
            if (input.length() != 1) {
                throw new IllegalArgumentException("Invalid input. Enter a single character.");
            }
            char element = input.charAt(0);
            array.add(element);
        }
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public void insertToIndex(int index, Character element) {
        if (index > array.size() - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        array.set(index, element);
    }

    private void swap(int i, int j) {
        char temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    @Override
    public Iterator<Character> iterator() {
        return new CharArrayIterator();
    }

    private class CharArrayIterator implements Iterator<Character> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < array.size();
        }

        @Override
        public Character next() {
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
