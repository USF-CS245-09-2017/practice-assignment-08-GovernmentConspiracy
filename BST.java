import java.util.ArrayList;
import java.util.Collection;

public class BST<Comparable> {
    /**
     * Number of Comparables objects in each line of toString().
     * 0 to use only 1 line.
     */
    private static int maxStringLimit = 0;

    private BSTNode root;
    private int size;

    public BST(Comparable data) {
        root = new BSTNode<Comparable>(data);
    }
    public BST() {
        root = null;
    }

    public boolean find(Comparable designation) {
        return find(root, designation) != null;
    }

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

    public void insert(Collection<Comparable> list) {
        for (Comparable part: list)
            insert(part);
    }

    @SuppressWarnings("unchecked")
    public void insert(Comparable designation) {
        root = insert(root, designation);
        size++;
    }
    @SuppressWarnings("unchecked")
    private BSTNode<Comparable> insert(BSTNode node, Comparable designation) {
        if (node == null) {
            return new BSTNode(designation);
        }
        if (node.compareTo(designation) >= 0) {
            node.left = insert(node.left, designation);
        }
        else {
            node.right = insert(node.right, designation);
        }
        return node;
    }



    public boolean delete(Comparable designation) {
        int temp = size;
        root = delete(root, designation);
        return size < temp; //Janky code
    }

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
            //needs inOrderSuccessor
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

    private BSTNode inOrderSuccessor(BSTNode node) {
        return getLeftmost(node.right);
    }

    private BSTNode getLeftmost(BSTNode node) {
        if (node.left != null)
            return getLeftmost(node.left);
        return node;
    }


    public void print() {
        System.out.println(toString());
    }

    public String toString() {
        return toString(maxStringLimit);
    }

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

    @SuppressWarnings("unchecked")
    public Comparable[] toArray() {
        ArrayList<Comparable> list = new ArrayList<>(size);
        fillArray(root, list);
        return (Comparable[])list.toArray();
    }

    @SuppressWarnings("unchecked")
    private void fillArray(BSTNode node, ArrayList<Comparable> list) {
        if (node != null) {
            fillArray(node.left, list);
            list.add((Comparable)(node.data));
            fillArray(node.right, list);
        }
    }

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
