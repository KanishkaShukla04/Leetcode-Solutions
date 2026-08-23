class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int leftSum = 0, rightSum = 0;
        int leftQ = 0, rightQ = 0;
        
        // Step 1: Scan the first half
        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                leftQ++;
            } else {
                leftSum += num.charAt(i) - '0';
            }
        }
        
        // Step 2: Scan the second half
        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                rightQ++;
            } else {
                rightSum += num.charAt(i) - '0';
            }
        }
        
        // Step 3: Compute differences
        int sumDiff = leftSum - rightSum;
        int qDiff = leftQ - rightQ;
        
        // Bob wins if and only if the sum difference perfectly offsets 
        // the question mark pairs (each pair contributing a value of 9).
        // Rewritten without floating point division: 2 * sumDiff + 9 * qDiff == 0
        if (2 * sumDiff + 9 * qDiff == 0) {
            return false; // Bob wins
        }
        
        return true; // Alice wins
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna