package tree;

import java.util.Stack;

/**
 * @Description: 非递归实现二叉树后序遍历
 * @Author: ls
 * @Date: 2024/8/19 22:05
 */
class TreeNode_0 {
    int val;
    TreeNode_0 left;
    TreeNode_0 right;

    TreeNode_0(int val) {
        this.val = val;
    }
}
public class BinaryTreePostorderTraversalNonRecursive {
    public static void postorderTraversal(TreeNode_0 root){
        if(root == null){
            return;
        }
        Stack<TreeNode_0> stack = new Stack<>();
        TreeNode_0 pre = null;
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode_0 cur = stack.peek();
            if((cur.left == null && cur.right == null) || (pre != null && (pre == cur.left || pre == cur.right))){
                System.out.println(cur.val + " ");
                pre = cur;
                stack.pop();
            }else{
                //先将cur的右子树入栈(入栈相反，出栈就是先左后右）
                if(cur.right != null){
                    stack.push(cur.right);
                }
                //再将cur的左子树入栈
                if(cur.left != null){
                    stack.push(cur.left);
                }
            }
        }
    }
}
