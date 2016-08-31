package net.igorkromin.supporting;

/**
 * Very simple linked list node, not attempt to make it too usable, just the bare bones.\
 * Wouldn't use this in the real world.
 */
public class Node {
    public int value;
    public Node nextNode;

    public Node(int v) {
        value = v;
    }
}
