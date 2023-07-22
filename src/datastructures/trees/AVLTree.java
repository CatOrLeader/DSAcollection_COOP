package datastructures.trees;

class AVLTree<T extends Comparable<T>> {
    private Node root;
    private final Node leafNode;

    public AVLTree() {
        leafNode = new Node(null);
        this.root = leafNode;
    }

    /**
     * Helper method to determine the height of a node.
     * Worst case time complexity: O(1)
     */
    public int height(Node node) {
        if (node == leafNode) { //todo
            throw new NullPointerException("Node is null");
        }
        return node.height;
    }

    /**
     * Worst case time complexity: O(1)
     */
    private Node rightRotate(Node node) {
        Node l = node.left;
        Node lr = l.right;
        l.right = node;
        node.left = lr;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        l.height = Math.max(height(l.left), height(l.right)) + 1;
        return l;
    }

    /**
     * Worst case time complexity: O(1)
     */
    private Node leftRotate(Node node) {
        Node r = node.right;
        Node rl = r.left;
        r.left = node;
        node.right = rl;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        r.height = Math.max(height(r.left), height(r.right)) + 1;
        return r;
    }

    /**
     * Worst case time complexity: O(1)
     */
    public int balance(Node node) {
        if (node == null || node == leafNode) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    /**
     * Worst case time complexity: O(log(n))
     */
    public void insert(T key) {
        root = insert(root, key);
    }

    /**
     * Worst case time complexity: O(log(n))
     */
    private Node insert(Node node, T key) {
        if (node == leafNode) {
            return new Node(key);
        }
        if (node.key.compareTo(key) > 0) {
            node.left = insert(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = balance(node);
        if (balance > 1 && node.left.key.compareTo(key) > 0) {
            return rightRotate(node);
        }
        if (balance < -1 && node.right.key.compareTo(key) < 0) {
            return leftRotate(node);
        }
        if (balance > 1 && node.left.key.compareTo(key) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && node.right.key.compareTo(key) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    /**
     * Worst case time complexity: O(log(n))
     */
    public void remove(T key) {
        root = remove(root, key);
    }

    /**
     * Worst case time complexity: O(log(n))
     */
    private Node remove(Node root, T key) {
        if (root == leafNode) {
            throw new NullPointerException("Root node is null");
        }
        if (key.compareTo(root.key) < 0) {
            root.left = remove(root.left, key);
        } else if (key.compareTo(root.key) > 0) {
            root.right = remove(root.right, key);
        } else {
            if (root.left == leafNode || root.right == leafNode) {
                Node temp;
                if (null == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }
                root = temp;
            } else {
                Node temp = findMin(root.right);
                root.key = temp.key;
                root.right = remove(root.right, temp.key);
            }
        }
        if (root == leafNode) {
            throw new NullPointerException("Root node is null");
        }
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = balance(root);
        if (balance > 1 && balance(root.left) >= 0) {
            return rightRotate(root);
        }
        if (balance > 1 && balance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && balance(root.right) <= 0) {
            return leftRotate(root);
        }
        if (balance < -1 && balance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    /**
     * Worst case time complexity: O(log(n))
     */
    public Node find(T key) {
        if (this.root == leafNode) {
            throw new NullPointerException("Root node is null");
        }
        Node curNode = this.root;
        while (curNode != leafNode && curNode.key.compareTo(key) != 0) {
            if (curNode.key.compareTo(key) > 0) {
                curNode = curNode.left;
            } else {
                curNode = curNode.right;
            }
        }
        return curNode;
    }

    /**
     * Worst case time complexity: O(log(n))
     */
    public Node predecessor(Node node) {
        if (node == leafNode) {
            throw new NullPointerException("Current node is null");
        }
        Node current;
        if (node.left != leafNode) {
            current = node.left;
            while (current.right != leafNode) {
                current = current.right;
            }
            return current;
        } else {
            current = node;
            Node parent = parent(node);
            while (parent != leafNode && current == parent.left) {
                current = parent;
                parent = parent(parent);
            }
            return parent;
        }
    }

    /**
     * Worst case time complexity: O(log(n))
     */
    public Node successor(Node node) {
        if (node == leafNode) {
            throw new NullPointerException("Current node is null");
        }
        Node current;
        if (node.right != leafNode) {
            current = node.right;
            while (current.left != leafNode) {
                current = current.left;
            }
            return current;
        } else {
            current = node;
            Node parent = parent(node);
            while (parent != leafNode && current == parent.right) {
                current = parent;
                parent = parent(parent);
            }
            return parent;
        }
    }

    /**
     * Worst case time complexity: O(log(n))
     */
    private Node parent(Node node) {
        if (node == this.root) {
            return leafNode;
        }
        Node curNode = this.root;
        while (curNode.left != leafNode && curNode.left.key.compareTo(node.key) != 0
                && curNode.right != leafNode && curNode.right.key.compareTo(node.key) != 0) {
            if (node.key.compareTo(curNode.key) > 0) {
                curNode = curNode.right;
            } else {
                curNode = curNode.left;
            }
        }
        return curNode;
    }

    /**
     * Worst case time complexity: O(log(n))
     */
    private Node findMin(Node node) {
        Node current = node;
        while (current.left != leafNode) {
            current = current.left;
        }
        return current;
    }

    class Node { //use just as a struct
        public T key;
        public int height;
        public Node left;
        public Node right;

        public Node(T key) {
            this.key = key;
            this.left = leafNode;
            this.right = leafNode;
            this.height = 1;
        }
    }
}
