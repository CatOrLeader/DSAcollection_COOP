package datastructures.trees;

class AVLTree<T extends Comparable<T>> {
    private Node<T> root;

    public AVLTree() {
        this.root = null;
    }

    /**
     * Helper method to determine the height of a node.
     * Worst case time complexity: O(1)
     */
    public int height(Node<T> node) {
        if (node == null)
            return 0;
        return node.height;
    }

    /**
     * Worst case time complexity: O(1)
     */
    private Node<T> rightRotate(Node<T> node) {
        Node<T> l = node.left;
        Node<T> lr = l.right;
        l.right = node;
        node.left = lr;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        l.height = Math.max(height(l.left), height(l.right)) + 1;
        return l;
    }

    /**
     * Worst case time complexity: O(1)
     */
    private Node<T> leftRotate(Node<T> node) {
        Node<T> r = node.right;
        Node<T> rl = r.left;
        r.left = node;
        node.right = rl;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        r.height = Math.max(height(r.left), height(r.right)) + 1;
        return r;
    }

    /**
     * Worst case time complexity: O(1)
     */
    public int balance(Node<T> node) {
        if (node == null) {
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
    private Node<T> insert(Node<T> node, T key) {
        if (node == null) {
            return new Node<>(key);
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
    private Node<T> remove(Node<T> root, T key) {
        if (root == null) {
            return null;
        }
        if (key.compareTo(root.key) < 0) {
            root.left = remove(root.left, key);
        } else if (key.compareTo(root.key) > 0) {
            root.right = remove(root.right, key);
        } else {
            if (root.left == null || root.right == null) {
                Node<T> temp;
                if (null == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }
                root = temp;
            } else {
                Node<T> temp = findMin(root.right);
                root.key = temp.key;
                root.right = remove(root.right, temp.key);
            }
        }
        if (root == null) {
            return null;
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
    public Node<T> find(T key) {
        if (this.root == null) {
            throw new NullPointerException("Root node is null");
        }
        Node<T> curNode = this.root;
        while (curNode != null && curNode.key.compareTo(key) != 0) {
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
    public Node<T> predecessor(Node<T> node) {
        if (node == null) {
            throw new NullPointerException("Current node is null");
        }
        Node<T> current;
        if (node.left != null) {
            current = node.left;
            while (current.right != null) {
                current = current.right;
            }
            return current;
        } else {
            current = node;
            Node<T> parent = parent(node);
            while (parent != null && current == parent.left) {
                current = parent;
                parent = parent(parent);
            }
            return parent;
        }
    }

    /**
     * Worst case time complexity: O(log(n))
     */
    public Node<T> successor(Node<T> node) {
        if (node == null) {
            throw new NullPointerException("Current node is null");
        }
        Node<T> current;
        if (node.right != null) {
            current = node.right;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        } else {
            current = node;
            Node<T> parent = parent(node);
            while (parent != null && current == parent.right) {
                current = parent;
                parent = parent(parent);
            }
            return parent;
        }
    }

    /**
     * Worst case time complexity: O(log(n))
     */
    private Node<T> parent(Node<T> node) {
        if (node == this.root) {
            return null;
        }
        Node<T> curNode = this.root;
        while (curNode.left != null && curNode.left.key.compareTo(node.key) != 0
                && curNode.right != null && curNode.right.key.compareTo(node.key) != 0) {
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
    private Node<T> findMin(Node<T> node) {
        Node<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    class Node<T extends Comparable<T>> { //use just as a struct
        public T key;
        public int height;
        public Node<T> left;
        public Node<T> right;

        public Node(T key) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }
}
