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
class RedBlackTree<T extends Comparable<T>> {
    /**
     * The root of the RBT
     */
    public Node root;

    RedBlackTree() {
        root = null;
    }

    enum Colour {
        RED,
        BLACK
    }

    class Node {
        protected T key;
        protected Colour colour;

        public Node leftChild;
        public Node rightChild;
        public Node parent;

        Node(T key) {
            this.key = key;
            colour = Colour.RED;

            leftChild = null;
            rightChild = null;
            parent = null;
        }

        protected boolean hasChildRed() {
            return  (leftChild != null && leftChild.colour == Colour.RED)
                    ||
                    (rightChild != null && rightChild.colour == Colour.RED);
        }

        protected boolean isLeftChild() {
            return parent.leftChild == this;
        }

        protected Node sibling() {
            if (parent == null) {
                return null;
            }

            if (isLeftChild()) {
                return parent.rightChild;
            }

            return parent.leftChild;
        }
        protected void replaceWithNodeBelow(Node newParent) {
            if (parent != null) {
                if (isLeftChild()) {
                    parent.leftChild = newParent;
                } else {
                    parent.rightChild = newParent;
                }
            }
            newParent.parent = parent;
            parent = newParent;
        }
    }

    // Rotate right by node
    private Node turnRight(Node node) {
        Node newParent = node.leftChild;

        if (node == root) {
            root = newParent;
        }

        node.replaceWithNodeBelow(newParent);
        node.leftChild = newParent.rightChild;
        if (newParent.rightChild != null) {
            newParent.rightChild.parent = node;
        }
        newParent.rightChild = node;
        return newParent;
    }

    // Rotate left by node
    private Node turnLeft(Node node) {
        Node newParent = node.rightChild;

        if (node == root) {
            root = newParent;
        }

        node.replaceWithNodeBelow(newParent);
        node.rightChild = newParent.leftChild;
        if (newParent.leftChild != null) {
            newParent.leftChild.parent = node;
        }
        newParent.leftChild = node;
        return newParent;
    }

    /**
     * Insert Node with data = key in RBT
     * Complexity: O(log(n)) average and worst case
     * @param key the data you want to include in RBT
     */
    public void insert(T key) {
        if (root == null) {
            root = new Node(key);
            root.colour = Colour.BLACK;
            return;
        }

        root = insertionStep(root, key);
    }

    boolean l = false;
    boolean lr = false;
    boolean rl = false;
    boolean r = false;
    private Node insertionStep(Node curRoot, T key) {
        boolean problemRR = false;

        if (curRoot == null) {
            return new Node(key);
        } else if (key.compareTo(curRoot.key) < 0) {
            curRoot.leftChild = insertionStep(curRoot.leftChild, key);
            curRoot.leftChild.parent = curRoot;

            if (curRoot != root) {
                if (curRoot.colour == Colour.RED && curRoot.colour == curRoot.leftChild.colour) {
                    problemRR = true;
                }
            }
        } else {
            curRoot.rightChild = insertionStep(curRoot.rightChild, key);
            curRoot.rightChild.parent = curRoot;

            if (curRoot != root) {
                if (curRoot.colour == Colour.RED && curRoot.colour == curRoot.rightChild.colour) {
                    problemRR = true;
                }
            }
        }

        if (this.l) {
            curRoot = turnLeft(curRoot);
            curRoot.leftChild.colour = Colour.RED;
            curRoot.colour = Colour.BLACK;
            this.l = false;
        } else if (this.r) {
            curRoot = turnRight(curRoot);
            curRoot.rightChild.colour = Colour.RED;
            curRoot.colour = Colour.BLACK;
            this.r = false;
        } else if (this.rl) {
            curRoot.rightChild = turnRight(curRoot.rightChild);
            curRoot.rightChild.parent = curRoot;
            curRoot = turnLeft(curRoot);
            curRoot.colour = Colour.BLACK;
            curRoot.leftChild.colour = Colour.RED;
            this.rl = false;
        } else if (this.lr) {
            curRoot.leftChild = turnLeft(curRoot.leftChild);
            curRoot.leftChild.parent = curRoot;
            curRoot = turnRight(curRoot);
            curRoot.colour = Colour.BLACK;
            curRoot.rightChild.colour = Colour.RED;
            this.lr = false;
        }

        int whichChild = -1;
        if (problemRR) {
            if (curRoot.parent.rightChild == curRoot) {
                whichChild = 0;
            } else if (curRoot.parent.leftChild == curRoot) {
                whichChild = 1;
            }

            switch (whichChild) {
                case 0 ->
                {
                    if (curRoot.sibling() == null || curRoot.sibling().colour == Colour.BLACK) {
                        if (curRoot.leftChild != null && curRoot.leftChild.colour == Colour.RED) {
                            this.rl = true;
                        } else if (curRoot.rightChild != null && curRoot.rightChild.colour == Colour.RED) {
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
                    if (curRoot.sibling() == null || curRoot.sibling().colour == Colour.BLACK) {
                        if (curRoot.leftChild != null && curRoot.leftChild.colour == Colour.RED) {
                            this.r = true;
                        } else if (curRoot.rightChild != null && curRoot.rightChild.colour == Colour.RED) {
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
        if (del == null) {
            return null;
        }

        int numOfChildren = 0;
        if (del.leftChild != null) numOfChildren++;
        if (del.rightChild != null) numOfChildren++;

        // Case 1: leaf
        if (numOfChildren == 0) {
            return null;
        }

        // Case 2: one child
        if (numOfChildren == 1) {
            return Objects.requireNonNullElseGet(del.leftChild, () -> del.rightChild);
        }

        // Case 3: two children
        return predBelow(del.leftChild);
    }

    protected void fixBLACK2(Node curRoot) {
        if (curRoot == root) {
            return;
        }

        Node sibling = curRoot.sibling();
        Node parent = curRoot.parent;
        if (sibling == null) {
            fixBLACK2(parent);
        } else {
            if (sibling.colour == Colour.RED) {
                parent.colour = Colour.RED;
                sibling.colour = Colour.BLACK;
                if (sibling.isLeftChild()) {
                    turnRight(parent);
                } else {
                    turnLeft(parent);
                }
                fixBLACK2(curRoot);
            } else {
                if (sibling.hasChildRed()) {
                    if (sibling.leftChild != null && sibling.leftChild.colour == Colour.RED) {
                        if (sibling.isLeftChild()) {
                            sibling.leftChild.colour = sibling.colour;
                            sibling.colour = parent.colour;
                            turnRight(parent);
                        } else {
                            sibling.leftChild.colour = parent.colour;
                            turnRight(sibling);
                            turnLeft(parent);
                        }
                    } else {
                        if (sibling.isLeftChild()) {
                            sibling.rightChild.colour = parent.colour;
                            turnLeft(sibling);
                            turnRight(parent);
                        } else {
                            sibling.rightChild.colour = sibling.colour;
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
        if (del == null) return;

        Node rep = replaceBST(del);

        if (rep == null) {
            if (del == root) {
                root = null;
            } else {
                if (del.colour == Colour.BLACK) {
                    fixBLACK2(del);
                } else {
                    if (del.sibling() != null) {
                        del.sibling().colour = Colour.RED;
                    }
                }

                if (del.isLeftChild()) {
                    del.parent.leftChild = null;
                } else {
                    del.parent.rightChild = null;
                }
            }
            return;
        }

        if (del.leftChild == null || del.rightChild == null) {
            if (del == root) {
                root = new Node(rep.key);
                root.leftChild = root.rightChild = null;
                root.colour = Colour.BLACK;
            } else {
                if (del.isLeftChild()) {
                    del.parent.leftChild = rep;
                } else {
                    del.parent.rightChild = rep;
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
    public void delete(T key) {
        deleteStep(find(root, key));
    }

    /**
     * Find the node in the RBT with data = key
     * Complexity: O(log(n)) average and worst case
     * @param curRoot more often - root of the RBT
     * @param key data which will be found in the RBT
     * @return Node with the data = key
     */
    public Node find(Node curRoot, T key) {
        if(curRoot == null) {
            return null;
        }
        if (key.compareTo(curRoot.key) == 0) {
            return curRoot;
        }
        if (curRoot.leftChild != null && key.compareTo(curRoot.key) < 0) {
            return find(curRoot.leftChild, key);
        }
        if (curRoot.rightChild != null && key.compareTo(curRoot.key) > 0) {
            return find(curRoot.rightChild, key);
        }
        return null;
    }

    // Find the lower predecessor of the node
    protected Node predBelow(Node node) {
        Node temp = node;

        while (temp.rightChild != null) {
            temp = temp.rightChild;
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
        if (node == null) {
            return null;
        }

        if (node.leftChild != null) {
            return maxNode(node.leftChild);
        }

        Node parent = node.parent;
        while (parent != null && node == parent.leftChild) {
            node = parent;
            parent = parent.parent;
        }

        return parent;
    }

    // Find maxNode to find predecessor (Going right and down while node exist)
    private Node maxNode(Node node) {
        Node temp = node;

        while (temp.rightChild != null) {
            temp = temp.rightChild;
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
        if (node == null) {
            return null;
        }

        if (node.rightChild != null) {
            return minNode(node.rightChild);
        }

        Node parent = node.parent;
        while (parent != null && node == parent.rightChild) {
            node = parent;
            parent = parent.parent;
        }

        return parent;
    }

    // Find minNode to find successor (Going left and down while node exist)
    private Node minNode(Node node) {
        Node temp = node;

        while (temp.leftChild != null) {
            temp = temp.leftChild;
        }

        return temp;
    }

    private void printInorder(Node node) {
        if (node == null)
            return;

        /* first recur on left child */
        printInorder(node.leftChild);

        /* then print the data of node */
        System.out.print(node.key);

        /* now recur on right child */
        printInorder(node.rightChild);
    }

    /**
     * Print RBT inorder traversal
     */
    void printInorder() {
        printInorder(root);
    }
}
