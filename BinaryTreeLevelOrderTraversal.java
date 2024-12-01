// Algo: Use a simple BFS using a queue
// Any problems faced: calculating SC for BFS with Q
// LC 102

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {
    /**
     * TC: O(n)
     * SC: O(n/2) == the leaf nodes in case of a complete BST
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder_bfs(TreeNode root) {
        List<List<Integer>> bfs = new ArrayList<>();
        // check for edge case if root is null
        if (root == null) {
            return bfs;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                res.add(curr.val);
                // avoid null entries into q
                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }
            bfs.add(res);
        }
        return bfs;
    }

    /**
     * TC: O(n)
     * SC: O(h), in the worst case due to skewed tree, it can be O(n)
     * <p>
     * NOTE: any pre, post, in order traversals work fine here as we're adding the node to a level in a sequential manner
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder_dfs(TreeNode root) {
        List<List<Integer>> bfs = new ArrayList<>();
        // check for edge case if root is null
        if (root == null) {
            return bfs;
        }
        dfs(bfs, root, 0);
        return bfs;
    }

    private void dfs(List<List<Integer>> bfs, TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (bfs.size() == level) {
            bfs.add(new ArrayList<>());
        }
        List<Integer> res = bfs.get(level);
        res.add(root.val);
        dfs(bfs, root.left, level + 1);
        dfs(bfs, root.right, level + 1);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}