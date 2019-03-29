import java.util.ArrayList;

public class BST<Comparable> {
    BSTNode root;
    int size;
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

    @SuppressWarnings("unchecked")
    public void insert(Comparable data) {
        root = insert(root, new BSTNode(data));
        size++;
    }
    @SuppressWarnings("unchecked")
    private BSTNode<Comparable> insert(BSTNode node, BSTNode temp) {
        if (node == null) {
            return temp;
        }
        if (temp.compareTo(node) < 0) {
            node.left = insert(node.left, temp);
        }
        else {
            node.right = insert(node.right, temp);
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
            if (node.right != null)
                return node.right;
            return node.left;
        }
        if ((node.compareTo(designation) > 0))
            node = delete(node.left, designation);
        else
            node = delete(node.right, designation);
        return node;
    }


    public void print() {
        System.out.println(toString());
    }

    public String toString() {
        Comparable[] array = toArray();
        String display = "[";
        for (int i = 0; i < array.length; i++) {
            display += array[i];
            if (i < array.length - 1)
                display += ", ";
        }
        display += "]";
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
     *
     * @param <Comparable>
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
            if (!(data instanceof java.lang.Comparable) || !(other.data instanceof java.lang.Comparable))
                throw new ClassCastException();
            return ((java.lang.Comparable)data).compareTo(other.data);
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
