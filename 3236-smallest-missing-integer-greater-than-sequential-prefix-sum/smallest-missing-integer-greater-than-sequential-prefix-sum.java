class Solution {
    public int missingInteger(int[] nums) {
        int sum = nums[0];
        
        // Step 1: Calculate the sum of the longest sequential prefix starting at index 0
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }
        
        // Step 2: Mark all numbers that exist in the input array
        boolean[] present = new boolean[51];
        for (int x : nums) {
            present[x] = true;
        }
        
        // Step 3: Find the smallest integer >= sum that is missing from nums
        while (sum < 51 && present[sum]) {
            sum++;
        }
        
        return sum;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna