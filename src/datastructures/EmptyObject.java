package datastructures;

public class EmptyObject implements Comparable<EmptyObject> {
    @Override
    public int compareTo(EmptyObject o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Empty. Nothing here for real!";
    }
}
