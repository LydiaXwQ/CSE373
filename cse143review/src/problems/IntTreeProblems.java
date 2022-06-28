package problems;

import datastructures.IntTree;
// Checkstyle will complain that this is an unused import until you use it in your code.
import datastructures.IntTree.IntTreeNode;

/**
 * See the spec on the website for tips and example behavior.
 *
 * Also note: you may want to use private helper methods to help you solve these problems.
 * YOU MUST MAKE THE PRIVATE HELPER METHODS STATIC, or else your code will not compile.
 * This happens for reasons that aren't the focus of this assignment and are mostly skimmed over in
 * 142 and 143. If you want to know more, you can ask on the discussion board or at office hours.
 *
 * REMEMBER THE FOLLOWING RESTRICTIONS:
 * - do not call any methods on the `IntTree` objects
 * - do not construct new `IntTreeNode` objects (though you may have as many `IntTreeNode` variables
 *      as you like).
 * - do not construct any external data structures such as arrays, queues, lists, etc.
 * - do not mutate the `data` field of any node; instead, change the tree only by modifying
 *      links between nodes.
 */

public class IntTreeProblems {

    /**
     * Computes and returns the sum of the values multiplied by their depths in the given tree.
     * (The root node is treated as having depth 1.)
     */
    public static int depthSum(IntTree tree) {
        int depth = 1;
        return helperSum(tree.overallRoot, depth);
    }

    private static int helperSum(IntTreeNode root, int depth) {
        if (root == null) {
            return 0;
        } else {
            return depth * root.data + helperSum(root.left, depth + 1) + helperSum(root.right, depth + 1);
        }
    }

    /**
     * Removes all leaf nodes from the given tree.
     */
    public static void removeLeaves(IntTree tree) {
        tree.overallRoot = removeHelper(tree.overallRoot);
    }

    private static IntTreeNode removeHelper(IntTreeNode root) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                root = null;
            } else {
                root.left = removeHelper(root.left);
                root.right = removeHelper(root.right);
            }
        }
        return root;

    }

    /**
     * Removes from the given BST all values less than `min` or greater than `max`.
     * (The resulting tree is still a BST.)
     */
    public static void trim(IntTree tree, int min, int max) {
        tree.overallRoot = trimHelper(tree.overallRoot, min, max);
    }

    private static IntTreeNode trimHelper(IntTreeNode root, int min, int max) {
        if (root == null) {
            return null;
        }

        if (root.data < min) {
            return trimHelper(root.right, min, max);
        }

        if (root.data > max) {
            return trimHelper(root.left, min, max);
        }

        root.left = trimHelper(root.left, min, max);
        root.right = trimHelper(root.right, min, max);
        return root;
    }
}
