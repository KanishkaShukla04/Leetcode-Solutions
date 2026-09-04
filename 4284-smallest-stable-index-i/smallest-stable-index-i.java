class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }

        // Step 1: Precompute the suffix minimums
        // suffixMin[i] will store min(nums[i..n-1])
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        // Step 2: Iterate from left to right to find the first stable index
        int runningMax = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            runningMax = Math.max(runningMax, nums[i]);
            
            // Instability score: max(nums[0..i]) - min(nums[i..n-1])
            int instabilityScore = runningMax - suffixMin[i];
            
            if (instabilityScore <= k) {
                return i; // Return the first (smallest) stable index found
            }
        }
        return -1;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna