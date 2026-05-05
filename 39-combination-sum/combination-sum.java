class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, candidates, target, new ArrayList<>(), result);
        return result;
    }
    private void dfs(int start, int[] candidates, int target,
                     List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) continue;
            path.add(candidates[i]);
            dfs(i, candidates, target - candidates[i], path, result); // reuse allowed
            path.remove(path.size() - 1);
        }
    }
}