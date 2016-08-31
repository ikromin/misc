package net.igorkromin.exercises;

import net.igorkromin.Exercise;
import net.igorkromin.supporting.LinkedList;
import net.igorkromin.supporting.Node;

/**
 * Given a singly linked list, find out if it is a palindrome.
 *
 * Example
 *
 * 1 -> 2 -> 3 -> 2 -> 1
 *
 * Should return true.
 */
public class SinglyLinkListPalindrome extends Exercise {

    public void solveWithHalfListReverse() {
        int[] vals = {1, 2, 3, 2, 1};

        Node head = LinkedList.arrayToList(vals);
        Node node;

        // find out the size of the list
        int size = 0;
        node = head;
        while (node != null) {
            node = node.nextNode;
            size++;
        }

        // determine split position based on odd/even counts
        int split = (size % 2 == 0) ? size/2 : (size/2) + 1;

        // fast forward to split position
        node = head;
        for (int i = 0; i < split; i++) {
            node = node.nextNode;
        }

        // reverse the list
        Node head_r = LinkedList.reverse(node);

        // compare two lists
        boolean matches = true;
        while (head_r != null) {
            if (head.value != head_r.value) {
                matches = false;
                break;
            }

            head = head.nextNode;
            head_r = head_r.nextNode;
        }

        out("Is palindrome: " + matches);

        LinkedList.print(head_r);
    }

    // -- Main method

    public static final void main(String[] args) {
        run(new SinglyLinkListPalindrome());
    }

}
