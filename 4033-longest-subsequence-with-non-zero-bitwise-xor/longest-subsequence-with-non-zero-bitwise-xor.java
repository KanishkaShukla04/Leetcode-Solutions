class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean hasNonZero = false;
        
        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }
        
        // Case 1: The entire array's XOR is already non-zero
        if (totalXor != 0) return nums.length;
        
        // Case 2: Total XOR is 0, but we can drop exactly 1 non-zero element
        if (hasNonZero) return nums.length - 1;
        
        // Case 3: The array contains nothing but zeros
        return 0;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna