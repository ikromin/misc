package net.igorkromin.supporting;

/**
 * Very simple linked list implementation, just the bare bones and useful only for the exercies in this project.
 */
public class LinkedList {

    /**
     * Prints out the contents of a linked list to console.
     */
    public static void print(Node head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.nextNode;
        }
    }

    /**
     * Constructs a linked list out of an array and returns the head element of that list.
     */
    public static Node arrayToList(int[] arr) {
        Node head = null;
        Node prev = null;

        for (int i = 0; i < arr.length; i++) {
            Node n = new Node(arr[i]);

            if (head == null) {
                head = n;
            }

            if (prev != null) {
                prev.nextNode = n;
            }

            prev = n;
        }

        return head;
    }

    /**
     * Reverses a linked list and returns the head of the reversed list.
     */
    public static Node reverse(Node head) {
        // e.g.
        // 1 -> 2 -> 3 -> null
        // 3 -> 2 -> 1 -> null

        Node prev = null, next = head.nextNode;

        while (next != null) {
            next = head.nextNode;
            head.nextNode = prev;
            prev = head;

            if (next != null) {
                head = next;
            }
        }

        return head;
    }

}
