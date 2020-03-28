package qian.ling.yi.arithmetic;

import org.junit.Test;

public class CustomBinaryTree {
    class Node {
        Node left;
        Node right;
        int val;
        Node(int val) {
            setVal(val);
        }

        void add(Node node) {
                if (node.val > this.val) {
                    if (null != getRight()) {
                        getRight().add(node);
                    }else {
                        setRight(node);
                    }
                } else {
                    if (null != left) {
                        getLeft().add(node);
                    }else {
                        setLeft(node);
                    }
            }
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    public void inOrderTraversal(Node node) {
        if (null != node) {
            System.out.print(node.val + " ");
            inOrderTraversal(node.getLeft());
            inOrderTraversal(node.getRight());
        }
    }

    @Test
    public void testAdd(){
        Node root = new Node(10);
        inOrderTraversal(root);
        System.out.println();
        root.add(new Node(3));
        inOrderTraversal(root);
        System.out.println();
        root.add(new Node(4));
        inOrderTraversal(root);
        System.out.println();
        root.add(new Node(1));
        inOrderTraversal(root);
        System.out.println();
        root.add(new Node(11));
        inOrderTraversal(root);
        System.out.println();
        root.add(new Node(19));
        inOrderTraversal(root);
        System.out.println();
        root.add(new Node(20));
        inOrderTraversal(root);
        System.out.println();
    }



}
