package datastructures.arrays;

import algorithms.sortings.ISort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class StringArray implements IArray<String>, Iterable<String> {
    private final ArrayList<String> array;

    public StringArray() {
        array = new ArrayList<>();
    }

    public StringArray(String[] array) {
        this.array = new ArrayList<>();

        for (String s : array) {
            this.array.add(s);
        }
    }

    public StringArray(ArrayList<String> array) {
        this.array = new ArrayList<>();

        for (String s : array) {
            this.array.add(s);
        }
    }

    public StringArray generateRandom(int size, int length) {
        this.clean();

        for (int i = 0; i < size; i++) {
            String randomString = generateRandomString(length);
            this.array.add(randomString);
        }

        return this;
    }

    private String generateRandomString(int length) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            char randomChar = (char) ('a' + rnd.nextInt(26));
            sb.append(randomChar);
        }

        return sb.toString();
    }

    @Override
    public StringArray shuffle() {
        Random random = new Random();
        for (int i = 0; i < array.size(); i++) {
            int j = random.nextInt(i + 1);
            swap(i, j);
        }

        return this;
    }

    @Override
    public StringArray sort(ISort<String> sorting) {
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
    public String valueByIndex(int index) {
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
            String element = scanner.next();
            array.add(element);
        }
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public void insertToIndex(int index, String element) {
        if (index > array.size() - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        array.set(index, element);
    }

    private void swap(int i, int j) {
        String temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    @Override
    public Iterator<String> iterator() {
        return new StringArrayIterator();
    }

    private class StringArrayIterator implements Iterator<String> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < array.size();
        }

        @Override
        public String next() {
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
