public class Traversal {
    /**
     * 144. Binary Tree Preorder Traversal
     * 94. Binary Tree Inorder Traversal
     * 145. Binary Tree Postorder Traversal
     * 285. Inorder Successor in BST
     * 314. Binary Tree Vertical Order Traversal
     * Pre/in/post-order traversal has recursion/iteration/morris solutions.
     */
    // Using recursion[Pre-order]:
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root);
        return res;
    }    
    private void helper(List<Integer> res, TreeNode root) {
        res.add(root);
        if (root.left != null) helper(res, root.left);
        if (root.right != null) helper(res, root.right);
    }
    // [In-order]:
    private void helper(List<Integer> res, TreeNode root) {        
        if (root.left != null) helper(res, root.left);
        res.add(root);
        if (root.right != null) helper(res, root.right);
    }
    // [Post-order]:
    private void helper(List<Integer> res, TreeNode root) {
        if (root.left != null) helper(res, root.left);
        if (root.right != null) helper(res, root.right);
        res.add(root);
    }
    
    /** Using iteration: 
     * Pre/in-order use stack + cur, 
     * Post-order use stack+cur+prev, or root, right, left then reverse order
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !s.isEmpty()) {
            if (cur != null) {
                res.add(cur.val);
                s.push(cur);        // TreeNode in stack: itself already been added to res, but right subTree NEED To be processed.
                cur = cur.left;
            } else {    // need to get next TreeNode
                cur = s.pop();
                cur = cur.right;
            }
        }
        return res;
    }
    
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !s.isEmpty()) {
            if (cur != null) {
                s.push(cur);     // cur itself hasn't been added into res, will keep track of left side.
                cur = cur.left;
            } else {
                cur = s.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }
    /** post-order, left, right, root
     * [SOLUTION-1]: Insert in root/right/left order, after insert all nodes, reverse.
     *               To make sure nodes are come in right/left, since stack is LIFO, need to insert left first, then right
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode cur = s.pop();
            res.add(cur.val);
            if (cur.left != null) s.push(cur.left);
            if (cur.right != null) s.push(cur.right);
        }
        Collections.reverse(res);
        return res;
    }
    /**
     * [SOLUTION-2]: need prev to help identify which condition cur is in.
     *  if prev is cur's parent/cur == root: 
     *  1. cur is new,(root/just pop from stack), hasn't explored left/right/itself
     *  else if cur.left = prev:  
     *  2. cur has just explored left-subtree, need to explore right side
     *  else 
     *  3. cur has explored both sides, now need to check itself, and at this time, it is still peek in stack.
     *     so after this is done, pop it from stack.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        TreeNode prev = null;
        s.push(root);
        while (!s.isEmpty()) {
            cur = s.peek(); // peek one is the one being processed
            // if cur is child of prev, it needs to explore all nodes under cur. 
            if (prev == null || prev.left == cur || prev.right == cur) {
                if (cur.left != null) {
                    s.push(cur.left);
                } else if (cur.right != null) {
                    s.push(cur.right);
                }
            } else if (cur.left == prev) {  // just finished left sub-tree, check if there are nodes on right side
                if (cur.right != null) s.push(cur.right);
            } else {    // already processed left + right, work on cur itself.
                res.add(cur.val);
                s.pop();    // already processed.
            }
            prev = cur;     
        }
        return res;
    }
}
