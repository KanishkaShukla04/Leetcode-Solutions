class Solution {
    public int maximumLengthSubstring(String s) {
        int[] counts = new int[26];
        int maxLen = 0, left = 0, n = s.length();
        
        for (int right = 0; right < n; right++) {
            int charIdx = s.charAt(right) - 'a';
            counts[charIdx]++;
            
            // Shrink window from the left if the frequency constraint is breached
            while (counts[charIdx] > 2) {
                counts[s.charAt(left) - 'a']--;
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna