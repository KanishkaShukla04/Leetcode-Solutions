import java.util.*;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        int[] suffixMatch = new int[n + 1];
        int w2Idx = m - 1;
        
        for (int i = n - 1; i >= 0; i--) {
            suffixMatch[i] = suffixMatch[i + 1];
            if (w2Idx >= 0 && word1.charAt(i) == word2.charAt(w2Idx)) {
                suffixMatch[i]++;
                w2Idx--;
            }
        }
        
        int[] ans = new int[m];
        Arrays.fill(ans, -1); // Initialize with -1 to safely handle structural checks
        int w1Idx = 0;
        boolean changed = false; 
        
        for (int i = 0; i < m; i++) {
            while (w1Idx < n) {
                boolean isExactMatch = (word1.charAt(w1Idx) == word2.charAt(i));
                
                if (isExactMatch) {
                    int remainingW2Needed = m - 1 - i;
                    if (!changed || suffixMatch[w1Idx + 1] >= remainingW2Needed) {
                        ans[i] = w1Idx;
                        w1Idx++;
                        break;
                    }
                } else {
                    if (!changed) {
                        int remainingW2Needed = m - 1 - i;
                        if (suffixMatch[w1Idx + 1] >= remainingW2Needed) {
                            ans[i] = w1Idx;
                            changed = true; 
                            w1Idx++;
                            break;
                        }
                    }
                }
                w1Idx++;
            }
        }
        
        // Decisive Fix: Verify the sequence is strictly increasing and completely filled
        if (ans[0] == -1) return new int[0];
        for (int i = 1; i < m; i++) {
            if (ans[i] <= ans[i - 1]) {
                return new int[0]; // Returns empty array for invalid/incomplete matches
            }
        }
        
        return ans;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna