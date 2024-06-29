//package Hello;
//
////import org.w3c.dom.Node;
//
//import org.w3c.dom.Node;
//
////import javax.swing.tree.TreeNode;
//import javax.swing.tree.TreeNode;
//import java.lang.reflect.Array;
//import java.util.*;
//
///**
// * @Description: TODO
// * @Author: ls
// * @Date: 2024/3/3114:37
// */
//public class Solution {
//    int ans = 0;
//    public int pseudoPalindromicPaths(TreeNode root){
//        dfs(root, 0);
//        return ans;
//    }
//    void dfs(TreeNode root, int cnt){
//        if(root.left == null && root.right == null){
//            cnt ^= 1 << root.val;
//            if(cnt == (cnt&-cnt)) ans++;
//            return;
//        }
//        if(root.left != null) dfs(root.left, cnt^(1<<root.val));
//        if(root.right != null) dfs(root.right, cnt^(1<<root.val));
//    }
//
//
//
//
//}
//class RecentCounter{
//    Queue<Integer> queue;
//
//    public RecentCounter(){
//        queue = new ArrayDeque<Integer>();
//    }
//
//    public int ping(int t){
//        queue.offer(t);
//        while(queue.peek() < t-3000){
//            queue.poll();
//        }
//        return queue.size();
//    }
//}
