package main.java.linkedlist;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1622:15
 */
class Node{
    public int val;
    Node next;

    public Node(int i, Node o) {
        val = i;
        next = o;
    }
}
public class ReverseLinkedlist {
    public static void main(String[] args){
        Node head = new Node(1, new Node(2, new Node(3, new Node(4, null))));
        recordList(head);
        while(head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }

    private static void recordList(Node head) {
        if(head == null){
            return;
        }
        Node mid = middleNode(head);
        Node l1 = head;
        Node l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    private static void mergeList(Node l1, Node l2) {
        Node l1_tmp;
        Node l2_tmp;
        while(l1 != null && l2 != null){
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }

    private static Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;
        while(curr != null){
            Node nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private static Node middleNode(Node head) {
        Node slow = head;
        Node fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


}
