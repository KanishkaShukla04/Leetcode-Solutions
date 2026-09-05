class Solution{
    public int firstStableIndex(int[] nums, int k){
        int n=nums.length;

        int ansIdx=0;
        int globalMax=Integer.MIN_VALUE;
        int ansMax=Integer.MIN_VALUE;
        
        for(int i = 0; i < n; i++){
            globalMax = Math.max(globalMax, nums[i]);

            if(i == ansIdx)
                ansMax = Math.max(ansMax, nums[i]);

            if(nums[i] < ansMax - k){
                ansIdx = i + 1;
                ansMax = globalMax;
            }
        }

        return ansIdx < n ? ansIdx : -1;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna