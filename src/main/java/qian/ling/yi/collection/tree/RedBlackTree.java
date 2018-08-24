package qian.ling.yi.collection.tree;

/**
 * RedBlackTree
 *
 * 性质
 * 1. 节点颜色为 红、黑
 * 2. 根节点是 黑
 * 3. 叶节点（空）为黑
 * 4. 红节点的子节点都为黑
 * 5. 对于每个节点，到后代叶节点的简单路径，包含相等的黑色节点。
 * @author liuguobin
 * @date 2018/5/31
 */

public class RedBlackTree {
    TreeNode head ;
    class TreeNode{
        boolean red;
        TreeNode pre;
        TreeNode left;
        TreeNode right;
        int v;

        TreeNode(boolean red) {
            this.red = red;
        }

        TreeNode(int v) {
            this.v = v;
        }

        boolean isRight() {
            return this == this.getPre().getRight();
        }

        boolean isLeft() {
            return this == this.getPre().getLeft();
        }

        TreeNode(boolean red, int v) {
            this.red = red;
            this.v = v;
        }


        public boolean isRed() {
            return red;
        }

        public TreeNode setRed(boolean red) {
            this.red = red;
            return this;
        }

        public TreeNode getPre() {
            return pre;
        }

        public TreeNode setPre(TreeNode pre) {
            this.pre = pre;
            return this;
        }

        public TreeNode getLeft() {
            return left;
        }

        public TreeNode setLeft(TreeNode left) {
            this.left = left;
            return this;
        }

        public TreeNode getRight() {
            return right;
        }

        public TreeNode setRight(TreeNode right) {
            this.right = right;
            return this;
        }

        public int getV() {
            return v;
        }

        public TreeNode setV(int v) {
            this.v = v;
            return this;
        }
    }


    TreeNode mini(TreeNode x) {
        while (x.getLeft() != null) {
            x = x.getLeft();
        }
        return x;
    }

    TreeNode max(TreeNode x) {
        while (x.getRight() != null) {
            x = x.getRight();
        }
        return x;
    }


    public void insertNodes(int... a) {
        for (int i : a) {
            insertNode(i);
        }
    }

    public void insertNode(int v) {
        // 首先假定为红色
        TreeNode node = new TreeNode(true, v);
        if (null == head) {
            head = node.setRed(false);
            return;
        }
        addNode(head, node);
        fixUp(node);
    }

    private void addNode(TreeNode pre, TreeNode node) {
        if (node.getV() < pre.getV()) {
            addLeft(pre, node);
        }
        else {
            addRight(pre, node);
        }
    }


    private void addLeft(TreeNode pre, TreeNode node) {
        if (null == pre.getLeft()) {
            pre.setLeft(node);
            node.setPre(pre);
        }
        else {
            addNode(pre.getLeft(), node);
        }
    }

    private void addRight(TreeNode pre, TreeNode node) {
        if (null == pre.getRight()) {
            pre.setRight(node);
            node.setPre(pre);
        }
        else {
            addNode(pre.getRight(), node);
        }
    }

    //从插入节点走起
    private void fixUp(TreeNode z) {
        while (null != z.getPre()
                && z.getPre().isRed()) {

            if (z.getPre() == z.getPre().getPre().getLeft()) {
                TreeNode y = z.getPre().getPre().getRight();
                if (null != y && y.isRed()) {
                    z.getPre().setRed(false);
                    y.setRed(false);
                    z.getPre().getPre().setRed(true);
                    z = z.getPre().getPre();
                }
                // z 为 右边节点
                else if (z == z.getPre().getRight()) {
                    z = z.getPre();
                    leftRotate(z);
                }
                z.getPre().setRed(false);
                if(null != z.getPre().getPre()) {
                    z.getPre().getPre().setRed(true);
                    rightRotate(z.getPre().getPre());
                }
            } else {
                TreeNode y = z.getPre().getPre().getLeft();
                if (null != y && y.isRed()) {
                    z.getPre().setRed(false);
                    y.setRed(false);
                    z.getPre().getPre().setRed(true);
                    z = z.getPre().getPre();
                }
                else if (z==z.getPre().getLeft()) {
                    z = z.getPre();
                    rightRotate(z);
                }
                if(null != z.getPre()) {
                    z.getPre().setRed(false);
                    if(null != z.getPre().getPre()) {
                        z.getPre().getPre().setRed(true);
                        leftRotate(z.getPre().getPre());
                    }
                }
            }
        }
        head.setRed(false);
    }


    private void leftRotate(TreeNode node) {
        TreeNode y = node.getRight();
        node.setRight(y.getLeft());
        if (null != y.getLeft()){
            y.getLeft().setPre(node);
        }

        y.setPre(node.getPre());
        // 无父节点
        if (null == node.getPre()) {
            head = y;
            // 如果是左节点
        } else if (node == y.getPre().getLeft()) {
            node.getPre().setLeft(y);
            // 如果是右节点
        }else {
            node.getPre().setRight(y);
        }
        y.setLeft(node);
        node.setPre(y);
    }

    private void rightRotate(TreeNode node) {
        TreeNode y = node.getLeft();
        node.setLeft(y.getRight());
        if (null !=y.getRight()){
            y.getRight().setPre(node);
        }
        y.setPre(node.getPre());
        if (null == y.getPre()) {
            head = y;
        } else if (node == node.getPre().getRight()) {
            node.getPre().setRight(y);
        }else {
            node.getPre().setLeft(y);
        }
        y.setRight(node);
        node.setPre(y);
    }

    public void printNode() {
        print(0, head);
    }

    private void print(int c, TreeNode treeNode) {
        System.out.print(" 层：" + c);
        System.out.print(" --：" + (treeNode.isRed() ? "红" : "黑"));
        System.out.println(" v: " + treeNode.getV());

        if (null != treeNode.getLeft()) {
            System.out.print(" 左子节点. ");
            print(c+1, treeNode.getLeft());
        }

        if (null != treeNode.getRight()) {
            System.out.print(" 右子节点. ");
            print(c+1, treeNode.getRight());
        }

    }

    /**********************FIND****************************************************/
    public TreeNode findNode(int v) {
        if (head == null) {
            return null;
        }
        return getNode(head, v);

    }
    private TreeNode getNode(TreeNode node, int v) {
        if (node.getV() == v) {
            return node;
        }
        else if (node.getV() > v) {
            if (null == node.getLeft()) {
                return null;
            }
            else {
                return getNode(node.getLeft(), v);
            }
        }
        else {
            if (null == node.getRight()) {
                return null;
            }
            else {
                return getNode(node.getRight(), v);
            }
        }
    }


    /**********************DELETE****************************************************/

    /**
     * u->pre
     * v->pre
     * pre->v
     */

    void transplant(TreeNode u, TreeNode v) {
        if (u.getPre() == null) {
            head = v;
        }
        else if (u.isLeft()) {
            u.getPre().setLeft(v);
        }
        else {
           u.getPre().setRight(v);
        }
        if (v != null)
            v.setPre(u.getPre());
    }

    public void deleteNode(int v) {
        TreeNode node = findNode(v);
        if (null != node) {
            System.out.println("节点查询成功");
            delete(node);
        }
    }

    private void delete(TreeNode z) {
        TreeNode y = z;
        boolean oriColor = y.isRed();
        TreeNode x;
        if (z.getLeft() == null) {
            x = z.getRight();
            transplant(z, z.getRight());
        }
        else if (z.getRight() == null) {
            x = z.getLeft();
            transplant(z, z.getLeft());
        }
        else {
            y = mini(z.getRight());
            oriColor = y.isRed();
            x = y.getRight();
            if (x != null) {
                if (y.getPre() == z) {
                    x.setPre(y);
                } else {
                    transplant(y, y.getRight());
                    y.setRight(z.getRight());
                    y.getRight().setPre(y);
                }
            }
            transplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setPre(y);
            y.setRed(z.isRed());
        }

        if (!oriColor) {
            deleteFixUp(x);
        }
    }

    private void deleteFixUp(TreeNode x) {
        while( x != head && !x.isRed()) {
            TreeNode w;
            if (x.isLeft()) {
                w = x.getPre().getRight();
                if (null != w) {
                    if (w.isRed()) {
                        w.setRed(false);
                        x.getPre().setRed(true);
                        leftRotate(x.getPre());
                        w = x.getPre().getRight();
                    }

                    if ((null != w.getLeft() && !w.getLeft().isRed()) &&
                            (null != w.getRight() && !w.getRight().isRed())) {
                        w.setRed(true);
                        x = x.getPre();
                    } else if (null != w.getRight() && !w.getRight().isRed()) {
                        if (null != w.getLeft()) {
                            w.getLeft().setRed(false);
                        }
                        w.setRed(true);
                        rightRotate(w);
                        w = x.getPre().getRight();
                    }

                    w.setRed(x.getPre().isRed());
                    x.getPre().setRed(false);
                    w.getRight().setRed(false);
                    leftRotate(x.getPre());
                    x = head;
                }
            }
            else {
                w = x.getPre().getLeft();
                if (null != w) {
                    if (w.isRed()) {
                        w.setRed(false);
                        x.getPre().setRed(true);
                        leftRotate(x.getPre());
                        w = x.getPre().getLeft();
                    }

                    if (!w.getLeft().isRed() && !w.getRight().isRed()) {
                        w.setRed(true);
                        x = x.getPre();
                    } else if (!w.getLeft().isRed()) {
                        w.getRight().setRed(false);
                        w.setRed(true);
                        rightRotate(w);
                        w = x.getPre().getLeft();
                    }
                    w.setRed(x.getPre().isRed());
                    x.getPre().setRed(false);
                    w.getLeft().setRed(false);
                    leftRotate(x.getPre());
                    x = head;
                }
            }

        }
        x.setRed(false);
    }


    void deleteNode(TreeNode node) {
        final TreeNode right = node.getRight();
        final TreeNode left = node.getLeft();

        if (head == node) {
            if (right == null) {
                head = left;
            } else if (left == null) {
                head = right;
            } else {

            }
        }
        if (right == null) {
            if (node.isLeft()) {
                node.getPre().setLeft(left);
                if (null != left) {
                    left.setPre(node.getPre());
                }
            } else {
                node.getPre().setLeft(left);
                if (null != left) {
                    left.setPre(node.getPre());
                }
            }
        } else if (left == null) {
            if (node.isLeft()) {
                node.getPre().setLeft(right);
                right.setPre(node.getPre());
            } else {
                node.getPre().setRight(right);
                right.setPre(node.getPre());
            }

        }
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
//        tree.insertNode(1);
//        tree.printNode();
//        tree.insertNode(2);
//        tree.printNode();
//        tree.insertNode(3);
//        tree.printNode();
//        tree.insertNode(4);
//        tree.printNode();
//        tree.insertNode(5);
//        tree.printNode();
//        tree.insertNode(6);
//        tree.printNode();
//        tree.insertNode(7);
//        tree.printNode();

//        tree.insertNodes(100,34,29,55,70,65,1);
        tree.insertNodes(34,29,55);
        tree.printNode();
        tree.deleteNode(34);
//        tree.deleteNode(70);
        tree.printNode();
//        tree.insertNode(3);

    }






}
