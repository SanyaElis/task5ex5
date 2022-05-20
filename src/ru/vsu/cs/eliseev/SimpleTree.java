package ru.vsu.cs.eliseev;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SimpleTree<T> {

    protected class SimpleTreeNode {
        public T value;
        public ArrayList<SimpleTreeNode> descendants;

        public SimpleTreeNode(T value, ArrayList<SimpleTreeNode> descendants) {
            this.value = value;
            this.descendants = descendants;
        }
        public SimpleTreeNode(T value) {
            this(value, null);
        }

        public T getValue() {
            return value;
        }

        public ArrayList<SimpleTreeNode> getDescendants() {
            return descendants;
        }

    }
    protected SimpleTreeNode root = null;

    protected Function<String, T> fromStrFunc;
    protected Function<T, String> toStrFunc;

    public SimpleTree(Function<String, T> fromStrFunc, Function<T, String> toStrFunc) {
        this.fromStrFunc = fromStrFunc;
        this.toStrFunc = toStrFunc;
    }
    public SimpleTree(Function<String, T> fromStrFunc) {
        this(fromStrFunc, Object::toString);
    }

    public SimpleTree() {
        this(null);
    }

    public SimpleTreeNode getRoot() {
        return root;
    }
    public void clear() {
        root = null;
    }

    private int numSpaces(String[] tree, int index) {
        int spaces = 0;
        while (Character.isWhitespace(tree[index].charAt(spaces))) {
            spaces++;
        }
        return spaces;
    }
    private int tempIndex = 0;

    private SimpleTreeNode fromStr(String[] tree) throws Exception {
        int spaces = numSpaces(tree, tempIndex);
        T parentValue = (T) tree[tempIndex].substring(spaces);
        int parentSpaces = spaces;
        SimpleTreeNode parentNode = new SimpleTreeNode(parentValue);
        tempIndex++;
        if (tempIndex + 1 <= tree.length && numSpaces(tree, tempIndex) == spaces + 2){
            ArrayList<SimpleTreeNode> des = new ArrayList<>();
            des.add(fromStr(tree));
            while (tempIndex + 1 <= tree.length && parentSpaces + 2 == numSpaces(tree, tempIndex)){
                des.add(fromStr(tree));
            }
            parentNode.descendants = des;
        }
        return parentNode;
    }

    public void fromStrNotation(String[] tree) throws Exception {
        this.root = fromStr(tree);
        if (tempIndex != tree.length){
            throw new Exception();
        }
    }
}
