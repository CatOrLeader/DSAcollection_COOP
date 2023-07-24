package datastructures.arrays.structures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class CharArrayStructure {
    private ArrayList<Character> array;

    public CharArrayStructure() {
        array = new ArrayList<>();
    }

    public ArrayList<Character> generateRandom(int size, char valueFrom, char valueTo) {
        this.array.clear();

        Random rnd = new Random();

        for (int i = 0; i < size; i++) {
            char randomChar = (char) (valueFrom + rnd.nextInt(valueTo - valueFrom + 1));
            this.array.add(randomChar);
        }

        return this.array;
    }

    public ArrayList<Character> generateWithoutRepetitions(int length, char min, char max) {
        if (max - min < length) {
            throw new RuntimeException("Impossible to create array without repetitions!");
        }

        this.array.clear();

        Random rnd = new Random();

        HashSet<Character> buffer = new HashSet<>();

        while (buffer.size() != length) {
            char random = (char) (min + rnd.nextInt(max - min + 1));
            if (!buffer.contains(random)) {
                buffer.add(random);
            }
        }

        for (Character element : buffer) {
            array.add(element);
        }

        return array;
    }

    public ArrayList<Character> generateWithRepetitions(int length, char min, char max) {
        if (max - min > length) {
            throw new RuntimeException("Impossible to create array with repetitions!");
        }

        this.array.clear();

        Random rnd = new Random();

        for (int i = 0; i < length; i++) {
            char random = (char) (min + rnd.nextInt(max - min + 1));
            array.add(random);
        }

        return array;
    }

    public ArrayList<Character> currentArray() {
        if (this.array == null) {
            throw new NullPointerException("Array was null!");
        }

        return this.array;
    }
}