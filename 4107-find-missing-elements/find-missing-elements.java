import java.util.*;

class Solution {
    // Renamed from findMissingNumbers to findMissingElements to match the test driver
    public List<Integer> findMissingElements(int[] nums) {
        boolean[] present = new boolean[101];
        int min = 101, max = 0;
        
        for (int x : nums) {
            present[x] = true;
            if (x < min) min = x;
            if (x > max) max = x;
        }
        
        List<Integer> missing = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (!present[i]) {
                missing.add(i);
            }
        }
        return missing;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna