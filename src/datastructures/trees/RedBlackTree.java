package datastructures.trees;

import java.util.Objects;

/**
 * Class implements the Red-Black tree
 * Properties:
 * 1) node colour is either red or black
 * 2) root.color() = black
 * 3) leaf(null) = black
 * 4) if node.color() == RED --> children both black
 * 5) For each node: is the same number of black nodes
 * @param <T>
 */
public class RedBlackTree<T extends Comparable<T>> {
    /**
     * The root of the RBT
     */
    private Node root;
    private final Node leafNode;

    public RedBlackTree() {
        leafNode = new Node(null);
        root = leafNode;
    }

    enum Colour {
        RED,
        BLACK
    }

    class Node { //use just as a struct
        public T key;
        public Colour colour;

        public Node left;
        public Node right;
        public Node parent;

        Node(T key) {
            this.key = key;
            colour = Colour.RED;
            left = leafNode;
            right = leafNode;
            parent = leafNode;
        }

        protected boolean hasChildRed() {
            return  (left != leafNode && left.colour == Colour.RED)
                    ||
                    (right != leafNode && right.colour == Colour.RED);
        }

        protected boolean getLeft() {
            return parent.left == this;
        }

        protected Node sibling() {
            if (parent == leafNode) {
                return leafNode;
            }
            if (getLeft()) {
                return parent.right;
            }
            return parent.left;
        }
        protected void replaceWithNodeBelow(Node newParent) {
            if (parent != leafNode) {
                if (getLeft()) {
                    parent.left = newParent;
                } else {
                    parent.right = newParent;
                }
            }
            newParent.parent = parent;
            parent = newParent;
        }
    }

    // Rotate right by node
    private Node turnRight(Node node) {
        Node newParent = node.left;

        if (node == root) {
            root = newParent;
        }

        node.replaceWithNodeBelow(newParent);
        node.left = newParent.right;
        if (newParent.right != leafNode) {
            newParent.right.parent = node;
        }
        newParent.right = node;
        return newParent;
    }

    // Rotate left by node
    private Node turnLeft(Node node) {
        Node newParent = node.right;

        if (node == root) {
            root = newParent;
        }

        node.replaceWithNodeBelow(newParent);
        node.right = newParent.left;
        if (newParent.left != leafNode) {
            newParent.left.parent = node;
        }
        newParent.left = node;
        return newParent;
    }

    /**
     * Insert Node with data = key in RBT
     * Complexity: O(log(n)) average and worst case
     * @param key the data you want to include in RBT
     */
    public void insert(T key) {
        if (root == leafNode) {
            root = new Node(key);
            root.colour = Colour.BLACK;
        } else {
            root = insertionStep(root, key);
        }
    }

    private boolean l = false;
    private boolean lr = false;
    private boolean rl = false;
    private boolean r = false;
    private Node insertionStep(Node curRoot, T key) {
        boolean problemRR = false;

        if (curRoot == leafNode) {
            return new Node(key);
        } else if (key.compareTo(curRoot.key) < 0) {
            curRoot.left = insertionStep(curRoot.left, key);
            curRoot.left.parent = curRoot;

            if (curRoot != root) {
                if (curRoot.colour == Colour.RED && curRoot.colour == curRoot.left.colour) {
                    problemRR = true;
                }
            }
        } else {
            curRoot.right = insertionStep(curRoot.right, key);
            curRoot.right.parent = curRoot;

            if (curRoot != root) {
                if (curRoot.colour == Colour.RED && curRoot.colour == curRoot.right.colour) {
                    problemRR = true;
                }
            }
        }

        if (this.l) {
            curRoot = turnLeft(curRoot);
            curRoot.left.colour = Colour.RED;
            curRoot.colour = Colour.BLACK;
            this.l = false;
        } else if (this.r) {
            curRoot = turnRight(curRoot);
            curRoot.right.colour = Colour.RED;
            curRoot.colour = Colour.BLACK;
            this.r = false;
        } else if (this.rl) {
            curRoot.right = turnRight(curRoot.right);
            curRoot.right.parent = curRoot;
            curRoot = turnLeft(curRoot);
            curRoot.colour = Colour.BLACK;
            curRoot.left.colour = Colour.RED;
            this.rl = false;
        } else if (this.lr) {
            curRoot.left = turnLeft(curRoot.left);
            curRoot.left.parent = curRoot;
            curRoot = turnRight(curRoot);
            curRoot.colour = Colour.BLACK;
            curRoot.right.colour = Colour.RED;
            this.lr = false;
        }

        int whichChild = -1;
        if (problemRR) {
            if (curRoot.parent.right == curRoot) {
                whichChild = 0;
            } else if (curRoot.parent.left == curRoot) {
                whichChild = 1;
            }

            switch (whichChild) {
                case 0 ->
                {
                    if (curRoot.sibling() == leafNode || curRoot.sibling().colour == Colour.BLACK) {
                        if (curRoot.left != leafNode && curRoot.left.colour == Colour.RED) {
                            this.rl = true;
                        } else if (curRoot.right != leafNode && curRoot.right.colour == Colour.RED) {
                            this.l = true;
                        }
                    } else {
                        curRoot.sibling().colour = Colour.BLACK;
                        curRoot.colour = Colour.BLACK;
                        if (curRoot.parent != root) {
                            curRoot.parent.colour = Colour.RED;
                        }
                    }
                }
                case 1 ->
                {
                    if (curRoot.sibling() == leafNode || curRoot.sibling().colour == Colour.BLACK) {
                        if (curRoot.left != leafNode && curRoot.left.colour == Colour.RED) {
                            this.r = true;
                        } else if (curRoot.right != leafNode && curRoot.right.colour == Colour.RED) {
                            this.lr = true;
                        }
                    } else {
                        curRoot.sibling().colour = Colour.BLACK;
                        curRoot.colour = Colour.BLACK;
                        if (curRoot.parent != root) {
                            curRoot.parent.colour = Colour.RED;
                        }
                    }
                }
            }
        }

        return curRoot;
    }

    // Swap data between node1 and node2
    protected void swapValues(Node n1, Node n2) {
        T temp = n1.key;
        n1.key = n2.key;
        n2.key = temp;
    }

    // Perform an ordinary replacement according to BST rules
    protected Node replaceBST(Node del) {
        if (del == leafNode) {
            throw new NullPointerException("Node is null");
        }

        int numOfChildren = 0;
        if (del.left != leafNode) {
            numOfChildren++;
        }
        if (del.right != leafNode) {
            numOfChildren++;
        }

        // Case 1: leaf
        if (numOfChildren == 0) {
            return leafNode;
        }

        // Case 2: one child
        if (numOfChildren == 1) {
            return Objects.requireNonNullElseGet(del.left, () -> del.right);
        }

        // Case 3: two children
        return predBelow(del.left);
    }

    protected void fixBLACK2(Node curRoot) {
        if (curRoot == root) {
            return;
        }
        Node sibling = curRoot.sibling();
        Node parent = curRoot.parent;
        if (sibling == leafNode) {
            fixBLACK2(parent);
        } else {
            if (sibling.colour == Colour.RED) {
                parent.colour = Colour.RED;
                sibling.colour = Colour.BLACK;
                if (sibling.getLeft()) {
                    turnRight(parent);
                } else {
                    turnLeft(parent);
                }
                fixBLACK2(curRoot);
            } else {
                if (sibling.hasChildRed()) {
                    if (sibling.left != leafNode && sibling.left.colour == Colour.RED) {
                        if (sibling.getLeft()) {
                            sibling.left.colour = sibling.colour;
                            sibling.colour = parent.colour;
                            turnRight(parent);
                        } else {
                            sibling.left.colour = parent.colour;
                            turnRight(sibling);
                            turnLeft(parent);
                        }
                    } else {
                        if (sibling.getLeft()) {
                            sibling.right.colour = parent.colour;
                            turnLeft(sibling);
                            turnRight(parent);
                        } else {
                            sibling.right.colour = sibling.colour;
                            sibling.colour = parent.colour;
                            turnLeft(parent);
                        }
                    }
                    parent.colour = Colour.BLACK;
                } else {
                    sibling.colour = Colour.RED;
                    if (parent.colour == Colour.BLACK) {
                        fixBLACK2(parent);
                    } else {
                        parent.colour = Colour.BLACK;
                    }
                }
            }
        }
    }

    private void deleteStep(Node del) {
        if (del == leafNode) {
            return;
        }

        Node rep = replaceBST(del);

        if (rep == leafNode) {
            if (del == root) {
                root = leafNode;
            } else {
                if (del.colour == Colour.BLACK) {
                    fixBLACK2(del);
                } else {
                    if (del.sibling() != leafNode) {
                        del.sibling().colour = Colour.RED;
                    }
                }

                if (del.getLeft()) {
                    del.parent.left = leafNode;
                } else {
                    del.parent.right = leafNode;
                }
            }
            return;
        }

        if (del.left == leafNode || del.right == leafNode) {
            if (del == root) {
                root = new Node(rep.key);
                root.left = root.right = leafNode;
                root.colour = Colour.BLACK;
            } else {
                if (del.getLeft()) {
                    del.parent.left = rep;
                } else {
                    del.parent.right = rep;
                }
                rep.parent = del.parent;
                if (rep.colour == Colour.BLACK && del.colour == Colour.BLACK) {
                    fixBLACK2(rep);
                } else {
                    rep.colour = Colour.BLACK;
                }
            }
            return;
        }

        swapValues(del, rep);
        deleteStep(rep);
    }

    /**
     * Delete node with the same data == key
     * Complexity: O(log(n)) average and worst case
     * @param key data which will be deleted from the RBT
     */
    public void remove(T key) {
        deleteStep(find(root, key));
    }

    public Node find(T key) {
        return find(root, key);
    }

    /**
     * Find the node in the RBT with data = key
     * Complexity: O(log(n)) average and worst case
     * @param curRoot more often - root of the RBT
     * @param key data which will be found in the RBT
     * @return Node with the data = key
     */
    private Node find(Node curRoot, T key) {
        if(curRoot == leafNode) {
            throw new NullPointerException("Node is null");
        }
        if (key.compareTo(curRoot.key) == 0) {
            return curRoot;
        }
        if (curRoot.left != leafNode && key.compareTo(curRoot.key) < 0) {
            return find(curRoot.left, key);
        }
        if (curRoot.right != leafNode && key.compareTo(curRoot.key) > 0) {
            return find(curRoot.right, key);
        }
        throw new NullPointerException("Node with key " + key + " is null");
    }

    // Find the lower predecessor of the node
    protected Node predBelow(Node node) {
        if (node == leafNode) {
            throw new NullPointerException("Node is null");
        }
        Node temp = node;

        while (temp.right != leafNode) {
            temp = temp.right;
        }

        return temp;
    }

    /**
     * Find predecessor of the node
     * Time complexity: O(h), h - height of tree
     * @param node current node
     * @return predecessor of current node (inorder traversal)
     */
    public Node pred(Node node) {
        if (node == leafNode) {
            throw new NullPointerException("Node is null");
        }

        if (node.left != leafNode) {
            return maxNode(node.left);
        }

        Node parent = node.parent;
        while (parent != leafNode && node == parent.left) {
            node = parent;
            parent = parent.parent;
        }

        return parent;
    }

    // Find maxNode to find predecessor (Going right and down while node exist)
    private Node maxNode(Node node) {
        if (node == leafNode) {
            throw new NullPointerException("Node is null");
        }
        Node temp = node;

        while (temp.right != leafNode) {
            temp = temp.right;
        }

        return temp;
    }

    /**
     * Find successor of the node
     * Time complexity: O(h), h - height of tree
     * @param node current node
     * @return successor of current node (inorder traversal)
     */
    public Node succ(Node node) {
        if (node == leafNode) {
            throw new NullPointerException("Node is null");
        }

        if (node.right != leafNode) {
            return minNode(node.right);
        }

        Node parent = node.parent;
        while (parent != leafNode && node == parent.right) {
            node = parent;
            parent = parent.parent;
        }

        return parent;
    }

    // Find minNode to find successor (Going left and down while node exist)
    private Node minNode(Node node) {
        if (node == leafNode) {
            throw new NullPointerException("Node is null");
        }
        Node temp = node;

        while (temp.left != leafNode) {
            temp = temp.left;
        }

        return temp;
    }

    private void printInorder(Node node) {
        if (node == leafNode) {
            return;
        }
        /* first recur on left child */
        printInorder(node.left);

        /* then print the data of node */
        System.out.print(node.key);

        /* now recur on right child */
        printInorder(node.right);
    }

    /**
     * Print RBT inorder traversal
     */
    void printInorder() {
        printInorder(root);
    }
}
