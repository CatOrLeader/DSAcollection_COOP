package datastructures.arrays.structures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class IntArrayStructure {
    private ArrayList<Integer> array;

    public IntArrayStructure() {
        array = new ArrayList<>();
    }

    public ArrayList<Integer> generateRandom(int size, int valueFrom, int valueTo) {
        this.array.clear();

        Random rnd = new Random();

        for (int i = 0; i < size; i++) {
            int randomInt = valueFrom + rnd.nextInt(valueTo - valueFrom + 1);
            this.array.add(randomInt);
        }

        return this.array;
    }

    public ArrayList<Integer> generateSorted(int size, int valueFrom, int step) {
        this.array.clear();

        for (int i = 0; i < size; i++) {
            array.add(valueFrom);
            valueFrom += step;
        }

        return array;
    }

    public ArrayList<Integer> generateReversed(int size, int valueFrom, int step) {
        this.array.clear();

        for (int i = 0; i < size; i++) {
            array.add(valueFrom);
            valueFrom -= step;
        }

        return array;
    }

    public ArrayList<Integer> generateWithoutRepetitions(int length, int min, int max) {
        if (max - min < length) {
            throw new RuntimeException("Impossible to create array without repetitions!");
        }

        this.array.clear();

        Random rnd = new Random();

        HashSet<Integer> buffer = new HashSet<>();

        while (buffer.size() != length) {
            int random = min + rnd.nextInt(max - min + 1);
            if (!buffer.contains(random)) {
                buffer.add(random);
            }
        }

        for (Integer element : buffer) {
            array.add(element);
        }

        return array;
    }

    public ArrayList<Integer> generateWithRepetitions(int length, int min, int max) {
        if (max - min > length) {
            throw new RuntimeException("Impossible to create array with repetitions!");
        }

        this.array.clear();

        Random rnd = new Random();

        for (int i = 0; i < length; i++) {
            int random = min + rnd.nextInt(max - min + 1);
            array.add(random);
        }

        return array;
    }

    public ArrayList<Integer> currentArray() {
        if (this.array == null) {
            throw new NullPointerException("Array was null!");
        }

        return this.array;
    }
}

