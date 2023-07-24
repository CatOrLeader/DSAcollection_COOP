package datastructures.maps;

public interface MapVisitor<K, V> {
    void visit(MapADT<K, V> map);
}
