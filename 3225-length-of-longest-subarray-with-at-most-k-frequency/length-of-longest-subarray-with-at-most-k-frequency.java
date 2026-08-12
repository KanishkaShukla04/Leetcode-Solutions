import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        
        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];
            counts.put(num, counts.getOrDefault(num, 0) + 1);
            
            // Shrink the window from the left if the frequency of the current element exceeds k
            while (counts.get(num) > k) {
                counts.put(nums[left], counts.get(nums[left]) - 1);
                left++;
            }
            
            // Track the maximum contiguous window length encountered
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna