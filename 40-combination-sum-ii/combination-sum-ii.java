import java.util.*;
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // key step
        dfs(0, candidates, target, new ArrayList<>(), result);
        return result;
    }
    private void dfs(int start, int[] nums, int target,
                     List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            if (nums[i] > target) break;
            path.add(nums[i]);
            dfs(i + 1, nums, target - nums[i], path, result); // i+1 → no reuse
            path.remove(path.size() - 1);
        }
    }
}