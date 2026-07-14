class Solution {
    public int subsequencePairCount(int[] nums) {
        int maxNum = 0, MOD = 1_000_000_007;
        for (int num : nums) maxNum = Math.max(maxNum, num);
        
        int[][] dp = new int[maxNum + 1][maxNum + 1];
        dp[0][0] = 1; // Base case: both subsequences are empty
        
        for (int num : nums) {
            int[][] next = new int[maxNum + 1][maxNum + 1];
            for (int i = 0; i <= maxNum; i++) {
                for (int j = 0; j <= maxNum; j++) {
                    if (dp[i][j] == 0) continue;
                    
                    long currentCount = dp[i][j];
                    next[i][j] = (int) ((next[i][j] + currentCount) % MOD);
                    
                    int nextI = (i == 0) ? num : gcd(num, i);
                    next[nextI][j] = (int) ((next[nextI][j] + currentCount) % MOD);
                    
                    int nextJ = (j == 0) ? num : gcd(num, j);
                    next[i][nextJ] = (int) ((next[i][nextJ] + currentCount) % MOD);
                }
            }
            dp = next;
        }
        
        int ans = 0;
        for (int i = 1; i <= maxNum; i++) {
            ans = (ans + dp[i][i]) % MOD;
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
