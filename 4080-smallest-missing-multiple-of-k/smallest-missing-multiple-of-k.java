class Solution {
    public int missingMultiple(int[] nums, int k) {
         boolean[] present = new boolean[101];
        for (int x : nums) {
            if (x <= 100) {
                present[x] = true;
            }
        }
        
        int multiple = k;
        // Keep stepping by k until we hit a number missing from our presence map
        while (multiple <= 100 && present[multiple]) {
            multiple += k;
        }
        return multiple;
        
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna