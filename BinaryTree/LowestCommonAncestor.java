public class LowestCommonAncestor {
    /**
     * 235. Lowest Common Ancestor of a Binary Search Tree
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
     * (we allow a node to be a descendant of itself)
     
     * 236. Lowest Common Ancestor of a Binary Tree
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
     */
     // BST contains distinct keys, so only need to check val to decide go left/right/return root.
     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                return lowestCommonAncestor(root.left, p, q);
            } else if (root.val < p.val && root.val < q.val) {
                return lowestCommonAncestor(root.right, p, q);
            } else {
                return root;
            }
        }
        return null;
     }
     
     /**
      * [SOLUTION-1] Can't choose left/right based on val now.. need to check both paths.
      *              can use similar solution as 235.Check left, check right, then check root.(post-order traversal)
      *              if left/right return p/q, means root is the LCA. If left return null, means no p/q found in left path, p/q both in right.
      * Recursion, Time: O(n) post-order traversal.
      */
     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
     }
     
     /**
      * [SOLUTION-2] Using a Map, level traversal in <child, parent> and make sure p, q both inserted into map.
      *              Then insert all p->p's parent->parent^2... into set, and use q->q's parent... along this way to 
      *              check first in set :)
      */
     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();      // Use a Stack to do level-traversal, get cur node,
        HashMap<TreeNode, TreeNode> parent = new HashMap<>();   // insert <cur, parent> into map
        stack.push(root);
        parent.put(root, null);
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode cur = stack.pop();
            if (cur.left != null) {
                stack.push(cur.left);
                parent.put(cur.left, cur);
            }
            if (cur.right != null) {
                stack.push(cur.right);
                parent.put(cur.right, cur);
            }
        }
        Set<TreeNode> set = new HashSet<>();
        while (p != null) {
            set.add(p);
            p = parent.get(p);
        }
        while (!set.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }
}
