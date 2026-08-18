class Solution {
    // Renamed from largestAlmostMissingInteger to largestInteger to match the test driver
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        // subarrayCount[x] tracks how many subarrays of size k contain the number x
        int[] subarrayCount = new int[51];
        
        // Slide a window of size k across the array
        for (int i = 0; i <= n - k; i++) {
            boolean[] seenInWindow = new boolean[51];
            for (int j = i; j < i + k; j++) {
                seenInWindow[nums[j]] = true;
            }
            // Increment the subarray count for all unique elements found in this window
            for (int x = 0; x <= 50; x++) {
                if (seenInWindow[x]) {
                    subarrayCount[x]++;
                }
            }
        }
        
        // Scan backwards from 50 to 0 to find the largest number with a count of exactly 1
        for (int x = 50; x >= 0; x--) {
            if (subarrayCount[x] == 1) {
                return x;
            }
        }
        
        return -1;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna