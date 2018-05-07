package com.kspt.palkin;


public class BinaryTree {

    static class Node {
        public int value;
        public boolean init;
        private Node parent;
        private Node left, right;

        Node(int value, Node p) {
            this.value = value;
            this.parent = p;
            right = null;
            left = null;
            init = true;
        }

        Node() {
        }
    }

    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    /**
     * @param KeyRoot initial value for root node
     */
    public BinaryTree(int KeyRoot) {
        this.root = new Node(KeyRoot, null);
    }

    public static String printTree(Node node) {
        StringBuilder str = new StringBuilder();
        if (node.init) {
            if (node.parent == null) {
                str.append(String.format("ROOT:%d\n", node.value));
            } else {
                if (node.parent.left == node) {
                    str.append(String.format("Left for %d --> %d",
                            node.parent.value, node.value));
                }
                if (node.parent.right == node) {
                    str.append(String.format("Right for %d --> %d",
                            node.parent.value, node.value));
                }
            }
            if (node.left.init)
                str.append(printTree(node.left));
            if (node.right.init)
                str.append(printTree(node.right));
        }
        return str.toString();
    }

    public Node getRoot() {
        return root;
    }


    /**
     * @param cur current node for recursive search
     * @param key search key
     */
    private Node search(Node cur, int key) {
        if (cur == null || key == cur.value)
            return cur;
        if (key < cur.value)
            return search(cur.left, key);
        else
            return search(cur.right, key);
    }
}







