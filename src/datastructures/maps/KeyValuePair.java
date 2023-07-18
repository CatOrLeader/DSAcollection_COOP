package datastructures.maps;

public class KeyValuePair<K, V> {
    public K key;
    public V value;

    KeyValuePair(K keyReceived, V valueReceived) {
        this.key = keyReceived;
        this.value = valueReceived;
    }
}
