class Solution {
    public int minimumPushes(String word) {
        int pushes = 0;
        int n = word.length();
        
        for (int i = 0; i < n; i++) {
            pushes += (i / 8) + 1;
        }
        return pushes;
    }
}
