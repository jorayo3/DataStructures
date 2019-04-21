package hw3.hash;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {

        /*
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        HashMap<Double, Integer> map = new HashMap<>();
        int N = oomages.size();
        for (Oomage o : oomages) {
            double bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            if (!map.containsKey(bucketNum)) {
                map.put(bucketNum, 0);
            } else {
                map.put(bucketNum, map.get(bucketNum) + 1);
            }
        }

        Collection<Integer> values = map.values();
        for (int i : values) {
            if (i < N / 50 || i > N / 2.5) {
                return false;
            }
        }
        return true;
    }
}
