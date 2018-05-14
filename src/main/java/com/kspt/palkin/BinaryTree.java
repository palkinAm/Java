package com.kspt.palkin;


import java.util.NoSuchElementException;

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

    enum Searchmode {Node, Parent, Left, Right}

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
     * @return node with the search key
     */
    private Node search(Node cur, int key) {
        if (cur == null || key == cur.value)
            return cur;
        if (key < cur.value)
            return search(cur.left, key);
        else
            return search(cur.right, key);
    }

    public void add(int key) {
        Node x = root, y = null;
        while (x != null) {
            if (key == x.value)
                throw new IllegalArgumentException("Duplicate keys");
            else {
                y = x;
                if (key < x.value)
                    x = x.left;
                else
                    x = x.right;
            }
        }
        Node res = new Node(key, y);
        if (y == null)
            root = res;
        else {
            if (y.value > key)
                y.left = res;
            else
                y.right = res;
        }
    }

    public void add(int[] keys) {
        for (int k : keys) {
            this.add(k);
        }
    }

    /**
     * @param key that must be removed
     */
    public void remove(int key) {
        Node cur = search(root, key), parent = cur.parent;
        if (cur == null) return;
        //First variant. Cur have only left subnode
        if (cur.right == null) {
            if (parent == null) {
                root = cur.left;
            } else {
                if (parent.left == cur) {
                    parent.left = cur.left;
                } else {
                    parent.right = cur.left;
                }
            }
        } else {
            Node left = cur.right;
            parent = null;
            while (left.left != null) {
                parent = left;
                left = left.left;
            }
            if (parent != null) {
                parent.left = left.right;
            } else {
                cur.right = left.right;
            }
            cur.value = left.value;
        }
    }

    private String print(Node t) {
        StringBuilder res = new StringBuilder();
        if (t != null) {
            res.append(print(t.left));
            res.append(t.value).append(" ");
            res.append(print(t.right));
        }
        return res.toString();
    }

    public String toString() {
        return print(root).trim();
    }

    public Node getNode(int key) {
        return getNode(key, Searchmode.Node);
    }
    /**
     * @param key  key of searching node
     * @param mode Node - get node, Parent - get parent
     *             Left - get left subnode, Right - get right subnode
     * @return searched node
     */
    public Node getNode(int key, Searchmode mode) {
        Node res = search(root, key);
        if (res == null)
            throw new NoSuchElementException("This key not found or have not parent ");
        switch (mode) {
            case Node:
                return res;
            case Parent:
                return res.parent != null ? res.parent : new Node();
            case Left:
                return res.left != null ? res.left : new Node();
            default:
                return res.right != null ? res.right : new Node();
        }
    }
}








