import java.util.ArrayList;
import java.util.Collection;

/**
 * A non-balancing implementation of a binary tree.
 * @param <Comparable> The type stored in the Binary Search Tree
 * @author Jason Liang
 * @version 4 April 2019
 */
public class BST<Comparable> {
    /**
     * Number of Comparables objects in each line of toString().
     * 0 to use only 1 line.
     */
    private static int maxStringLimit = 0;

    /**
     * The head of the tree
     */
    private BSTNode root;
    /**
     * The number of Comparables stored in the tree
     */
    private int size;

    /**
     * Constructs a binary tree which initializes data into a root node
     * @param data The data of the root node
     */
    public BST(Comparable data) {
        insert(data);
    }

    /**
     * Constructs an empty binary tree
     */
    public BST() {
        root = null;
        size = 0;
    }

    /**
     * Returns true if the tree contains the specified comparable
     * @param designation The specified comparable to be searched for.
     * @return {@code true} if the variable is found
     */
    public boolean find(Comparable designation) {
        return find(root, designation) != null;
    }

    /**
     * Helper Method: Returns the first node of this tree containing the specified comparable.
     * If there is no such comparable, returns null.
     * @param node The node being searched
     * @param designation The specified comparable
     * @return The node containing the comparable
     */
    @SuppressWarnings("unchecked")
    private BSTNode find(BSTNode node, Comparable designation) {
        if (node == null)
            return null;
        if (node.compareTo(designation) == 0)
            return node;
        if ((node.compareTo(designation) > 0))
            return find(node.left, designation);
        return find(node.right, designation);
    }

    /**
     * Inserts a list of specified Comparable(s)
     * @param list {@code Comparable} list to be inserted
     */
    public void insert(Collection<Comparable> list) {
        for (Comparable part: list)
            insert(part);
    }

    /**
     * Inserts the specified Comparable
     * @param designation {@code Comparable} to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert(Comparable designation) {
        root = insert(root, designation);
        size++;
    }

    /**
     * Helper method: Inserts the specified Comparable either left or right of the node depending on
     * whether the node is less than and equal to or greater than, respectively.
     * If there is node already pointing to the node being compared, the method
     * will attempt to store the Comparable into the node being compared to.
     * @param node The {@code BSTNode} being compared to
     * @param designation The specified {@code Comparable}
     * @return the {@code BSTNode} to be (re)linked to its parent
     */
    @SuppressWarnings("unchecked")
    private BSTNode<Comparable> insert(BSTNode node, Comparable designation) {
        if (node == null) {
            return new BSTNode(designation);
        }
        if (node.compareTo(designation) >= 0) { //could not do alternate method
            node.left = insert(node.left, designation);
        }
        else {
            node.right = insert(node.right, designation);
        }
        return node;
    }


    /**
     * Returns true if the specified Comparable was successfully deleted
     * @param designation The specified {@code Comparable}
     * @return {@code true} if the Comparable was removed. {@code false} otherwise.
     */
    public boolean delete(Comparable designation) {
        int temp = size;
        root = delete(root, designation);
        return size < temp; //Janky code
    }

    /**
     * Helper Method: Finds the node with the designation.
     * Then, the node is unlinked by either returning the left or right child
     * if at least one the children is empty or unlinks the next in order successor,
     * a Comparable in the tree that is slightly higher than the previous.
     * @param node
     * @param designation The specified {@code Comparable}
     * @return the {@code BSTNode} to be (re)linked to its parent
     */
    @SuppressWarnings("unchecked")
    private BSTNode delete(BSTNode node, Comparable designation) {
        if (node == null)
            return null;
        if (node.compareTo(designation) == 0) {
            size--;
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            BSTNode temp = getLeftmost(node.right);
            node = delete(node, (Comparable)(temp.data));
            size++; //replaces the lost size from 2nd call.
            temp.left = node.left;
            temp.right = node.right;

            return temp;
        }
        else if ((node.compareTo(designation) > 0))
            node.left = delete(node.left, designation);
        else
            node.right = delete(node.right, designation);
        return node;
    }

//    private BSTNode inOrderSuccessor(BSTNode node) {
//        return getLeftmost(node.right);
//    }

