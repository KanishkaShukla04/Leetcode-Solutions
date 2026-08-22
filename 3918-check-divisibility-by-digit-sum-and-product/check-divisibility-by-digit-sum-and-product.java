class Solution {
    public boolean checkDivisibility(int n) {
        int temp = n;
        int digitSum = 0;
        int digitProduct = 1;
        
        // Single pass to extract digits from right to left
        while (temp > 0) {
            int digit = temp % 10;
            digitSum += digit;
            digitProduct *= digit;
            temp /= 10;
        }
        
        int combinedSum = digitSum + digitProduct;
        
        // Return true if n is perfectly divisible by the combined total
        return n % combinedSum == 0;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna