package datastructures.arrays.structures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class FloatArrayStructure {
    private ArrayList<Float> array;

    public FloatArrayStructure() {
        array = new ArrayList<>();
    }

    public ArrayList<Float> generateRandom(int size, float valueFrom, float valueTo) {
        this.array.clear();

        Random rnd = new Random();

        for (int i = 0; i < size; i++) {
            float randomFloat = valueFrom + rnd.nextFloat() * (valueTo - valueFrom);
            this.array.add(randomFloat);
        }

        return this.array;
    }

    public ArrayList<Float> generateSorted(int size, float valueFrom, float step) {
        this.array.clear();

        for (int i = 0; i < size; i++) {
            array.add(valueFrom);
            valueFrom += step;
        }

        return array;
    }

    public ArrayList<Float> generateReversed(int size, float valueFrom, float step) {
        this.array.clear();

        for (int i = 0; i < size; i++) {
            array.add(valueFrom);
            valueFrom -= step;
        }

        return array;
    }

    public ArrayList<Float> generateWithoutRepetitions(int length, float min, float max) {
        if (max - min < length) {
            throw new RuntimeException("Impossible to create array without repetitions!");
        }

        this.array.clear();

        Random rnd = new Random();

        HashSet<Float> buffer = new HashSet<>();

        while (buffer.size() != length) {
            float random = min + rnd.nextFloat() * (max - min);
            if (!buffer.contains(random)) {
                buffer.add(random);
            }
        }

        for (Float element : buffer) {
            array.add(element);
        }

        return array;
    }

    public ArrayList<Float> generateWithRepetitions(int length, float min, float max) {
        if (max - min > length) {
            throw new RuntimeException("Impossible to create array with repetitions!");
        }

        this.array.clear();

        Random rnd = new Random();

        for (int i = 0; i < length; i++) {
            float random = min + rnd.nextFloat() * (max - min);
            array.add(random);
        }

        return array;
    }

    public ArrayList<Float> currentArray() {
        if (this.array == null) {
            throw new NullPointerException("Array was null!");
        }

        return this.array;
    }
}

