class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        
        // Seed the initial elements based on the first 2 operations
        arr1[0] = nums[0];
        arr2[0] = nums[1];
        
        int i = 0, j = 0; // Tracks the last element index for arr1 and arr2
        
        for (int k = 2; k < n; k++) {
            if (arr1[i] > arr2[j]) {
                arr1[++i] = nums[k];
            } else {
                arr2[++j] = nums[k];
            }
        }
        
        // Concatenate arr2 directly into the remaining empty slots of arr1
        for (int k = 0; k <= j; k++) {
            arr1[++i] = arr2[k];
        }
        
        return arr1;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna