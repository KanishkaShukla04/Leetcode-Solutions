class Solution {
    public int reverseDegree(String s) {
        int sum=0; 
        int n=s.length();
        for(int i=0;i<n;i++){
            sum+=(i+1)*('z'-s.charAt(i)+1);
        }
        return sum;
        
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna