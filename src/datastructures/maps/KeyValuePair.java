package datastructures.maps;

public final class KeyValuePair<K, V> {
    public K key;
    public V value;

    KeyValuePair(K keyReceived, V valueReceived) {
        this.key = keyReceived;
        this.value = valueReceived;
    }
}