    /**
     * Finds the leftmost, which corresponds to the lowest node of the starting parent.
     * @param node The starting node
     * @return the node containing the lowest Comparable of the parent node.
     */
    private BSTNode getLeftmost(BSTNode node) {
        if (node.left != null)
            return getLeftmost(node.left);
        return node;
    }


    /**
     * Prints the toString()
     */
    public void print() {
        System.out.println(toString());
    }

    /**
     * Returns a String of one line representing the tree in linear order.
     * @return String of the tree in order.
     */
    public String toString() {
        return toString(maxStringLimit);
    }

    /**
     * Returns a String of Math.ceil(array.length/stringPerLine) lines representing the tree in linear order.
     * If stringPerLine is 0, then there will not be any new line calls.
     * Otherwise, the toString will put stringPerLine Comparables in one line
     * @param stringPerLine The maximum count of Comparables in each line.
     * @return String of the tree in order.
     */
    public String toString(int stringPerLine) {
        Comparable[] array = toArray();
        String display = "[";
        for (int i = 0; i < array.length - 1; i++) {
            display += array[i];

            display += ", ";
            if (stringPerLine > 0 && (i + 1) % stringPerLine == 0)
                display += "\n";

        }
        if (array.length != 0)
            display += array[array.length-1];
        display +=  "]";
        return display;
    }

    /**
     * Returns an array of Comparables representing the tree in linear order.
     * @return An ordered array of Comparables of the tree.
     */
    @SuppressWarnings("unchecked")
    public Comparable[] toArray() {
        ArrayList<Comparable> list = new ArrayList<>(size);
        fillArray(root, list);
        return (Comparable[])list.toArray();
    }


    /**
     * Helper method: Adds the Comparable into the ArrayList in order to fill it.
     * @param node The current node being read
     * @param list The list being written into.
     */
    @SuppressWarnings("unchecked")
    private void fillArray(BSTNode node, ArrayList<Comparable> list) {
        if (node != null) {
            fillArray(node.left, list);
            list.add((Comparable)(node.data));
            fillArray(node.right, list);
        }
    }

    /**
     * Returns the number of Comparables in this tree.
     * @return The size of the tree.
     */
    public int size() {
        return size;
    }

//    public void print() {
//        System.out.print("[");
//        print(root);
//        System.out.println("]");
//    }
//    private void print(BSTNode node) {
//        if (node != null) {
//            print(node.left);
//            System.out.print(node.data + " ");
//            print(node.right);
//        }
//    }


    /**
     * Janky helper method for BST.java (Binary Search Tree)
     * @param <Comparable> Stored data type
     */
    private class BSTNode<Comparable>{
        BSTNode left, right;
        Comparable data;

        /**
         * Constructs a unit containing Comparable data.
         * @param data
         */
        public BSTNode(Comparable data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        @SuppressWarnings("unchecked")
        public int compareTo(BSTNode other) throws ClassCastException{
            return compareTo((Comparable)other.data);
        }

        @SuppressWarnings("unchecked")
        public int compareTo(Comparable other) throws ClassCastException{
            if (!(data instanceof java.lang.Comparable) || !(other instanceof java.lang.Comparable))
                throw new ClassCastException();
            return ((java.lang.Comparable)data).compareTo(other);
        }

//
//        public BSTNode left() {
//            return left;
//        }
//
//        public BSTNode right() {
//            return right;
//        }
//
//        public void setLeft(BSTNode left) {
//            this.left = left;
//        }
//
//        public void setRight(BSTNode right) {
//            this.right = right;
//        }
//
//        public void setData(T data) {
//            this.data = data;
//        }
    }
}
