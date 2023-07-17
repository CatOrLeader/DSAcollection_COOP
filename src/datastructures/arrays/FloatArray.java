package datastructures.arrays;

import algorithms.sortings.ISort;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FloatArray implements IArray<Float> {
    private ArrayList<Float> array = new ArrayList<>();

    public FloatArray() {

    }

    public FloatArray(float[] array) {
        for (int i = 0; i < array.length; i++) {
            this.array.add(array[i]);
        }
    }

    public void generateRandom(int size, float valueFrom, float valueTo) {
        this.clean();

        for (int i = 0; i < size; i++) {
            Random rnd = new Random();
            float randomFloat = valueFrom + rnd.nextFloat() * (valueTo - valueFrom);
            this.array.add(randomFloat);
        }
    }

    @Override
    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < array.size(); i++) {
            int j = random.nextInt(i + 1);
            swap(i, j);
        }
    }

    @Override
    public void sort(ISort sorting) {
        sorting.sort(this.array);
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
    public Float valueByIndex(int index) {
        if (index > array.size() - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        return array.get(index);
    }

    @Override
    public void insertToIndex(int index, Float element) {
        if (index > array.size() - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        array.set(index, element);
    }

    @Override
    public void inputFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int size = scanner.nextInt();

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element at index " + i + ": ");
            float element = scanner.nextFloat();
            array.add(element);
        }
    }

    private void swap(int i, int j) {
        float temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}
