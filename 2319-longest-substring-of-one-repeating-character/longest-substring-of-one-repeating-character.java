import java.util.*;

class Solution {
    static class Node {
        int l, r;
        int lmx, rmx, mx;
        char lc, rc;
    }

    private Node[] tree;
    private char[] chars;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        this.chars = s.toCharArray();
        this.tree = new Node[n << 2];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node();
        }

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);
            chars[idx] = c;
            update(1, idx, c);
            ans[i] = tree[1].mx; // The root node always holds the global maximum
        }

        return ans;
    }

    private void build(int u, int l, int r) {
        tree[u].l = l;
        tree[u].r = r;
        if (l == r) {
            tree[u].lmx = tree[u].rmx = tree[u].mx = 1;
            tree[u].lc = tree[u].rc = chars[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build((u << 1) + 1, mid + 1, r);
        pushUp(u);
    }

    private void update(int u, int idx, char c) {
        if (tree[u].l == tree[u].r) {
            tree[u].lc = tree[u].rc = c;
            return;
        }
        int mid = (tree[u].l + tree[u].r) >> 1;
        if (idx <= mid) update(u << 1, idx, c);
        else update((u << 1) + 1, idx, c);
        pushUp(u);
    }

    private void pushUp(int u) {
        Node leftNode = tree[u << 1];
        Node rightNode = tree[(u << 1) + 1];

        tree[u].lc = leftNode.lc;
        tree[u].rc = rightNode.rc;
        tree[u].lmx = leftNode.lmx;
        tree[u].rmx = rightNode.rmx;
        tree[u].mx = Math.max(leftNode.mx, rightNode.mx);

        // If the boundaries meet and share the same character, merge them
        if (leftNode.rc == rightNode.lc) {
            if (leftNode.lmx == leftNode.r - leftNode.l + 1) {
                tree[u].lmx += rightNode.lmx;
            }
            if (rightNode.rmx == rightNode.r - rightNode.l + 1) {
                tree[u].rmx += leftNode.rmx;
            }
            tree[u].mx = Math.max(tree[u].mx, leftNode.rmx + rightNode.lmx);
        }
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna