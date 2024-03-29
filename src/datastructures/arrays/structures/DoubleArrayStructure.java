package datastructures.arrays.structures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public final class DoubleArrayStructure {
    private ArrayList<Double> array;

    public DoubleArrayStructure () {
        array = new ArrayList<>();
    }

    public ArrayList<Double> generateRandom(int size, double valueFrom, double valueTo) {
        this.array.clear();

        for (int i = 0; i < size; i++) {
            Random rnd = new Random();
            double randomDouble = valueFrom + rnd.nextDouble() * (valueTo - valueFrom);
            this.array.add(randomDouble);
        }

        return (ArrayList<Double>) this.array.clone();
    }

    public ArrayList<Double> generateSorted(int size, double valueFrom, double step) {
        this.array.clear();

        for (int i = 0; i < size; i++)
        {
            array.add(valueFrom);
            valueFrom += step;
        }
        return (ArrayList<Double>) array.clone();
    }

    public ArrayList<Double> generateReversed(int size, double valueFrom, double step) {
        this.array.clear();

        for (int i = 0; i < size; i++)
        {
            array.add(valueFrom);
            valueFrom -= step;
        }

        return (ArrayList<Double>) array.clone();
    }

    public ArrayList<Double> generateWithoutRepetitions(int length, int min, int max)
    {
        if (max - min + 1 < length){
            throw new RuntimeException("Impossible to create array without repetitions!");
        }

        this.array.clear();

        Random rnd = new Random();

        HashSet<Double> buffer = new HashSet<>();

        while(buffer.size() != length)
        {
            double random = min + (max - min) * rnd.nextDouble();
            if (!buffer.contains(random)) {
                buffer.add(random);
            }
        }

        for (Double element: buffer){
            array.add(element);
        }

        return (ArrayList<Double>) array.clone();
    }

    public ArrayList<Double> generateWithRepetitions(int length, int min, int max)
    {
        if (max - min > length){
            throw new RuntimeException("Impossible to create array with repetitions!");
        }

        this.array.clear();

        Random rnd = new Random();

        for (int i = 0; i < length; i++)
        {
            double random = min + (max - min) * rnd.nextDouble();
            array.add(random);
        }

        return (ArrayList<Double>) array.clone();
    }

    public ArrayList<Double> currentArray() {
        if (this.array == null){
            throw new NullPointerException("Array was null!");
        }

        return (ArrayList<Double>) this.array.clone();
    }
}
