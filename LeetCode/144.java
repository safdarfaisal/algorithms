import com.sun.source.tree.Tree;

import java.util.*;

class Scratch {
    public static void main(String[] args) {
        
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> iterTraversal = new Stack<TreeNode>();
        List<Integer> list = new ArrayList<Integer>();

        iterTraversal.push(root);

        while (!iterTraversal.isEmpty()){
            TreeNode currentNode = new TreeNode();
            currentNode = iterTraversal.pop();

            if(currentNode.right != null)
                iterTraversal.push(currentNode.right);
            if(currentNode.left != null)
                iterTraversal.push(currentNode.left);
            list.add(currentNode.val);
        }
        return list;
    }
    
}