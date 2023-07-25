package datastructures.maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class HashMapTestAlgorithm<K, V> implements MapAlgorithm<K, V> {
    @Override
    public void implement(MapADT<K, V> map) {
        System.out.println();
        System.out.println("----------------MAP PROPERTIES-----------------");
        List<K> keys = new ArrayList<>();
        map.keySet().forEach(keys::add);
        System.out.println("Is map empty? --> " + keys.isEmpty());
        System.out.println("Map current size: " + keys.size());
        map.print();
        System.out.println("---------------------------------");


        System.out.println("---------------Cycle #1------------------");
        String name = "Efim";
        System.out.println("Find by name \"Efim\": " + map.find((K) name));
        System.out.print("Remove by name \"Efim\": ");
        map.remove((K) name);
        System.out.println("Find by name \"Efim\": " + map.find((K) name));
        System.out.println("---------------------------------");

        System.out.println();
        System.out.println("---------------Cycle #2------------------");
        System.out.println("Put \"Efim\" back again :)");
        map.put((K) name, (V) "A");
        System.out.println("---------------------------------");

        System.out.println();
        System.out.println("---------------Cycle #3-----------------");
        System.out.println("Remove all!");
        System.out.println("...ZzZzZzZz...");
        map.keySet().forEach(
                map::remove
        );
        System.out.println("...ZzZzZzZz...");
        System.out.println("---------------------------------");

        System.out.println();
        System.out.println("----------------MAP PROPERTIES-----------------");
        keys = new ArrayList<>();
        map.keySet().forEach(keys::add);
        System.out.println("Is map empty? --> " + keys.isEmpty());
        System.out.println("Map current size: " + keys.size());
        map.print();
        System.out.println("---------------------------------");
    }
}
