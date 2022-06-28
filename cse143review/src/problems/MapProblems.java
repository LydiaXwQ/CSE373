package problems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * See the spec on the website for example behavior.
 */
public class MapProblems {

    /**
     * Returns true if any string appears at least 3 times in the given list; false otherwise.
     */
    public static boolean contains3(List<String> list) {
        Map<String, Integer> storage = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if (!storage.containsKey(list.get(i))) {
                storage.put(list.get(i), 0);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (storage.containsKey(list.get(i))) {
                storage.put(list.get(i), storage.get(list.get(i)) + 1);
            }
        }

        for (int counts : storage.values()) {
            if (counts >= 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a map containing the intersection of the two input maps.
     * A key-value pair exists in the output iff the same key-value pair exists in both input maps.
     */
    public static Map<String, Integer> intersect(Map<String, Integer> m1, Map<String, Integer> m2) {
        Map<String, Integer> storage = new HashMap<>();
        for (String key : m1.keySet()) {
            if (m2.containsKey(key)) {
                if (m1.get(key).equals(m2.get(key))) {
                    storage.put(key, m1.get(key));
                }
            }
        }
        return storage;
    }
}
