package datastructures.arrays.structures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public final class StringArrayStructure {
    private ArrayList<String> array;

    public StringArrayStructure() {
        array = new ArrayList<>();
    }

    public ArrayList<String> generateRandom(int size, int stringLength, boolean includeNumbers, boolean includeUppercaseLetters, boolean includeLowercaseLetters) {
        this.array.clear();

        if (!includeNumbers && !includeUppercaseLetters && !includeLowercaseLetters) {
            throw new IllegalArgumentException("At least one character type (numbers, uppercase letters, or lowercase letters) should be included.");
        }

        StringBuilder chars = new StringBuilder();
        if (includeNumbers) {
            chars.append("0123456789");
        }
        if (includeUppercaseLetters) {
            chars.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        if (includeLowercaseLetters) {
            chars.append("abcdefghijklmnopqrstuvwxyz");
        }

        Random rnd = new Random();

        for (int i = 0; i < size; i++) {
            StringBuilder randomString = new StringBuilder();
            for (int j = 0; j < stringLength; j++) {
                char randomChar = chars.charAt(rnd.nextInt(chars.length()));
                randomString.append(randomChar);
            }
            this.array.add(randomString.toString());
        }

        return this.array;
    }

    public ArrayList<String> generateWithoutRepetitions(int length, int stringLength, boolean includeNumbers, boolean includeUppercaseLetters, boolean includeLowercaseLetters) {
        if (length > Math.pow(10, stringLength)) {
            throw new IllegalArgumentException("Impossible to create array without repetitions with given string length.");
        }

        this.array.clear();

        if (!includeNumbers && !includeUppercaseLetters && !includeLowercaseLetters) {
            throw new IllegalArgumentException("At least one character type (numbers, uppercase letters, or lowercase letters) should be included.");
        }

        StringBuilder chars = new StringBuilder();
        if (includeNumbers) {
            chars.append("0123456789");
        }
        if (includeUppercaseLetters) {
            chars.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        if (includeLowercaseLetters) {
            chars.append("abcdefghijklmnopqrstuvwxyz");
        }

        Random rnd = new Random();

        HashSet<String> buffer = new HashSet<>();

        while (buffer.size() != length) {
            StringBuilder randomString = new StringBuilder();
            for (int j = 0; j < stringLength; j++) {
                char randomChar = chars.charAt(rnd.nextInt(chars.length()));
                randomString.append(randomChar);
            }
            buffer.add(randomString.toString());
        }

        for (String element : buffer) {
            array.add(element);
        }

        return (ArrayList<String>) array.clone();
    }

    public ArrayList<String> currentArray() {
        if (this.array == null) {
            throw new NullPointerException("Array was null!");
        }

        return (ArrayList<String>) this.array.clone();
    }
}
