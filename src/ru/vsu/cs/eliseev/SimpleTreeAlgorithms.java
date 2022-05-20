package ru.vsu.cs.eliseev;

public class SimpleTreeAlgorithms {
    public static <T> String toBracketStr(SimpleTree.SimpleTreeNode tree) {
        class Inner {
            void printTo(SimpleTree.SimpleTreeNode node, StringBuilder sb) {
                if (node == null) {
                    return;
                }
                sb.append(node.getValue());
                if (node.getDescendants() != null) {
                    int i = 0;
                    sb.append(" (");
                    printTo((SimpleTree.SimpleTreeNode) node.getDescendants().get(i), sb);
                    while (node.getDescendants().size() > i + 1) {
                        i++;
                        sb.append(", ");
                        printTo((SimpleTree.SimpleTreeNode) node.getDescendants().get(i), sb);
                    }
                    sb.append(")");
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        new Inner().printTo(tree, sb);

        return sb.toString();
    }
}
